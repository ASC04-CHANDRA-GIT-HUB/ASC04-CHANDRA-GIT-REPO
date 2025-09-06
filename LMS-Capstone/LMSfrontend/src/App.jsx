import { BrowserRouter as Router, Routes, Route, useLocation, useNavigate, Link } from "react-router-dom";
import Dashboard from "./components/Dashboard.jsx";
import Login from "./pages/Login.jsx";
import Catalogue from "./pages/Catalogue.jsx";
import Members from "./pages/Members.jsx";
import Reviews from "./pages/Reviews.jsx";
import Circulation from "./pages/Circulation.jsx";
import Acquisitions from "./pages/Acquisition.jsx";

function Navbar() {
  const location = useLocation();
  const navigate = useNavigate();

  // Hide navbar completely on login page? or only logout button?
  const hideLogout = location.pathname === "/login";

  const handleLogout = () => {
    localStorage.removeItem("isAuthenticated");
    navigate("/login");
  };

return (
<div className="flex justify-between items-center bg-[#1e1a18] px-6 py-3 shadow-2xl border-b border-[#333]/50">
  {/* Logo & Project Title */}
  <Link
    to="/dashboard"
    className="flex items-center space-x-2"
  >
    {/* Book Icon */}
    <span className="text-2xl text-[#ffd700] drop-shadow-[0_2px_5px_rgba(0,0,0,0.8)]">
      🏛️
    </span>

    {/* 3D Outline Title */}
    <span
      className="text-xl font-extrabold font-sans relative text-[#ffd700]"
      style={{
        textShadow: `
          2px 2px 0 #000,
          -2px 2px 0 #000,
          2px -2px 0 #000,
          -2px -2px 0 #000
        `
      }}
    >
      ವಾಚನಾಲಯ
    </span>
  </Link>

  {/* Logout Button */}
  {!hideLogout && (
    <button
      onClick={handleLogout}
      className="bg-[#d4af37] text-[#1e1a18] px-4 py-1 rounded font-semibold shadow-md hover:bg-[#ffd700] transition-colors duration-300"
    >
      ಲಾಗಔಟ್
    </button>
  )}
</div>



);

}

export default function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/catalogue" element={<Catalogue />} />
        <Route path="/members" element={<Members />} />
        <Route path="/reviews" element={<Reviews />} />
        <Route path="/circulation" element={<Circulation />} />
        <Route path="/acquisitions" element={<Acquisitions />} />
      </Routes>
    </Router>
  );
}
