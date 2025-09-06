import { Link } from "react-router-dom";
import "./Dashboard.css";

export default function Dashboard() {
  const services = [
    { name: "Acquisitions", path: "/acquisitions" },
    { name: "Catalogue", path: "/catalogue" },
    { name: "Circulation", path: "/circulation" },
    { name: "Members", path: "/members" },
    { name: "Reviews", path: "/reviews" },
  ];

  return (
    <div className="dashboard">
      {services.map(s => (
        <Link key={s.name} to={s.path} className="card">
          <h2>{s.name}</h2>
        </Link>
      ))}
    </div>
  );
}
