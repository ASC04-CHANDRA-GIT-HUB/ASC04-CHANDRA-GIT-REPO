// src/pages/Login.jsx
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../services/api.js";

export default function Login() {
  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const res = await login(form.email, form.password);

      if (res.message === "Login Successful") {
        localStorage.setItem("isAuthenticated", "true");
        navigate("/dashboard"); // redirect
      } else {
        setError(res.message || "ಅಮಾನ್ಯ ಪ್ರಮಾಣಪತ್ರಗಳು (Invalid credentials)");
      }
    } catch (err) {
      setError("ಲಾಗಿನ್ ವಿಫಲವಾಗಿದೆ. ದಯವಿಟ್ಟು ಮರುಪ್ರಯತ್ನಿಸಿ (Login failed. Please try again.)");
    }
  };

  return (
    <div
      className="flex items-center justify-center min-h-screen text-[#f7f1e1] relative"
      style={{
        backgroundImage: `url('https://images.unsplash.com/photo-1755675673216-6195d954ef7f?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D')`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      {/* Overlay */}
      <div className="absolute inset-0 bg-black/70"></div>

      {/* Login Card */}
      <div className="relative z-10 w-full max-w-md bg-[#2f261e]/90 border border-[#d4af37]/60 rounded-3xl shadow-2xl p-10">
        <h2 className="text-3xl font-serif font-extrabold text-center text-[#ffd700] mb-6 drop-shadow">
          ಪ್ರವೇಶ
        </h2>

        <form onSubmit={handleSubmit} className="space-y-5">
          <input
            type="email"
            value={form.email}
            onChange={(e) => setForm({ ...form, email: e.target.value })}
            placeholder="ಇಮೇಲ್ (Email)"
            required
            className="w-full px-4 py-3 border border-[#d4af37]/50 rounded-xl bg-[#3e2e1c]/80 text-[#f7f1e1] placeholder-[#d4af37]/70 focus:ring-2 focus:ring-[#ffd700] focus:outline-none"
          />
          <input
            type="password"
            value={form.password}
            onChange={(e) => setForm({ ...form, password: e.target.value })}
            placeholder="ಪಾಸ್‌ವರ್ಡ್ (Password)"
            required
            className="w-full px-4 py-3 border border-[#d4af37]/50 rounded-xl bg-[#3e2e1c]/80 text-[#f7f1e1] placeholder-[#d4af37]/70 focus:ring-2 focus:ring-[#ffd700] focus:outline-none"
          />
          <button
            type="submit"
            className="w-full bg-gradient-to-r from-[#d4af37] to-[#ffd700] text-[#1c1815] font-bold py-3 rounded-xl shadow-lg hover:scale-105 transition-transform"
          >

⏎ ಲಾಗಿನ್ (Login)
          </button>
        </form>

        {error && (
          <p className="mt-4 text-center text-sm text-red-400 font-serif">{error}</p>
        )}
      </div>
    </div>
  );
}
