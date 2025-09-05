import React from 'react';
import Navbar from '../components/Navbar';
import Sidebar from '../components/Sidebar';

const Dashboard = () => (
  <div className="flex">
    <Sidebar />
    <main className="flex-1 p-6">
      <Navbar />
      <h1 className="text-3xl font-bold mb-4">Dashboard</h1>
      <p>Welcome to the LMS Dashboard!</p>
    </main>
  </div>
);

export default Dashboard;