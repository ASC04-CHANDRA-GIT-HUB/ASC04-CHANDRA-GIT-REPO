import React from 'react';
import { Link } from 'react-router-dom';

const Sidebar = () => (
  <aside className="sidebar shadow-md bg-white min-h-screen p-4">
    <ul className="space-y-3">
      <li><Link to="/dashboard">Dashboard</Link></li>
      <li><Link to="/catalogues">Catalogues</Link></li>
      <li><Link to="/members">Members</Link></li>
      <li><Link to="/circulations">Circulations</Link></li>
      <li><Link to="/acquisitions">Acquisitions</Link></li>
      <li><Link to="/reviews">Reviews</Link></li>
    </ul>
  </aside>
);

export default Sidebar;