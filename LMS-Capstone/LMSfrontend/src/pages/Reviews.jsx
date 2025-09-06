import React, { useEffect, useState } from "react";
import {
  getReviews,
  addReview,
  updateReview,
  deleteReview,
} from "../services/api";

export default function Reviews() {
  const [reviews, setReviews] = useState([]);
  const [form, setForm] = useState({ memberId: "", catalogueId: "", text: "" });

  useEffect(() => {
    loadReviews();
  }, []);

  async function loadReviews() {
    setReviews(await getReviews());
  }

  async function handleSubmit(e) {
    e.preventDefault();
    await addReview(form);
    setForm({ memberId: "", catalogueId: "", text: "" });
    loadReviews();
  }

  async function handleDelete(id) {
    await deleteReview(id);
    loadReviews();
  }

  return (
    <div className="page">
      <h2>Reviews</h2>
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
        <input
          placeholder="Review text"
          value={form.text}
          onChange={(e) => setForm({ ...form, text: e.target.value })}
        />
        <button type="submit">Add</button>
      </form>
      <ul>
        {reviews.map((r) => (
          <li key={r.id}>
            {r.text} (Member {r.memberId} on Catalogue {r.catalogueId})
            <button onClick={() => handleDelete(r.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
