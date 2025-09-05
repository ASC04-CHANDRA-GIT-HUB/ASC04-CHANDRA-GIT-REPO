import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import Sidebar from '../components/Sidebar';
import EntityTable from '../components/EntityTable';
import EntityForm from '../components/EntityForm';
import { getReviews, createReview, updateReview, deleteReview } from '../services/reviewService';
import { getMembers } from '../services/memberService';
import { getCatalogues } from '../services/catalogueService';

const Reviews = () => {
  const [data, setData] = useState([]);
  const [editing, setEditing] = useState(null);
  const [members, setMembers] = useState([]);
  const [catalogues, setCatalogues] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      setData(await getReviews());
      setMembers(await getMembers());
      setCatalogues(await getCatalogues());
    };
    fetchData();
  }, []);

  const handleSubmit = async (formData) => {
    if (editing) {
      await updateReview(editing.id, formData);
      setEditing(null);
    } else {
      await createReview(formData);
    }
    setData(await getReviews());
  };

  const handleDelete = async (row) => {
    await deleteReview(row.id);
    setData(await getReviews());
  };

  const columns = ['id', 'member_id', 'catalogue_id', 'rating', 'comments'];

  return (
    <div className="flex">
      <Sidebar />
      <main className="flex-1 p-6">
        <Navbar />
        <h1 className="text-2xl mb-4">Reviews</h1>
        <EntityForm
          fields={[
            { 
              name: 'member_id', 
              label: 'Member', 
              type: 'select', 
              options: members.map(m => m.id) 
            },
            { 
              name: 'catalogue_id', 
              label: 'Book', 
              type: 'select', 
              options: catalogues.map(c => c.id) 
            },
            { name: 'rating', label: 'Rating', type: 'number' },
            { name: 'comments', label: 'Comments' }
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

export default Reviews;