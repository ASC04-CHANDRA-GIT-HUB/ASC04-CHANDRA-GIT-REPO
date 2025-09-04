import React from 'react'
import { useParams } from 'react-router-dom'

export default function MemberDetail() {
  const { id } = useParams()
  return (
    <div className="container">
      <div className="card">
        <h2>Member #{id}</h2>
        <p className="muted">Extend with edit/update endpoints as available.</p>
      </div>
    </div>
  )
}
