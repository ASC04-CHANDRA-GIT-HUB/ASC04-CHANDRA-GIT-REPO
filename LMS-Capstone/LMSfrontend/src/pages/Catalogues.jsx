import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import Sidebar from '../components/Sidebar';
import EntityTable from '../components/EntityTable';
import EntityForm from '../components/EntityForm';
import { getCatalogues, createCatalogue, updateCatalogue, deleteCatalogue } from '../services/catalogueService';

const Catalogues = () => {
  const [data, setData] = useState([]);
  const [editing, setEditing] = useState(null);

  const fetchData = async () => setData(await getCatalogues());
  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (formData) => {
    if (editing) {
      await updateCatalogue(editing.id, formData);
      setEditing(null);
    } else {
      await createCatalogue(formData);
    }
    fetchData();
  };

  const handleDelete = async (row) => {
    await deleteCatalogue(row.id);
    fetchData();
  };

  const columns = ['id', 'title', 'author', 'description', 'available', 'rating'];

  return (
    <div className="flex">
      <Sidebar />
      <main className="flex-1 p-6">
        <Navbar />
        <h1 className="text-2xl mb-4">Catalogues</h1>
        <EntityForm
          fields={[
            { name: 'title', label: 'Title' },
            { name: 'author', label: 'Author' },
            { name: 'description', label: 'Description' },
            { 
              name: 'available', 
              label: 'Available', 
              type: 'select', 
              options: [true , false] 
            },
            { name: 'rating', label: 'Rating', type: 'number' }
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

export default Catalogues;