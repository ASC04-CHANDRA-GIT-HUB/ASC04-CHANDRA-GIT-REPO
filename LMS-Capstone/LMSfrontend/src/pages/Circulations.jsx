import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import Sidebar from '../components/Sidebar';
import EntityTable from '../components/EntityTable';
import EntityForm from '../components/EntityForm';
import { getCirculations, createCirculation, updateCirculation, deleteCirculation } from '../services/circulationService';

const Circulations = () => {
  const [data, setData] = useState([]);
  const [editing, setEditing] = useState(null);

  const fetchData = async () => setData(await getCirculations());
  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (formData) => {
    if (editing) {
      await updateCirculation(editing.id, formData);
      setEditing(null);
    } else {
      await createCirculation(formData);
    }
    fetchData();
  };

  const handleDelete = async (row) => {
    await deleteCirculation(row.id);
    fetchData();
  };

  const columns = ['id', 'memberName', 'bookTitle', 'issueDate', 'returnDate', 'status'];

  return (
    <div className="flex">
      <Sidebar />
      <main className="flex-1 p-6">
        <Navbar />
        <h1 className="text-2xl mb-4">Circulations</h1>
        <EntityForm
          fields={[
            { name: 'memberName', label: 'Member Name' },
            { name: 'bookTitle', label: 'Book Title' },
            { name: 'issueDate', label: 'Issue Date', type: 'date' },
            { name: 'returnDate', label: 'Return Date', type: 'date' },
            { name: 'status', label: 'Status', type: 'select', options: ['Issued', 'Returned'] }
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

export default Circulations;