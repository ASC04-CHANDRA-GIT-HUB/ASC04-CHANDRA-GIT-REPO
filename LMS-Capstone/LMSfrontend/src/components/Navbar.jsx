import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => (
  <nav className="navbar shadow-md bg-white px-6 py-4 flex justify-between items-center">
    <div className="text-xl font-bold">LMS</div>
    <div className="flex space-x-4">
      <Link to="/dashboard">Dashboard</Link>
      <Link to="/catalogues">Catalogues</Link>
      <Link to="/members">Members</Link>
      <Link to="/circulations">Circulations</Link>
      <Link to="/acquisitions">Acquisitions</Link>
      <Link to="/reviews">Reviews</Link>
      <Link to="/login" className="text-red-600">Login</Link>
    </div>
  </nav>
);

export default Navbar;