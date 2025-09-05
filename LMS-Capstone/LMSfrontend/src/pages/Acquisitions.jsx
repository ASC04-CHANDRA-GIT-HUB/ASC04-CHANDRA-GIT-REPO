import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import Sidebar from '../components/Sidebar';
import EntityTable from '../components/EntityTable';
import EntityForm from '../components/EntityForm';
import { getAcquisitions, createAcquisition, updateAcquisition, deleteAcquisition } from '../services/acquisitionService';

const Acquisitions = () => {
  const [data, setData] = useState([]);
  const [editing, setEditing] = useState(null);

  const fetchData = async () => setData(await getAcquisitions());
  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (formData) => {
    if (editing) {
      await updateAcquisition(editing.id, formData);
      setEditing(null);
    } else {
      await createAcquisition(formData);
    }
    fetchData();
  };

  const handleDelete = async (row) => {
    await deleteAcquisition(row.id);
    fetchData();
  };

  // Table columns (ignore 'deleted')
  const columns = ['id', 'title', 'author', 'supplier', 'status', 'description'];

  return (
    <div className="flex">
      <Sidebar />
      <main className="flex-1 p-6">
        <Navbar />
        <h1 className="text-2xl mb-4">Acquisitions</h1>
        <EntityForm
          fields={[
            { name: 'title', label: 'Title' },
            { name: 'author', label: 'Author' },
            { name: 'supplier', label: 'Supplier' },
            { name: 'status', label: 'Status', type: 'select', options: ['REQUESTED', 'RECEIVED'] },
            { name: 'description', label: 'Description' }
          ]}
          initialData={editing || {}}
          onSubmit={handleSubmit}
          onCancel={() => setEditing(null)}
        />
        <EntityTable columns={columns} data={data} onEdit={setEditing} onDelete={handleDelete} />
      </main>
    </div>
  );
};

export default Acquisitions;