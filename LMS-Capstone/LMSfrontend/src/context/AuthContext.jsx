import React, { createContext, useContext, useEffect, useState } from 'react'
import api from '../api/client'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [token, setToken] = useState(() => localStorage.getItem('jwt'))
  const [user, setUser] = useState(null)

  const login = async (email, password) => {
    const { data } = await api.post('/auth/login', { email, password })
    const t = data.token || data.jwt || data.accessToken || data.access_token
    if (t) {
      localStorage.setItem('jwt', t)
      setToken(t)
    }
    setUser(data.user || null)
    return data
  }

  const register = async (payload) => {
    const { data } = await api.post('/auth/register', payload)
    return data
  }

  const logout = () => {
    localStorage.removeItem('jwt')
    setToken(null)
    setUser(null)
  }

  return (
    <AuthContext.Provider value={{ token, user, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext)
