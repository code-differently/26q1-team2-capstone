import { useState } from "react";

export default function LoginPage() {
  const [form, setForm] = useState({
    email: "",
    password: ""
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    alert(`Welcome back, ${form.email || "user"}!`);
  };

  return (
    <div className="login-page fade-in-page">
      <section className="login-layout">
        <div className="login-left glass-card">
          <p className="section-kicker">Sign In</p>
          <h1>Welcome back to NeuroNest</h1>
          <p className="landing-text">
            Sign in to continue tracking your mood, view charts, and access your
            calming dashboard.
          </p>

          <div className="login-benefits">
            <div className="login-benefit-card">
              <strong>Daily check-ins</strong>
              <span>Track mood, stress, and sleep</span>
            </div>
            <div className="login-benefit-card">
              <strong>Visual insights</strong>
              <span>See your patterns over time</span>
            </div>
            <div className="login-benefit-card">
              <strong>Support tools</strong>
              <span>Get AI-generated wellness suggestions</span>
            </div>
          </div>
        </div>

        <div className="login-right glass-card">
          <form className="login-form" onSubmit={handleSubmit}>
            <p className="section-kicker">Account Access</p>
            <h2>Login</h2>

            <label>
              Email
              <input
                type="email"
                name="email"
                placeholder="Enter your email"
                value={form.email}
                onChange={handleChange}
                required
              />
            </label>

            <label>
              Password
              <input
                type="password"
                name="password"
                placeholder="Enter your password"
                value={form.password}
                onChange={handleChange}
                required
              />
            </label>

            <button type="submit" className="primary-btn glow-btn">
              Sign In
            </button>

            <button type="button" className="demo-btn">
              Continue as Demo User
            </button>

            <p className="login-footer-text">
              Don’t have an account yet? <span>Create one later.</span>
            </p>
          </form>
        </div>
      </section>
    </div>
  );
}