// src/pages/Reviews.jsx
import React, { useEffect, useState } from "react";
import {
  getReviews,
  addReview,
  updateReview,
  deleteReview,
} from "../services/api";

export default function Reviews() {
  const [reviews, setReviews] = useState([]);
  const [form, setForm] = useState({
    member_id: "",
    catalogue_id: "",
    comment: "",
    rating: "",
    id: null,
  });

  useEffect(() => {
    loadReviews();
  }, []);

  async function loadReviews() {
    setReviews(await getReviews());
  }

  async function handleSubmit(e) {
    e.preventDefault();

    if (form.id) {
      await updateReview(form.id, form);
    } else {
      const { id, ...newData } = form;
      await addReview(newData);
    }

    setForm({ member_id: "", catalogue_id: "", comment: "", rating: "", id: null });
    loadReviews();
  }

  async function handleDelete(id) {
    await deleteReview(id);
    loadReviews();
  }

  return (
    <div
      className="flex flex-col items-center min-h-screen bg-cover bg-center py-16"
      style={{
        backgroundImage: `url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1950&q=80')`,
        backgroundColor: "#1e1a18",
        backgroundBlendMode: "multiply",
      }}
    >
      <div className="bg-[#2c241f]/80 p-10 rounded-3xl shadow-2xl w-full max-w-3xl text-yellow-300">
        <h2 className="text-3xl font-serif-extrabold text-yellow-400 text-center mb-8 drop-shadow-lg">
          ⭐ ವಿಮರ್ಶೆಗಳು (Reviews)
        </h2>

        {/* Form */}
        <form
          onSubmit={handleSubmit}
          className="space-y-4 bg-[#3e2c1c]/60 shadow-inner rounded-2xl p-6 mb-8"
        >
          <input
            type="text"
            value={form.member_id}
            onChange={(e) => setForm({ ...form, member_id: e.target.value })}
            placeholder="👤 ಸದಸ್ಯ ಐಡಿ (Member ID: M0003)"
            required
            className="w-full border border-yellow-600 bg-[#2c241f]/50 text-yellow-200 px-3 py-2 rounded-lg focus:ring-2 focus:ring-yellow-400 outline-none"
          />

          <input
            type="text"
            value={form.catalogue_id}
            onChange={(e) => setForm({ ...form, catalogue_id: e.target.value })}
            placeholder="📖 ಸೂಚಿಪತ್ರ ಐಡಿ (Catalogue ID: C0004)"
            required
            className="w-full border border-yellow-600 bg-[#2c241f]/50 text-yellow-200 px-3 py-2 rounded-lg focus:ring-2 focus:ring-yellow-400 outline-none"
          />

          <textarea
            value={form.comment}
            onChange={(e) => setForm({ ...form, comment: e.target.value })}
            placeholder="✍️ ನಿಮ್ಮ ವಿಮರ್ಶೆ ಬರೆಯಿರಿ (Write your review)"
            required
            className="w-full border border-yellow-600 bg-[#2c241f]/50 text-yellow-200 px-3 py-2 rounded-lg focus:ring-2 focus:ring-yellow-400 outline-none"
          />

          <input
            type="number"
            step="0.1"
            value={form.rating}
            onChange={(e) => setForm({ ...form, rating: e.target.value })}
            placeholder="⭐ ಅಂಕ (Rating: 1-5)"
            min="1"
            max="5"
            required
            className="w-full border border-yellow-600 bg-[#2c241f]/50 text-yellow-200 px-3 py-2 rounded-lg focus:ring-2 focus:ring-yellow-400 outline-none"
          />

          <button
            type="submit"
            className="bg-yellow-500 text-[#2c241f] px-6 py-2 rounded-xl font-semibold hover:bg-yellow-600 transition"
          >
            {form.id ? "🔄 ನವೀಕರಿಸಿ (Update)" : "➕ ಸೇರಿಸಿ (Add Review)"}
          </button>
        </form>

        {/* Review List */}
        <ul className="space-y-4">
          {reviews.map((r) => (
            <li
              key={r.id}
              className="border border-yellow-600 bg-[#3e2c1c]/70 p-5 rounded-2xl shadow-md"
            >
              <p className="text-yellow-200 italic">“{r.comment}”</p>
              <p className="mt-2 text-sm text-yellow-300">
                👤 ಸದಸ್ಯ (Member): <span className="font-medium">{r.member_id}</span> | 📖 ಸೂಚಿಪತ್ರ (Catalogue):{" "}
                <span className="font-medium">{r.catalogue_id}</span>
              </p>
              <p className="text-yellow-400 font-semibold mt-1">⭐ {r.rating}</p>
              <div className="space-x-2 mt-3">
                <button
                  onClick={() => setForm(r)}
                  className="bg-yellow-500 text-[#2c241f] px-3 py-1 rounded-lg font-semibold hover:bg-yellow-600 transition"
                >
                  ✏️ ತಿದ್ದು (Edit)
                </button>
                <button
                  onClick={() => handleDelete(r.id)}
                  className="bg-red-500 text-white px-3 py-1 rounded-lg font-semibold hover:bg-red-600 transition"
                >
                  🗑️ ಅಳಿಸಿ (Delete)
                </button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
