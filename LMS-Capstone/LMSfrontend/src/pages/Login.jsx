import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'

export default function Login() {
  const { login } = useAuth()
  const nav = useNavigate()
  const [email, setEmail] = useState('admin@example.com')
  const [password, setPassword] = useState('password')
  const [msg, setMsg] = useState(null)

  const onSubmit = async (e) => {
    e.preventDefault()
    try {
      const res = await login(email, password)
      setMsg(JSON.stringify(res, null, 2))
      nav('/')
    } catch (err) {
      setMsg(err.response?.data ? JSON.stringify(err.response.data) : String(err))
    }
  }

  return (
    <div className="container">
      <div className="card" style={{maxWidth:480, margin:'0 auto'}}>
        <h2>Login</h2>
        <form onSubmit={onSubmit}>
          <div className="space" />
          <input className="input" value={email} onChange={e=>setEmail(e.target.value)} placeholder="Email" />
          <div className="space" />
          <input className="input" value={password} onChange={e=>setPassword(e.target.value)} type="password" placeholder="Password" />
          <div className="space" />
          <button className="btn" type="submit">Sign in</button>
        </form>
        {msg && (<>
          <div className="space" />
          <pre style={{whiteSpace:'pre-wrap'}} className="muted">{msg}</pre>
        </>)}
      </div>
    </div>
  )
}
