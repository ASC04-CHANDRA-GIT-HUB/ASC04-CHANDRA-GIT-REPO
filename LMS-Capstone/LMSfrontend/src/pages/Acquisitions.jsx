import React, { useEffect, useState } from 'react'
import api from '../api/client'

export default function Acquisitions() {
  const [items, setItems] = useState([])
  const [msg, setMsg] = useState(null)

  const load = async () => {
    try {
      const { data } = await api.get('/acquisitions')
      setItems(Array.isArray(data) ? data : (data?.content || []))
    } catch (e) {
      setItems([])
    }
  }

  useEffect(() => { load() }, [])

  return (
    <div className="container">
      <div className="card">
        <h2>Acquisitions <span className="tag">secure</span></h2>
        <p className="muted">Requires login (JWT attached automatically).</p>
        <div className="space" />
        <button className="btn" onClick={load}>Reload</button>
        <div className="space" />
        <table>
          <thead><tr><th>ID</th><th>Title</th><th>Status</th></tr></thead>
          <tbody>
            {items.map((x,i)=>(
              <tr key={x.id || i}>
                <td>{x.id ?? '—'}</td>
                <td>{x.title || x.name || '—'}</td>
                <td>{x.status || '—'}</td>
              </tr>
            ))}
          </tbody>
        </table>
        {msg && (<><div className="space" /><pre className="muted" style={{whiteSpace:'pre-wrap'}}>{msg}</pre></>)}
      </div>
    </div>
  )
}
