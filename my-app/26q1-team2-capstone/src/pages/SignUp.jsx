export default function SignUp() {
  return (
    <section className="page-section center-page">
      <div className="auth-card">
        <h2>Create your account</h2>
        <p>Start tracking your mood and wellness with NeuroNest.</p>

        <div className="form-group">
          <label>Full Name</label>
          <input type="text" placeholder="Enter your full name" />
        </div>

        <div className="form-group">
          <label>Email</label>
          <input type="email" placeholder="Enter your email" />
        </div>

        <div className="form-group">
          <label>Password</label>
          <input type="password" placeholder="Create a password" />
        </div>

        <div className="form-group">
          <label>Confirm Password</label>
          <input type="password" placeholder="Confirm password" />
        </div>

        <button className="primary-btn full-btn">Create Account</button>
      </div>
    </section>
  );
}