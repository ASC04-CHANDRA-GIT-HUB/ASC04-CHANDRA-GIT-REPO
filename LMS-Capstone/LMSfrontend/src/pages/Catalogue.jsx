import React, { useEffect, useState } from "react";
import {
  getCatalogues,
  addCatalogue,
  updateCatalogue,
  deleteCatalogue,
  updateCatalogueRating,
} from "../services/api";

export default function Catalogue() {
  const [catalogues, setCatalogues] = useState([]);
  const [form, setForm] = useState({ title: "", author: "", description: "" });

  useEffect(() => {
    loadCatalogues();
  }, []);

  async function loadCatalogues() {
    setCatalogues(await getCatalogues());
  }

  async function handleSubmit(e) {
    e.preventDefault();
    await addCatalogue(form);
    setForm({ title: "", author: "", description: "" });
    loadCatalogues();
  }

  async function handleDelete(id) {
    await deleteCatalogue(id);
    loadCatalogues();
  }

  async function handleRating(id) {
    const rating = prompt("Enter new rating (0-5):");
    if (rating) {
      await updateCatalogueRating(id, rating);
      loadCatalogues();
    }
  }

  return (
    <div className="page">
      <h2>Catalogues</h2>
      <form onSubmit={handleSubmit}>
        <input
          placeholder="Title"
          value={form.title}
          onChange={(e) => setForm({ ...form, title: e.target.value })}
        />
        <input
          placeholder="Author"
          value={form.author}
          onChange={(e) => setForm({ ...form, author: e.target.value })}
        />
        <input
          placeholder="Description"
          value={form.description}
          onChange={(e) => setForm({ ...form, description: e.target.value })}
        />
        <button type="submit">Add</button>
      </form>
      <ul>
        {catalogues.map((c) => (
          <li key={c.id}>
            {c.title} by {c.author} ({c.rating || "No rating"})
            <button onClick={() => handleRating(c.id)}>Update Rating</button>
            <button onClick={() => handleDelete(c.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
