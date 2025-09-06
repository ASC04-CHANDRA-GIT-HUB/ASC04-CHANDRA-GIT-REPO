import React, { useState } from "react";
import { register } from "../services/api";

export default function Register() {
  const [form, setForm] = useState({ name: "", email: "", password: "" });

  async function handleSubmit(e) {
    e.preventDefault();
    const res = await register(form);
    alert(res.message || "Registration success!");
  }

  return (
    <div className="page">
      <h2>Register</h2>
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
        <input
          type="password"
          placeholder="Password"
          value={form.password}
          onChange={(e) => setForm({ ...form, password: e.target.value })}
        />
        <button type="submit">Register</button>
      </form>
    </div>
  );
}
