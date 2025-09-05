import React, { useState } from 'react';
import { login } from '../services/authService';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    const res = await login(email, password);
    console.log(res);
    navigate('/dashboard');
  };

  return (
    <>
      <Navbar />
      <div className="p-8 max-w-md mx-auto">
        <h2 className="text-2xl mb-4">Login</h2>
        <form onSubmit={handleLogin} className="space-y-3">
          <input type="email" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} required />
          <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} required />
          <button type="submit">Login!</button>
        </form>
      </div>
    </>
  );
};

export default Login;