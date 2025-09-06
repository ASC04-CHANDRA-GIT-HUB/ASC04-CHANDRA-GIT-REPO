// src/pages/Members.jsx
import { useEffect, useState } from "react";
import {
  getMembers,
  addMember,
  updateMember,
  deleteMember,
} from "../services/api.js";

export default function Members() {
  const [members, setMembers] = useState([]);
  const [newMember, setNewMember] = useState({ name: "", email: "", phone: "" });
  const [editingMember, setEditingMember] = useState(null);

  useEffect(() => {
    fetchMembers();
  }, []);

  const fetchMembers = async () => {
    const data = await getMembers();
    setMembers(data);
  };

  const handleAdd = async (e) => {
    e.preventDefault();
    if (!newMember.name || !newMember.email || !newMember.phone) return;

    await addMember(newMember);
    setNewMember({ name: "", email: "", phone: "" }); // reset form
    fetchMembers();
  };

  const handleUpdate = async (id) => {
    await updateMember(id, editingMember);
    setEditingMember(null);
    fetchMembers();
  };

  const handleDelete = async (id) => {
    await deleteMember(id);
    fetchMembers();
  };

  return (
    <div
      className="flex flex-col items-center min-h-screen bg-cover bg-center py-16"
      style={{
        backgroundImage: `url('https://images.unsplash.com/photo-1524578271613-d550eacf6090?auto=format&fit=crop&w=1950&q=80')`,
        backgroundColor: "#1e1a18",
        backgroundBlendMode: "multiply",
      }}
    >
      <div className="bg-[#2c241f]/80 p-10 rounded-3xl shadow-2xl w-full max-w-3xl text-yellow-300">
        <h2 className="text-3xl font-serif-extrabold text-yellow-400 text-center mb-8 drop-shadow-lg">
          👥 ಸದಸ್ಯರು (Members)
        </h2>

        {/* Add Member Form */}
        <form
          onSubmit={handleAdd}
          className="mb-8 flex flex-wrap gap-3 justify-center"
        >
          <input
            type="text"
            placeholder="👤 ಹೆಸರು (Name)"
            value={newMember.name}
            onChange={(e) => setNewMember({ ...newMember, name: e.target.value })}
            className="border border-yellow-600 bg-[#3e2c1c]/50 text-yellow-200 p-3 rounded-xl focus:ring-2 focus:ring-yellow-400 outline-none"
          />
          <input
            type="email"
            placeholder="📧 ಇಮೇಲ್ (Email)"
            value={newMember.email}
            onChange={(e) => setNewMember({ ...newMember, email: e.target.value })}
            className="border border-yellow-600 bg-[#3e2c1c]/50 text-yellow-200 p-3 rounded-xl focus:ring-2 focus:ring-yellow-400 outline-none"
          />
          <input
            type="text"
            placeholder="📞 ಫೋನ್ (Phone)"
            value={newMember.phone}
            onChange={(e) => setNewMember({ ...newMember, phone: e.target.value })}
            className="border border-yellow-600 bg-[#3e2c1c]/50 text-yellow-200 p-3 rounded-xl focus:ring-2 focus:ring-yellow-400 outline-none"
          />
          <button
            type="submit"
            className="bg-yellow-500 text-[#2c241f] px-5 py-2 rounded-xl font-semibold hover:bg-yellow-600 transition"
          >
            ➕ ಸೇರಿಸಿ (Add)
          </button>
        </form>

        {/* Members List */}
        <ul className="space-y-4">
          {members.map((m) => (
            <li
              key={m.id}
              className="flex flex-col md:flex-row md:justify-between md:items-center bg-[#3e2c1c]/70 border border-yellow-600 p-5 rounded-2xl shadow-md"
            >
              {editingMember?.id === m.id ? (
                <div className="flex flex-wrap gap-2">
                  <input
                    type="text"
                    value={editingMember.name}
                    onChange={(e) =>
                      setEditingMember({ ...editingMember, name: e.target.value })
                    }
                    className="border border-yellow-600 bg-[#2c241f]/50 text-yellow-200 p-2 rounded-md"
                  />
                  <input
                    type="email"
                    value={editingMember.email}
                    onChange={(e) =>
                      setEditingMember({ ...editingMember, email: e.target.value })
                    }
                    className="border border-yellow-600 bg-[#2c241f]/50 text-yellow-200 p-2 rounded-md"
                  />
                  <input
                    type="text"
                    value={editingMember.phone}
                    onChange={(e) =>
                      setEditingMember({ ...editingMember, phone: e.target.value })
                    }
                    className="border border-yellow-600 bg-[#2c241f]/50 text-yellow-200 p-2 rounded-md"
                  />
                  <button
                    onClick={() => handleUpdate(m.id)}
                    className="bg-green-500 text-[#2c241f] px-4 py-2 rounded-md font-semibold hover:bg-green-600 transition"
                  >
                    💾 ಉಳಿಸಿ (Save)
                  </button>
                </div>
              ) : (
                <>
                  <span className="text-yellow-200 font-semibold">
                    👤 {m.name} | 📧 {m.email} | 📞 {m.phone}
                  </span>
                  <div className="space-x-2 mt-2 md:mt-0">
                    <button
                      onClick={() => setEditingMember(m)}
                      className="bg-yellow-500 text-[#2c241f] px-3 py-1 rounded-lg font-semibold hover:bg-yellow-600 transition"
                    >
                      ✏️ ತಿದ್ದು (Edit)
                    </button>
                    <button
                      onClick={() => handleDelete(m.id)}
                      className="bg-red-500 text-white px-3 py-1 rounded-lg font-semibold hover:bg-red-600 transition"
                    >
                      🗑️ ಅಳಿಸಿ (Delete)
                    </button>
                  </div>
                </>
              )}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
