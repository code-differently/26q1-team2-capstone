export default function HomePage({ setPage, authUser }) {
  return (
    <section className="page-section">
      <div className="content-card hero-card">
        <p className="section-tag">Welcome</p>
        <h2>A calmer way to care for your mind every day</h2>
        <p>
          NeuroNest helps you track your mood, reflect on stress and sleep,
          and build healthier daily wellness habits in one peaceful space.
        </p>

        <div className="hero-actions">
          <button
            type="button"
            className="primary-btn"
            onClick={() => setPage(authUser ? "dashboard" : "login")}
          >
            {authUser ? "Open Dashboard" : "Sign In to Start"}
          </button>

          <button
            type="button"
            className="secondary-btn"
            onClick={() => {
              console.log("Create Account clicked");
              setPage("signup");
            }}
          >
            Create Account
          </button>
        </div>
      </div>
    </section>
  );
}