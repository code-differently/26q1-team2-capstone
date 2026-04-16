export default function Navbar({ setPage }) {
  return (
    <header className="navbar">
      <div className="nav-brand" onClick={() => setPage("home")}>
        <div className="logo-shell">
          <img
            src="/images/mentalhealthlogo.png"
            alt="NeuroNest Logo"
            className="logo"
          />
        </div>

        <div className="brand-text">
          <h1>NeuroNest</h1>
          <p>Daily wellness tracking and support</p>
        </div>
      </div>

      <nav className="nav-links">
        <button onClick={() => setPage("home")}>Home</button>
        <button onClick={() => setPage("dashboard")}>Dashboard</button>
        <button onClick={() => setPage("profile")}>Profile</button>
        <button onClick={() => setPage("login")}>Login</button>
        <button
          className="nav-signup-btn"
          onClick={() => setPage("signup")}
        >
          Sign Up
        </button>
      </nav>
    </header>
  );
}