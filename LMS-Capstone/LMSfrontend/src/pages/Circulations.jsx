import React, { useState } from 'react'
import api from '../api/client'

export default function Circulations() {
  const [catalogueId, setCatalogueId] = useState('')
  const [memberId, setMemberId] = useState('')
  const [exists, setExists] = useState(null)
  const [returnMsg, setReturnMsg] = useState(null)

  const checkExists = async () => {
    setExists(null)
    try {
      const { data } = await api.get(`/circulations/exists`, { params: { catalogueId, memberId } })
      setExists(data)
    } catch (e) {
      setExists(e.response?.data || String(e))
    }
  }

  const returnBook = async () => {
    setReturnMsg(null)
    try {
      const { data } = await api.put(`/circulations/${catalogueId}/return`, { memberId })
      setReturnMsg(JSON.stringify(data, null, 2))
    } catch (e) {
      setReturnMsg(e.response?.data ? JSON.stringify(e.response.data) : String(e))
    }
  }

  return (
    <div className="container">
      <div className="card">
        <h2>Circulation</h2>
        <div className="row">
          <input className="input" placeholder="Catalogue ID" value={catalogueId} onChange={e=>setCatalogueId(e.target.value)} />
          <input className="input" placeholder="Member ID" value={memberId} onChange={e=>setMemberId(e.target.value)} />
          <button className="btn" onClick={checkExists}>Check Issue</button>
          <button className="btn" onClick={returnBook}>Return</button>
        </div>
        <div className="space" />
        {exists !== null && <pre className="muted" style={{whiteSpace:'pre-wrap'}}>{JSON.stringify(exists,null,2)}</pre>}
        {returnMsg && <><div className="space" /><pre className="muted" style={{whiteSpace:'pre-wrap'}}>{returnMsg}</pre></>}
      </div>
    </div>
  )
}
