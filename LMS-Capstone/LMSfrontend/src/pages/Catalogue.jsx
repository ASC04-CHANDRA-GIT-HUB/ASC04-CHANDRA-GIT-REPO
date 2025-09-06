import { useState, useEffect } from "react";
import {
  addCatalogue,
  updateCatalogue,
  getCatalogues,
  deleteCatalogue,
} from "../services/api.js";

export default function Catalogue() {
  const [catalogues, setCatalogues] = useState([]);
  const [form, setForm] = useState({
    title: "",
    author: "",
    description: "",
    available: true,
    id: null,
  });

  useEffect(() => {
    getCatalogues().then((res) => setCatalogues(res || []));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (form.id) {
      await updateCatalogue(form.id, form);
    } else {
      const { id, ...newData } = form;
      await addCatalogue(newData);
    }

    const updated = await getCatalogues();
    setCatalogues(updated || []);
    setForm({ title: "", author: "", description: "", available: true, id: null });
  };

  const handleDelete = async (id) => {
    if (confirm("Are you sure you want to delete this catalogue?")) {
      await deleteCatalogue(id);
      const updated = await getCatalogues();
      setCatalogues(updated || []);
    }
  };

  return (
    <div
      className="w-screen min-h-screen p-8 text-[#f7f1e1] relative overflow-y-auto"
      style={{
        backgroundImage: `url('https://images.unsplash.com/photo-1512820790803-83ca734da794?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&q=80')`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      {/* Dark overlay */}
      <div className="absolute inset-0 bg-black/70"></div>

      {/* Content */}
      <div className="relative z-10 max-w-6xl mx-auto">
        <h2 className="text-4xl font-serif font-extrabold text-[#ffd700] mb-8 drop-shadow-xl">
          📚 ಪುಸ್ತಕ ನಿರ್ವಹಣೆ (Catalogue Management)
        </h2>

        {/* Form */}
        <form
          onSubmit={handleSubmit}
          className="space-y-5 bg-[#2f261e]/80 backdrop-blur-lg shadow-2xl rounded-3xl p-8 mb-10 border border-[#d4af37]/50"
        >
          <input
            type="text"
            value={form.title}
            onChange={(e) => setForm({ ...form, title: e.target.value })}
            placeholder="ಶೀರ್ಷಿಕೆ (Title)"
            required
            className="w-full border border-[#d4af37]/50 rounded-xl px-4 py-3 bg-[#3e2e1c]/90 text-[#f7f1e1] placeholder-[#d4af37]/70 focus:ring-2 focus:ring-[#ffd700]"
          />

          <input
            type="text"
            value={form.author}
            onChange={(e) => setForm({ ...form, author: e.target.value })}
            placeholder="ಲೇಖಕ (Author)"
            required
            className="w-full border border-[#d4af37]/50 rounded-xl px-4 py-3 bg-[#3e2e1c]/90 text-[#f7f1e1] placeholder-[#d4af37]/70 focus:ring-2 focus:ring-[#ffd700]"
          />

          <textarea
            value={form.description}
            onChange={(e) => setForm({ ...form, description: e.target.value })}
            placeholder="ವಿವರಣೆ (Description)"
            required
            className="w-full border border-[#d4af37]/50 rounded-xl px-4 py-3 bg-[#3e2e1c]/90 text-[#f7f1e1] placeholder-[#d4af37]/70 focus:ring-2 focus:ring-[#ffd700]"
          />

          <label className="flex items-center space-x-3">
            <input
              type="checkbox"
              checked={form.available}
              onChange={(e) => setForm({ ...form, available: e.target.checked })}
              className="h-5 w-5 text-[#ffd700] focus:ring-[#ffd700] border-gray-400 rounded"
            />
            <span className="text-[#d4af37] font-serif text-lg">
              ಲಭ್ಯವಿದೆ (Available)
            </span>
          </label>

          <button
            type="submit"
            className="px-6 py-3 rounded-2xl font-bold bg-gradient-to-r from-[#d4af37] to-[#ffd700] text-[#1c1815] shadow-2xl hover:scale-105 transition-transform duration-300"
          >
            {form.id ? "ಅಪ್‌ಡೇಟ್ (Update)" : "ಸೇರಿಸಿ (Add)"}
          </button>
        </form>

        {/* Catalogue List */}
        <ul className="space-y-6">
          {catalogues?.length ? (
            catalogues.map((c) => (
              <li
                key={c.id}
                className="border border-[#d4af37]/50 p-6 rounded-3xl shadow-2xl bg-[#2f261e]/80 backdrop-blur-lg flex justify-between items-start transition-transform hover:scale-102"
              >
                <div className="space-y-2">
                  <h3 className="text-xl font-serif font-bold text-[#ffd700]">
                    {c.title}{" "}
                    <span className="text-[#d4af37] font-normal">
                      - {c.author}
                    </span>
                  </h3>
                  <p className="italic text-[#f7f1e1]/90">{c.description}</p>
                  <p className="mt-1">
                    {c.available ? (
                      <span className="px-3 py-1 rounded-full text-sm font-medium bg-green-700/40 text-green-200">
                        ✅ ಲಭ್ಯವಿದೆ (Available)
                      </span>
                    ) : (
                      <span className="px-3 py-1 rounded-full text-sm font-medium bg-red-700/40 text-red-200">
                        ❌ ಲಭ್ಯವಿಲ್ಲ (Not Available)
                      </span>
                    )}
                  </p>
                </div>

                <div className="flex space-x-3">
                  <button
                    onClick={() => setForm(c)}
                    className="px-4 py-2 rounded-2xl font-semibold bg-gradient-to-r from-[#d4af37] to-[#ffd700] text-[#1c1815] shadow-lg hover:scale-105 transition-transform duration-200"
                  >
                    ಸಂಪಾದಿಸಿ (Edit)
                  </button>
                  <button
                    onClick={() => handleDelete(c.id)}
                    className="px-4 py-2 rounded-2xl font-semibold bg-red-700/80 text-[#f7f1e1] shadow-lg hover:scale-105 transition-transform duration-200"
                  >
                    ಅಳಿಸಿ (Delete)
                  </button>
                </div>
              </li>
            ))
          ) : (
            <p className="text-[#d4af37] text-lg font-serif">
              ಲಭ್ಯವಿರುವ ಪುಸ್ತಕಗಳು ಇಲ್ಲ (No catalogues available)
            </p>
          )}
        </ul>
      </div>
    </div>
  );
}
