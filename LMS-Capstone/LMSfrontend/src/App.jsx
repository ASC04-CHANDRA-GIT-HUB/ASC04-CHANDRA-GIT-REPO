import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Dashboard from "./components/Dashboard";
import Acquisition from "./pages/Acquisition";
import Catalogue from "./pages/Catalogue";
import Circulation from "./pages/Circulation";
import Members from "./pages/Members";
import Reviews from "./pages/Reviews";
import Login from "./pages/Login";
import Register from "./pages/Register";
import "./App.css";

export default function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <div className="container">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/acquisitions" element={<Acquisition />} />
          <Route path="/catalogue" element={<Catalogue />} />
          <Route path="/circulation" element={<Circulation />} />
          <Route path="/members" element={<Members />} />
          <Route path="/reviews" element={<Reviews />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}
