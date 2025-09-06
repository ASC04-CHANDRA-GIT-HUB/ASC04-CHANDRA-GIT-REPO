import { useState, useEffect } from "react";
import {
  addAcquisition,
  updateAcquisition,
  getAcquisitions,
  deleteAcquisition,
} from "../services/api.js";

export default function Acquisition() {
  const [acquisitions, setAcquisitions] = useState([]);
  const [form, setForm] = useState({
    title: "",
    author: "",
    supplier: "",
    status: "REQUESTED",
    description: "",
    id: null,
  });

  useEffect(() => {
    getAcquisitions().then(setAcquisitions);
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (form.id) {
      await updateAcquisition(form.id, form);
    } else {
      const { id, ...newData } = form;
      await addAcquisition(newData);
    }

    getAcquisitions().then(setAcquisitions);
    setForm({
      title: "",
      author: "",
      supplier: "",
      status: "REQUESTED",
      description: "",
      id: null,
    });
  };

  const handleDelete = async (id) => {
    if (confirm("Are you sure you want to delete this acquisition?")) {
      await deleteAcquisition(id);
      getAcquisitions().then(setAcquisitions);
    }
  };

  return (
    <div
      className="min-h-screen w-full relative"
      style={{
        backgroundImage:
          "url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=1950&q=80')",
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundAttachment: "fixed",
      }}
    >
      {/* Overlay */}
      <div className="absolute inset-0 bg-black/60"></div>

      {/* Content */}
      <div className="relative z-10 max-w-5xl mx-auto p-6 text-[#f8f3e9] font-serif flex flex-col items-center">
        {/* Page Heading */}
        <h2 className="text-3xl md:text-4xl font-extrabold text-[#ffd700] mb-10 tracking-wide drop-shadow-lg">
          🛒 ಅರ್ಜಿ ನಿರ್ವಹಣೆ (Acquisition Management)
        </h2>

        {/* Form */}
        <form
          onSubmit={handleSubmit}
          className="w-full md:w-3/4 space-y-5 bg-[#2c241f]/95 border border-[#bfa46f] rounded-2xl p-8 shadow-2xl"
        >
          <input
            type="text"
            value={form.title}
            onChange={(e) => setForm({ ...form, title: e.target.value })}
            placeholder="ಶೀರ್ಷಿಕೆ (Title)"
            required
            className="w-full border border-[#bfa46f] rounded-lg px-4 py-3 bg-[#3e2c1c] text-[#f8f3e9] placeholder-[#d4af37] focus:outline-none focus:ring-2 focus:ring-[#ffd700] focus:border-[#ffd700] transition"
          />
          <input
            type="text"
            value={form.author}
            onChange={(e) => setForm({ ...form, author: e.target.value })}
            placeholder="ಲೇಖಕ (Author)"
            required
            className="w-full border border-[#bfa46f] rounded-lg px-4 py-3 bg-[#3e2c1c] text-[#f8f3e9] placeholder-[#d4af37] focus:outline-none focus:ring-2 focus:ring-[#ffd700] focus:border-[#ffd700] transition"
          />
          <input
            type="text"
            value={form.supplier}
            onChange={(e) => setForm({ ...form, supplier: e.target.value })}
            placeholder="ನೀಡಿದವರು (Supplier)"
            required
            className="w-full border border-[#bfa46f] rounded-lg px-4 py-3 bg-[#3e2c1c] text-[#f8f3e9] placeholder-[#d4af37] focus:outline-none focus:ring-2 focus:ring-[#ffd700] focus:border-[#ffd700] transition"
          />
          <select
            value={form.status}
            onChange={(e) => setForm({ ...form, status: e.target.value })}
            className="w-full border border-[#bfa46f] rounded-lg px-4 py-3 bg-[#3e2c1c] text-[#f8f3e9] focus:outline-none focus:ring-2 focus:ring-[#ffd700] focus:border-[#ffd700] transition"
          >
            <option value="REQUESTED">ಕೋರಿಕೆ (Requested)</option>
            <option value="RECEIVED">ಸ್ವೀಕೃತ (Received)</option>
          </select>
          <textarea
            value={form.description}
            onChange={(e) => setForm({ ...form, description: e.target.value })}
            placeholder="ವಿವರಣೆ (Description)"
            required
            className="w-full border border-[#bfa46f] rounded-lg px-4 py-3 bg-[#3e2c1c] text-[#f8f3e9] placeholder-[#d4af37] focus:outline-none focus:ring-2 focus:ring-[#ffd700] focus:border-[#ffd700] transition resize-y"
          />

          <button
            type="submit"
            className="bg-gradient-to-r from-[#5b452a] to-[#3e2c1c] border-2 border-[#ffd700] text-[#fdf5e6] font-bold px-6 py-3 rounded-xl shadow-xl hover:from-[#3e2c1c] hover:to-[#5b452a] hover:shadow-2xl transition-all"
          >
            {form.id ? "ನವೀಕರಿಸಿ (Update)" : "➕ ಸೇರಿಸಿ (Add)"}
          </button>
        </form>

        {/* Acquisition List */}
        <ul className="w-full md:w-3/4 mt-10 space-y-6">
          {acquisitions.map((a) => (
            <li
              key={a.id}
              className="border border-[#bfa46f] p-6 rounded-2xl shadow-2xl bg-[#2c241f]/95 flex flex-col md:flex-row justify-between items-start transition hover:shadow-3xl hover:scale-105"
            >
              <div>
                <h3 className="text-xl md:text-2xl font-bold text-[#ffd700] mb-1">
                  {a.title}{" "}
                  <span className="text-[#d4af37] font-medium">- {a.author}</span>
                </h3>
                <p className="text-[#f8f3e9] mb-1 font-medium">
                  ಒದಗಿಸಿದವರು: {a.supplier} (Supplier)
                </p>
                <p className="italic text-[#d4af37] mb-2">{a.description}</p>
                <p>
                  <span className="px-3 py-1 rounded-full text-sm font-semibold bg-[#5b452a] text-[#ffd700]">
                    {a.status === "REQUESTED"
                      ? "ಕೋರಿಕೆ (Requested)"
                      : "ಸ್ವೀಕೃತ (Received)"}
                  </span>
                </p>
              </div>

              <div className="mt-4 md:mt-0 space-x-3 flex">
                <button
                  onClick={() => setForm(a)}
                  className="bg-[#bfa46f] text-[#1e1a18] font-bold px-4 py-2 rounded-lg shadow hover:bg-[#ffd700] hover:text-[#1e1a18] transition"
                >
                  ಸಂಪಾದನೆ (Edit)
                </button>
                <button
                  onClick={() => handleDelete(a.id)}
                  className="bg-[#c0392b] text-[#fdf5e6] font-bold px-4 py-2 rounded-lg shadow hover:bg-[#e74c3c] transition"
                >
                  ಅಳಿಸಿ (Delete)
                </button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
