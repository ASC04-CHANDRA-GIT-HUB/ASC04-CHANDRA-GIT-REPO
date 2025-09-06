import { useState, useEffect } from "react";
import { addCirculation, getCirculations, returnCirculation } from "../services/api.js";

export default function Circulation() {
  const [circulations, setCirculations] = useState([]);
  const [form, setForm] = useState({ memberId: "", catalogueId: "" });

  useEffect(() => {
    getCirculations().then(setCirculations);
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    await addCirculation(form);
    getCirculations().then(setCirculations);
    setForm({ memberId: "", catalogueId: "" });
  };

  const handleReturn = async (id) => {
    await returnCirculation(id, { returned: true });
    getCirculations().then(setCirculations);
  };

  return (
    <div
      className="flex flex-col items-center min-h-screen bg-cover bg-center py-16"
      style={{
        backgroundImage: `url('https://images.unsplash.com/photo-1507842217343-583bb7270b66?auto=format&fit=crop&w=1950&q=80')`,
        backgroundColor: '#1e1a18',
        backgroundBlendMode: 'multiply',
      }}
    >
      <div className="bg-[#2c241f]/80 p-10 rounded-3xl shadow-2xl w-full max-w-3xl text-yellow-300">
        <h2 className="text-3xl font-serif-extrabold text-yellow-400 text-center mb-8 drop-shadow-lg">
          📚 ಪ್ರವಾಹಗಳು (Circulations)
        </h2>

        {/* Form */}
        <form onSubmit={handleSubmit} className="flex flex-col gap-4 mb-8">
          <input
            type="text"
            value={form.memberId}
            onChange={(e) => setForm({ ...form, memberId: e.target.value })}
            placeholder="👤 ಸದಸ್ಯ ಐಡಿ (Member ID, e.g., M0001)"
            className="border border-yellow-600 bg-[#3e2c1c]/50 text-yellow-200 p-3 rounded-xl focus:ring-2 focus:ring-yellow-400 outline-none"
            required
          />
          <input
            type="text"
            value={form.catalogueId}
            onChange={(e) => setForm({ ...form, catalogueId: e.target.value })}
            placeholder="📖 ಸೂಚಿಪತ್ರ ಐಡಿ (Catalogue ID, e.g., C0001)"
            className="border border-yellow-600 bg-[#3e2c1c]/50 text-yellow-200 p-3 rounded-xl focus:ring-2 focus:ring-yellow-400 outline-none"
            required
          />
          <button
            type="submit"
            className="bg-yellow-500 text-[#2c241f] py-3 rounded-xl font-semibold hover:bg-yellow-600 transition"
          >
            ➕ ಪ್ರವಾಹ ಸೇರಿಸಿ (Add Circulation)
          </button>
        </form>

        {/* List */}
        <ul className="space-y-4">
          {circulations.map((c) => (
            <li
              key={c.id}
              className="flex justify-between items-center bg-[#3e2c1c]/70 border border-yellow-600 p-5 rounded-2xl shadow-md"
            >
              <div>
                <p className="font-semibold text-yellow-200">
                  👤 ಸದಸ್ಯ (Member): {c.member_id || c.memberId}
                </p>
                <p className="text-yellow-300">
                  📖 ಸೂಚಿಪತ್ರ (Catalogue): {c.catalogue_id || c.catalogueId}
                </p>
                <p className="text-sm text-yellow-400 mt-1">
                  {c.returned ? "✅ ಮರಳಿಸಲಾಗಿದೆ (Returned)" : "📚 ಸಾಲದಲ್ಲಿದೆ (On Loan)"}
                </p>
              </div>
              {!c.returned && (
                <button
                  onClick={() => handleReturn(c.id)}
                  className="bg-green-500 text-[#2c241f] px-4 py-2 rounded-xl font-semibold hover:bg-green-600 transition"
                >
                  🔄 ಮರಳಿ (Return)
                </button>
              )}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
