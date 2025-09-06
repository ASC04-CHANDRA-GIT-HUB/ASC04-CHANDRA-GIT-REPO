import { useEffect, useState } from "react";
import { getAcquisitions, addAcquisition, updateAcquisition, deleteAcquisition } from "../services/api";
import "./Pages.css";

export default function Acquisition() {
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({ title: '', author: '', supplier: '', status: 'PENDING' });
  const [editingId, setEditingId] = useState(null);
  const load = async () => {
    try { const data = await getAcquisitions(); setItems(data); } catch(e){ alert(e.message); }
  };
  useEffect(()=>{ load(); }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if(editingId){ await updateAcquisition(editingId, form); setEditingId(null); }
      else { await addAcquisition(form); }
      setForm({ title:'', author:'', supplier:'', status:'PENDING' });
      load();
    } catch(e){ alert(e.message); }
  };

  const handleEdit = (item) => { setEditingId(item.id); setForm({ title:item.title, author:item.author, supplier:item.supplier, status:item.status }); };
  const handleDelete = async (id) => { if(!confirm('Delete?')) return; try{ await deleteAcquisition(id); load(); }catch(e){alert(e.message);} };

  return (
    <div className="page">
      <h1>Acquisitions</h1>
      <ul>
        {items.map((a)=> (<li key={a.id} className="list-item"><span className="title">{a.title}</span> — {a.author} — {a.status} <button onClick={()=>handleEdit(a)}>Edit</button> <button onClick={()=>handleDelete(a.id)}>Delete</button></li>))}
      </ul>

      <h2>{editingId? 'Edit' : 'Add'} Acquisition</h2>
      <form className="form" onSubmit={handleSubmit}>
        <input value={form.title} onChange={(e)=>setForm({...form,title:e.target.value})} placeholder="Title" required />
        <input value={form.author} onChange={(e)=>setForm({...form,author:e.target.value})} placeholder="Author" required />
        <input value={form.supplier} onChange={(e)=>setForm({...form,supplier:e.target.value})} placeholder="Supplier" />
        <select value={form.status} onChange={(e)=>setForm({...form,status:e.target.value})}>
          <option value="PENDING">PENDING</option>
          <option value="RECEIVED">RECEIVED</option>
        </select>
        <button type="submit">Save</button>
      </form>
    </div>
);
}