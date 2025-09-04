import React, { useEffect, useState } from 'react'
import api from '../api/client'

export default function Reviews() {
  const [list, setList] = useState([])
  const [catalogueId, setCatalogueId] = useState('')
  const [filtered, setFiltered] = useState([])

  const loadAll = async () => {
    try {
      const { data } = await api.get('/reviews')
      setList(Array.isArray(data) ? data : (data?.content || []))
    } catch (e) {
      setList([])
    }
  }

  const loadByCatalogue = async () => {
    try {
      const { data } = await api.get(`/reviews/catalogue/${catalogueId}`)
      setFiltered(Array.isArray(data) ? data : [])
    } catch (e) {
      setFiltered([])
    }
  }

  useEffect(() => { loadAll() }, [])

  return (
    <div className="container">
      <div className="card">
        <h2>Reviews</h2>
        <div className="row">
          <input className="input" placeholder="Catalogue ID" value={catalogueId} onChange={e=>setCatalogueId(e.target.value)} />
          <button className="btn" onClick={loadByCatalogue}>Load for Book</button>
        </div>
        <div className="space" />
        <h3>All</h3>
        <table>
          <thead><tr><th>ID</th><th>Member</th><th>Catalogue</th><th>Rating</th><th>Comment</th></tr></thead>
          <tbody>
            {list.map((r, i) => (
              <tr key={r.id || i}>
                <td>{r.id ?? '—'}</td>
                <td>{r.memberId ?? r.member_id ?? '—'}</td>
                <td>{r.catalogueId ?? r.catalogue_id ?? '—'}</td>
                <td>{r.rating ?? '—'}</td>
                <td>{r.comment ?? r.review ?? ''}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="space" />
        <h3>By Catalogue</h3>
        <table>
          <thead><tr><th>ID</th><th>Member</th><th>Rating</th><th>Comment</th></tr></thead>
          <tbody>
            {filtered.map((r, i) => (
              <tr key={r.id || i}>
                <td>{r.id ?? '—'}</td>
                <td>{r.memberId ?? r.member_id ?? '—'}</td>
                <td>{r.rating ?? '—'}</td>
                <td>{r.comment ?? r.review ?? ''}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
