import { useState } from "react";
import "../styles/Dashboard.css";
import DisclaimerBanner from "../components/DisclaimerBanner";
import DailyCheckInForm from "../components/DailyCheckInForm";
import MoodTrendsDashboard from "../components/MoodTrendsDashboard";
import AiSuggestionCard from "../components/AiSuggestionCard";
import ResourceAlertCard from "../components/ResourceAlertCard";

export default function DashboardPage({ userId = 1 }) {
  const [savedCheckIn, setSavedCheckIn] = useState(null);
  const [aiSuggestion, setAiSuggestion] = useState(null);

  return (
    <div className="dashboard-page">
      <DisclaimerBanner />

      <div className="dashboard-hero">
        <p className="dashboard-eyebrow">Wellness Dashboard</p>
        <h1>NeuroNest Dashboard</h1>
        <p className="dashboard-subtext">
          Track your daily mental wellness, review mood patterns, and receive
          supportive AI-generated guidance based on your recent check-ins.
        </p>
      </div>

      <div className="stats-row">
        <div className="stat-pill">
          Latest Mood
          <span>{savedCheckIn ? savedCheckIn.moodScore : "--"}</span>
        </div>

        <div className="stat-pill">
          Stress Level
          <span>{savedCheckIn ? savedCheckIn.stressLevel : "--"}</span>
        </div>

        <div className="stat-pill">
          Sleep
          <span>{savedCheckIn ? savedCheckIn.sleepQuality : "--"}</span>
        </div>
      </div>

      <div className="dashboard-grid">
        <div className="dashboard-main">
          <DailyCheckInForm
            userId={userId}
            onCheckInSaved={(saved) => setSavedCheckIn(saved)}
            onAiSuggestionReceived={(ai) => setAiSuggestion(ai)}
          />

          <AiSuggestionCard suggestionData={aiSuggestion} />
        </div>

        <div className="dashboard-side">
          <div className="dashboard-side-inner">
            <MoodTrendsDashboard userId={userId} />
            <ResourceAlertCard />
          </div>
        </div>
      </div>
    </div>
  );
}