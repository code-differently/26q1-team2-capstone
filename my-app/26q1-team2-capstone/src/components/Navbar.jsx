import logo from "../assets/mentalhealthlogo.png";

export default function Navbar({ setPage }) {
  return (
    <header className="navbar">
      <div className="nav-brand" onClick={() => setPage("home")}>
        <img src={logo} alt="NeuroNest logo" className="logo" />
        <div className="brand-text">
          <h1>NeuroNest</h1>
          <p>Your calm space for daily wellness</p>
        </div>
      </div>

      <div className="nav-links">
        <button onClick={() => setPage("home")}>Home</button>
        <button onClick={() => setPage("dashboard")}>Dashboard</button>
        <button onClick={() => setPage("login")}>Login</button>
        <button onClick={() => setPage("profile")}>Profile</button>
        <button onClick={() => setPage("signup")} className="nav-signup-btn">
          Create Account
        </button>
      </div>
    </header>
  );
}