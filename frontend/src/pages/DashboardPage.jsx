import { useState } from "react";
import "../styles/Dashboard.css";
import DisclaimerBanner from "../components/DisclaimerBanner";
import DailyCheckInForm from "../components/DailyCheckInForm";
import MoodTrendsDashboard from "../components/MoodTrendsDashboard";
import AiSuggestionCard from "../components/AiSuggestionCard";
import ResourceAlertCard from "../components/ResourceAlertCard";

export default function DashboardPage({
  userId,
  authUser,
  wellnessState,
  onGoalUpdate,
  onAiUpdate,
}) {
  const [savedCheckIn, setSavedCheckIn] = useState(null);

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

      <div className="profile-summary-card">
        <div>
          <p className="summary-label">Name</p>
          <p className="summary-value">
            {authUser?.firstName} {authUser?.lastName}
          </p>
        </div>

        <div>
          <p className="summary-label">Email</p>
          <p className="summary-value">{authUser?.email}</p>
        </div>

        <div>
          <p className="summary-label">Goal</p>
          <p className="summary-value">{wellnessState?.goal || "No goal set yet"}</p>
        </div>
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
            currentGoal={wellnessState?.goal || authUser?.goal || ""}
            onCheckInSaved={setSavedCheckIn}
            onGoalUpdate={onGoalUpdate}
            onAiSuggestionReceived={onAiUpdate}
          />

          <AiSuggestionCard
            title="Supportive Guidance"
            suggestionData={{
              suggestion: wellnessState?.aiSuggestion || "",
              prompt: wellnessState?.lastPrompt || "",
            }}
          />
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