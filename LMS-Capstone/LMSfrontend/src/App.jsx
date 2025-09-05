import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Landing from './pages/Landing';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import Catalogues from './pages/Catalogues';
import Members from './pages/Members';
import Circulations from './pages/Circulations';
import Acquisitions from './pages/Acquisitions';
import Reviews from './pages/Reviews';

const App = () => (
    <Routes>
      <Route path="/" element={<Landing />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/catalogues" element={<Catalogues />} />
      <Route path="/members" element={<Members />} />
      <Route path="/circulations" element={<Circulations />} />
      <Route path="/acquisitions" element={<Acquisitions />} />
      <Route path="/reviews" element={<Reviews />} />
    </Routes>
);

export default App;