import React, { useEffect, useState } from "react";
import {
  getCirculations,
  addCirculation,
  returnCirculation,
  deleteCirculation,
  checkCirculationExists,
} from "../services/api";

export default function Circulation() {
  const [circulations, setCirculations] = useState([]);
  const [form, setForm] = useState({ memberId: "", catalogueId: "" });

  useEffect(() => {
    loadCirculations();
  }, []);

  async function loadCirculations() {
    setCirculations(await getCirculations());
  }

  async function handleSubmit(e) {
    e.preventDefault();
    const exists = await checkCirculationExists(form.memberId, form.catalogueId);
    if (exists) {
      alert("Already exists!");
      return;
    }
    await addCirculation(form);
    setForm({ memberId: "", catalogueId: "" });
    loadCirculations();
  }

  async function handleReturn(id) {
    await returnCirculation(id, {});
    loadCirculations();
  }

  async function handleDelete(id) {
    await deleteCirculation(id);
    loadCirculations();
  }

  return (
    <div className="page">
      <h2>Circulations</h2>
      <form onSubmit={handleSubmit}>
        <input
          placeholder="Member ID"
          value={form.memberId}
          onChange={(e) => setForm({ ...form, memberId: e.target.value })}
        />
        <input
          placeholder="Catalogue ID"
          value={form.catalogueId}
          onChange={(e) => setForm({ ...form, catalogueId: e.target.value })}
        />
        <button type="submit">Add</button>
      </form>
      <ul>
        {circulations.map((c) => (
          <li key={c.id}>
            {c.memberId} → {c.catalogueId} ({c.status})
            <button onClick={() => handleReturn(c.id)}>Return</button>
            <button onClick={() => handleDelete(c.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
