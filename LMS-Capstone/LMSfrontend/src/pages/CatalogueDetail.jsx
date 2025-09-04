import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import api from '../api/client'

export default function CatalogueDetail() {
  const { id } = useParams()
  const [data, setData] = useState(null)
  const [rating, setRating] = useState(0)
  const [msg, setMsg] = useState(null)

  const load = async () => {
    try {
      const { data } = await api.get(`/catalogues/${id}`)
      setData(data)
    } catch (e) {
      setData(null)
    }
  }
  useEffect(() => { load() }, [id])

  const updateRating = async () => {
    try {
      const { data: res } = await api.put(`/catalogues/${id}/rating`, { rating: Number(rating) })
      setMsg(JSON.stringify(res, null, 2))
    } catch (e) {
      setMsg(e.response?.data ? JSON.stringify(e.response.data) : String(e))
    }
  }

  if (!data) return <div className="container"><div className="card">Not found.</div></div>

  return (
    <div className="container">
      <div className="card">
        <h2>{data.title || data.name || 'Untitled'} <span className="muted">#{id}</span></h2>
        <p className="muted">{data.author || data.description}</p>
        <div className="row">
          <input className="input" type="number" value={rating} onChange={e=>setRating(e.target.value)} placeholder="Rating 0-5" />
          <button className="btn" onClick={updateRating}>Update Rating</button>
        </div>
        {msg && (<><div className="space" /><pre className="muted" style={{whiteSpace:'pre-wrap'}}>{msg}</pre></>)}
      </div>
    </div>
  )
}
