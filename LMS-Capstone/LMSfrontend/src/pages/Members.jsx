import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import api from '../api/client'

export default function Members() {
  const [id, setId] = useState('')
  const [data, setData] = useState(null)
  const [err, setErr] = useState(null)

  const fetchById = async () => {
    setErr(null)
    setData(null)
    try {
      const { data } = await api.get(`/members/${id}`)
      setData(data)
    } catch (e) {
      setErr(e.response?.data ? JSON.stringify(e.response.data) : String(e))
    }
  }

  return (
    <div className="container">
      <div className="card">
        <h2>Find Member by ID</h2>
        <div className="row">
          <input className="input" placeholder="Member ID" value={id} onChange={e=>setId(e.target.value)} />
          <button className="btn" onClick={fetchById}>Fetch</button>
        </div>
        {err && (<><div className="space" /><pre className="muted">{err}</pre></>)}
        {data && (
          <>
            <div className="space" />
            <table>
              <tbody>
                {Object.entries(data).map(([k,v])=> (
                  <tr key={k}><th>{k}</th><td>{String(v)}</td></tr>
                ))}
              </tbody>
            </table>
            <div className="space" />
            <Link className="btn" to={`/members/${data.id}`}>Open Detail</Link>
          </>
        )}
      </div>
    </div>
  )
}
