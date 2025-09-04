import React, { useState } from 'react'
import { useAuth } from '../context/AuthContext'

export default function Register() {
  const { register } = useAuth()
  const [form, setForm] = useState({ name:'Admin', email:'admin@example.com', password:'password' })
  const [msg, setMsg] = useState(null)

  const onSubmit = async (e) => {
    e.preventDefault()
    try {
      const res = await register(form)
      setMsg(JSON.stringify(res, null, 2))
    } catch (err) {
      setMsg(err.response?.data ? JSON.stringify(err.response.data) : String(err))
    }
  }

  return (
    <div className="container">
      <div className="card" style={{maxWidth:560, margin:'0 auto'}}>
        <h2>Register</h2>
        <form onSubmit={onSubmit}>
          <div className="row">
            <input className="input" placeholder="Name" value={form.name} onChange={e=>setForm({...form, name:e.target.value})} />
          </div>
          <div className="space" />
          <div className="row">
            <input className="input" placeholder="Email" value={form.email} onChange={e=>setForm({...form, email:e.target.value})} />
          </div>
          <div className="space" />
          <div className="row">
            <input type="password" className="input" placeholder="Password" value={form.password} onChange={e=>setForm({...form, password:e.target.value})} />
          </div>
          <div className="space" />
          <button className="btn" type="submit">Create account</button>
        </form>
        {msg && (<><div className="space" /><pre className="muted" style={{whiteSpace:'pre-wrap'}}>{msg}</pre></>)}
      </div>
    </div>
  )
}
