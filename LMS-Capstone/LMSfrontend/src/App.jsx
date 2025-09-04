import React from 'react'
import { NavLink, Route, Routes } from 'react-router-dom'
import { AuthProvider, useAuth } from './context/AuthContext'
import Login from './pages/Login'
import Register from './pages/Register'
import Catalogues from './pages/Catalogues'
import CatalogueDetail from './pages/CatalogueDetail'
import Members from './pages/Members'
import MemberDetail from './pages/MemberDetail'
import Circulations from './pages/Circulations'
import Reviews from './pages/Reviews'
import Acquisitions from './pages/Acquisitions'
import ProtectedRoute from './components/ProtectedRoute'

function Nav() {
  const { token, logout } = useAuth()
  return (
    <div className="nav">
      <NavLink to="/" end className={({isActive}) => isActive ? 'active' : ''}>Home</NavLink>
      <NavLink to="/catalogues" className={({isActive}) => isActive ? 'active' : ''}>Catalogue</NavLink>
      <NavLink to="/members" className={({isActive}) => isActive ? 'active' : ''}>Members</NavLink>
      <NavLink to="/circulations" className={({isActive}) => isActive ? 'active' : ''}>Circulations</NavLink>
      <NavLink to="/reviews" className={({isActive}) => isActive ? 'active' : ''}>Reviews</NavLink>
      <NavLink to="/acquisitions" className={({isActive}) => isActive ? 'active' : ''}>Acquisitions</NavLink>
      <div style={{marginLeft:'auto'}} />
      {token ? (
        <button className="btn" onClick={logout}>Logout</button>
      ) : (
        <>
          <NavLink to="/login" className={({isActive}) => isActive ? 'active' : ''}>Login</NavLink>
          <NavLink to="/register" className={({isActive}) => isActive ? 'active' : ''}>Register</NavLink>
        </>
      )}
    </div>
  )
}

function Home() {
  return (
    <div className="container">
      <div className="card">
        <h1>📚 Library Management System Frontend</h1>
        <p className="muted">API base: <span className="accent">{import.meta.env.VITE_API_BASE || 'http://localhost:8080'}</span></p>
        <p>Use the navigation above to browse services. Endpoints are routed via API Gateway (Spring Cloud) to microservices.</p>
      </div>
    </div>
  )
}

export default function App() {
  return (
    <AuthProvider>
      <Nav />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/catalogues" element={<Catalogues />} />
        <Route path="/catalogues/:id" element={<CatalogueDetail />} />
        <Route path="/members" element={<Members />} />
        <Route path="/members/:id" element={<MemberDetail />} />
        <Route path="/circulations" element={<Circulations />} />
        <Route path="/reviews" element={<Reviews />} />
        <Route path="/acquisitions" element={
          <ProtectedRoute>
            <Acquisitions />
          </ProtectedRoute>
        } />
      </Routes>
    </AuthProvider>
  )
}
