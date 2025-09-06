import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <nav>
      <Link to="/dashboard">Dashboard</Link>
      <Link to="/acquisition">Acquisition</Link>
      <Link to="/catalogue">Catalogue</Link>
      <Link to="/members">Members</Link>
      <Link to="/circulation">Circulation</Link>
      <Link to="/reviews">Reviews</Link>
      <button onClick={handleLogout}>Logout</button>
    </nav>
  );
}
