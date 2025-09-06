import React, { useEffect, useState } from "react";
import {
  getMembers,
  addMember,
  updateMember,
  deleteMember,
} from "../services/api";

export default function Members() {
  const [members, setMembers] = useState([]);
  const [form, setForm] = useState({ name: "", email: "" });

  useEffect(() => {
    loadMembers();
  }, []);

  async function loadMembers() {
    setMembers(await getMembers());
  }

  async function handleSubmit(e) {
    e.preventDefault();
    await addMember(form);
    setForm({ name: "", email: "" });
    loadMembers();
  }

  async function handleDelete(id) {
    await deleteMember(id);
    loadMembers();
  }

  return (
    <div className="page">
      <h2>Members</h2>
      <form onSubmit={handleSubmit}>
        <input
          placeholder="Name"
          value={form.name}
          onChange={(e) => setForm({ ...form, name: e.target.value })}
        />
        <input
          placeholder="Email"
          value={form.email}
          onChange={(e) => setForm({ ...form, email: e.target.value })}
        />
        <button type="submit">Add</button>
      </form>
      <ul>
        {members.map((m) => (
          <li key={m.id}>
            {m.name} ({m.email})
            <button onClick={() => handleDelete(m.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
