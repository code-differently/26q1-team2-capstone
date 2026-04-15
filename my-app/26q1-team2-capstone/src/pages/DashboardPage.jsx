import DailyCheckInForm from "../components/DailyCheckInForm";
import MoodTrendsDashboard from "../components/MoodTrendsDashboard";
import AiSuggestionCard from "../components/AiSuggestionCard";
import ResourceAlertCard from "../components/ResourceAlertCard";
import useMoodData from "../hooks/useMoodData";
import { buildSuggestionsAndAlert } from "../services/api";

export default function DashboardPage() {
  const { data, addEntry } = useMoodData();

  const latestEntry = data.length > 0 ? data[0] : null;
  const { suggestions, resourceAlert } = latestEntry
    ? buildSuggestionsAndAlert(latestEntry)
    : { suggestions: [], resourceAlert: "" };

  const handleSubmit = (formData) => {
    const entry = {
      id: Date.now(),
      date: new Date().toLocaleDateString(),
      mood: formData.mood,
      stress: Number(formData.stress),
      sleep: Number(formData.sleep),
      notes: formData.notes.trim()
    };

    addEntry(entry);
  };

  return (
    <div className="dashboard-layout fade-in-page">
      <section className="dashboard-hero glass-card">
        <div>
          <p className="section-kicker">Daily Wellness</p>
          <h1>Your Calm Space</h1>
          <p className="hero-text">
            Track your mood, understand stress, and build healthier patterns one
            check-in at a time.
          </p>
        </div>

        <div className="hero-badges">
          <div className="hero-badge float-card">
            <span>Latest Mood</span>
            <strong>{latestEntry ? latestEntry.mood : "No data"}</strong>
          </div>
          <div className="hero-badge float-card delay-1">
            <span>Stress Level</span>
            <strong>{latestEntry ? `${latestEntry.stress}/10` : "No data"}</strong>
          </div>
          <div className="hero-badge float-card delay-2">
            <span>Sleep</span>
            <strong>{latestEntry ? `${latestEntry.sleep} hrs` : "No data"}</strong>
          </div>
        </div>
      </section>

      <div className="dashboard-grid">
        <div className="left-column">
          <DailyCheckInForm onSubmit={handleSubmit} />
          <AiSuggestionCard suggestions={suggestions} />
          <ResourceAlertCard message={resourceAlert} />
        </div>

        <div className="right-column">
          <MoodTrendsDashboard data={data} />
        </div>
      </div>
    </div>
  );
}