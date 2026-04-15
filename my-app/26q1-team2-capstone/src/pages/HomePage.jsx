import { Link } from "react-router-dom";

export default function HomePage() {
  return (
    <div className="home-page fade-in-page">
      <section className="landing-hero glass-card">
        <div className="landing-copy">
          <p className="section-kicker">Welcome to NeuroNest</p>
          <h1>A calmer way to care for your mind every day</h1>
          <p className="landing-text">
            NeuroNest helps you track your mood, reflect on stress and sleep,
            and receive supportive AI-generated suggestions in a peaceful
            dashboard designed for daily wellness.
          </p>

          <div className="landing-actions">
            <Link to="/dashboard" className="primary-btn inline-btn">
              Open Dashboard
            </Link>
            <Link to="/login" className="secondary-btn inline-btn">
              Sign In
            </Link>
          </div>

          <div className="landing-stats">
            <div className="landing-stat-card">
              <span>Track</span>
              <strong>Daily mood check-ins</strong>
            </div>
            <div className="landing-stat-card">
              <span>View</span>
              <strong>Wellness trend charts</strong>
            </div>
            <div className="landing-stat-card">
              <span>Receive</span>
              <strong>AI support suggestions</strong>
            </div>
          </div>
        </div>

        <div className="landing-visual">
          <div className="visual-card floating-panel">
            <h3>Today’s Focus</h3>
            <p>Breathe, reflect, and notice your patterns one step at a time.</p>
          </div>
          <div className="visual-orb orb-one"></div>
          <div className="visual-orb orb-two"></div>
          <div className="visual-wave"></div>
        </div>
      </section>

      <section className="feature-grid">
        <article className="feature-card glass-card">
          <h3>Daily Check-Ins</h3>
          <p>
            Quickly log your mood, stress level, sleep, and journal notes in a
            calming space.
          </p>
        </article>

        <article className="feature-card glass-card">
          <h3>Visual Trends</h3>
          <p>
            See how your mood and stress change over time with clean charts and
            easy-to-read history.
          </p>
        </article>

        <article className="feature-card glass-card">
          <h3>Support When Needed</h3>
          <p>
            Receive clearly labeled AI-generated suggestions and supportive
            resource reminders.
          </p>
        </article>
      </section>
    </div>
  );
}