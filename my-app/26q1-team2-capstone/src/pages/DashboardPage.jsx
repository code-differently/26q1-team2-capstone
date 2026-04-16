import DailyCheckInForm from "../components/DailyCheckInForm";
import MoodTrendsDashboard from "../components/MoodTrendsDashboard";
import AISuggestionCard from "../components/AISuggestionCard";
import ResourceAlertCard from "../components/ResourceAlertCard";
import DisclaimerBanner from "../components/DisclaimerBanner";
import Footer from "../components/Footer";
import "../styles/Dashboard.css";

export default function DashboardPage() {
  return (
    <div className="dashboard-page">
      <DisclaimerBanner />

      <section className="dashboard-hero">
        <div>
          <p className="dashboard-eyebrow">Welcome to NeuroNest</p>
          <h1>A calmer way to care for your mind every day</h1>
          <p className="dashboard-subtext">
            Track your mood, stress, and sleep. Build healthy patterns one
            check-in at a time.
          </p>
        </div>
      </section>

      <section className="stats-row">
        <div className="stat-pill">Latest Mood <span>No data</span></div>
        <div className="stat-pill">Stress Level <span>No data</span></div>
        <div className="stat-pill">Sleep <span>No data</span></div>
      </section>

      <section className="dashboard-grid">
        <div className="dashboard-main">
          <DailyCheckInForm />
          <AISuggestionCard />
          <ResourceAlertCard />
        </div>

        <aside className="dashboard-side">
          <MoodTrendsDashboard />
        </aside>
      </section>

      <Footer />
    </div>
  );
}