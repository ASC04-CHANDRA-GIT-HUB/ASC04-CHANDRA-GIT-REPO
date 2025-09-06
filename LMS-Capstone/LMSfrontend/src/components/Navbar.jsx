import { Link } from "react-router-dom";
import "./Navbar.css";

export default function Navbar() {
  return (
    <nav className="navbar">
      <h2 className="logo">LMS Dashboard</h2>
      <div className="links">
        <Link to="/">Home</Link>
        <Link to="/acquisitions">Acquisitions</Link>
        <Link to="/catalogue">Catalogue</Link>
        <Link to="/circulation">Circulation</Link>
        <Link to="/members">Members</Link>
        <Link to="/reviews">Reviews</Link>
        <Link to="/login">Login</Link>
      </div>
    </nav>
  );
}
