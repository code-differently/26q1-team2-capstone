import logo from "../assets/mentalhealthlogo.png";

export default function Navbar({ setPage, loggedIn, onLogout }) {
  return (
    <header className="navbar">
      <div className="nav-brand" onClick={() => setPage("home")}>
        <img src={logo} alt="NeuroNest logo" className="logo" />
        <div className="brand-text">
          <h1>NeuroNest</h1>
          <p>Your calm space for daily wellness</p>
        </div>
      </div>

      <nav className="nav-links">
        <button onClick={() => setPage("home")}>Home</button>
        <button onClick={() => setPage("about")}>About</button>
        <button onClick={() => setPage("dashboard")}>Dashboard</button>

        {!loggedIn ? (
          <>
            <button onClick={() => setPage("login")}>Login</button>
            <button
              className="nav-signup-btn"
              onClick={() => setPage("signup")}
            >
              Create Account
            </button>
          </>
        ) : (
          <>
            <button onClick={() => setPage("profile")}>Profile</button>
            <button className="nav-signup-btn" onClick={onLogout}>
              Logout
            </button>
          </>
        )}
      </nav>
    </header>
  );
}