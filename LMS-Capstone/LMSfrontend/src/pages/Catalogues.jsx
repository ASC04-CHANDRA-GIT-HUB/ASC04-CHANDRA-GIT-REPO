import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import api from '../api/client'

export default function Catalogues() {
  const [items, setItems] = useState([])
  const [q, setQ] = useState('')

  const load = async () => {
    try {
      const { data } = await api.get('/catalogues')
      setItems(Array.isArray(data) ? data : (data?.content || []))
    } catch (e) {
      setItems([])
    }
  }

  useEffect(() => { load() }, [])

  const filtered = items.filter(x => JSON.stringify(x).toLowerCase().includes(q.toLowerCase()))

  return (
    <div className="container">
      <div className="row">
        <input className="input" placeholder="Search…" value={q} onChange={e=>setQ(e.target.value)} />
        <button className="btn" onClick={load}>Reload</button>
      </div>
      <div className="space" />
      <div className="grid">
        {filtered.map((b, idx) => (
          <div className="card" key={b.id || idx}>
            <div className="tag">#{b.id ?? '—'}</div>
            <h3 style={{margin:'6px 0'}}>{b.title || b.name || 'Untitled'}</h3>
            <p className="muted">{b.author || b.description || ''}</p>
            <div className="space" />
            <Link className="btn" to={`/catalogues/${b.id}`}>Open</Link>
          </div>
        ))}
      </div>
    </div>
  )
}
