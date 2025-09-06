// src/components/ProtectedRoute.jsx
import { Navigate } from "react-router-dom";

export default function ProtectedRoute({ children }) {
  const isAuth = localStorage.getItem("isAuthenticated") === "true";
  return isAuth ? children : <Navigate to="/login" replace />;
}
