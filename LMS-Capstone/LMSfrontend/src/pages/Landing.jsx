import React from 'react';
import Navbar from '../components/Navbar';

const Landing = () => (
  <>
    <Navbar />
    <div className="p-8 text-center">
      <h1 className="text-4xl font-bold mb-4">Welcome to the house of knowledge!</h1>
      <p>Knowledge at your finger tips.</p>
    </div>
  </>
);

export default Landing;