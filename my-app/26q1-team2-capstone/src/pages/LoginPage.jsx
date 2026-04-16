export default function LoginPage() {
  return (
    <section className="page-section center-page">
      <div className="auth-card">
        <h2>Welcome back to NeuroNest</h2>
        <p>Sign in to continue your daily wellness journey.</p>

        <div className="form-group">
          <label>Email</label>
          <input type="email" placeholder="Enter your email" />
        </div>

        <div className="form-group">
          <label>Password</label>
          <input type="password" placeholder="Enter your password" />
        </div>

        <button className="primary-btn full-btn">Sign In</button>
      </div>
    </section>
  );
}