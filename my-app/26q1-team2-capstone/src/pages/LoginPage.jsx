import { useState } from "react";

export default function LoginPage({ onSignIn, setPage }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!email.trim() || !password.trim()) {
      alert("Please enter your email and password.");
      return;
    }

    onSignIn();
  };

  return (
    <section className="page-section center-page">
      <div className="auth-card">
        <h2>Welcome back to NeuroNest</h2>
        <p>Sign in to continue your daily wellness journey.</p>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="login-email">Email</label>
            <input
              id="login-email"
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div className="form-group">
            <label htmlFor="login-password">Password</label>
            <input
              id="login-password"
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <button type="submit" className="primary-btn full-btn">
            Sign In
          </button>
        </form>

        <p className="switch-text">
          Don’t have an account?{" "}
          <span onClick={() => setPage("signup")}>Create one here</span>
        </p>
      </div>
    </section>
  );
}