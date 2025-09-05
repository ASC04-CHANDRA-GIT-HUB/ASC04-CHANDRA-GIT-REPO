import React, { useState } from 'react';
import { register } from '../services/authService';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';

const Register = () => {
  const [form, setForm] = useState({ name: '', email: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleRegister = async (e) => {
    e.preventDefault();
    const res = await register(form);
    console.log(res);
    navigate('/login');
  };

  return (
    <>
      <Navbar />
      <div className="p-8 max-w-md mx-auto">
        <h2 className="text-2xl mb-4">Register</h2>
        <form onSubmit={handleRegister} className="space-y-3">
          <input type="text" name="name" placeholder="Name" value={form.name} onChange={handleChange} required />
          <input type="email" name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
          <input type="password" name="password" placeholder="Password" value={form.password} onChange={handleChange} required />
          <button type="submit">Register!</button>
        </form>
      </div>
    </>
  );
};

export default Register;