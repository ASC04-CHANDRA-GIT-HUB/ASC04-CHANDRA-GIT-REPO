import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import Sidebar from '../components/Sidebar';
import EntityTable from '../components/EntityTable';
import EntityForm from '../components/EntityForm';
import { getMembers, createMember, updateMember, deleteMember } from '../services/memberService';

const Members = () => {
  const [data, setData] = useState([]);
  const [editing, setEditing] = useState(null);

  const fetchData = async () => setData(await getMembers());
  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (formData) => {
    if (editing) {
      await updateMember(editing.id, formData);
      setEditing(null);
    } else {
      await createMember(formData);
    }
    fetchData();
  };

  const handleDelete = async (row) => {
    await deleteMember(row.id);
    fetchData();
  };

  const columns = ['id', 'name', 'email', 'phone'];

  return (
    <div className="flex">
      <Sidebar />
      <main className="flex-1 p-6">
        <Navbar />
        <h1 className="text-2xl mb-4">Members</h1>
        <EntityForm
          fields={[
            { name: 'name', label: 'Name' },
            { name: 'email', label: 'Email' },
            { name: 'phone', label: 'Phone' }
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

export default Members;