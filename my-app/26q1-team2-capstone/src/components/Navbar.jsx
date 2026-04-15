import { Link } from "react-router-dom";
import logo from "../assets/mentalhealthlogo.png";

export default function Navbar() {
  return (
    <nav className="nav">
      <div className="nav-left">
        <div className="logo-shell">
          <img src={logo} alt="NeuroNest logo" className="logo" />
          <span className="logo-glow-ring"></span>
        </div>

        <div className="brand-text">
          <h2 className="brand-title">NeuroNest</h2>
          <p className="brand-subtitle">Your calm space for daily wellness</p>
        </div>
      </div>

      <div className="nav-links">
        <Link to="/">Home</Link>
        <Link to="/dashboard">Dashboard</Link>
        <Link to="/login">Login</Link>
        <Link to="/profile">Profile</Link>
      </div>
    </nav>
  );
}