import { useEffect, useState } from "react";
import "../styles/Dashboard.css";
import {
  getUserProfile,
  getAiRecommendation,
  updateUserGoal,
} from "../api/api";

export default function ProfilePage({
  authUser,
  userId,
  wellnessState,
  onGoalUpdate,
  onAiUpdate,
}) {
  const [profile, setProfile] = useState(authUser || null);
  const [formData, setFormData] = useState({
    moodScore: 3,
    stressLevel: 3,
    sleepQuality: 3,
    journalNotes: "",
    currentGoal: "",
  });

  const [goal, setGoal] = useState(
    wellnessState?.goal || authUser?.goal || ""
  );
  const [loadingProfile, setLoadingProfile] = useState(true);
  const [loadingAi, setLoadingAi] = useState(false);
  const [savingGoal, setSavingGoal] = useState(false);
  const [error, setError] = useState("");
  const [goalMessage, setGoalMessage] = useState("");

  useEffect(() => {
    async function loadData() {
      try {
        setLoadingProfile(true);
        setError("");

        if (!userId) {
          throw new Error("No user ID found.");
        }

        const userData = await getUserProfile(userId);

        const loadedProfile = {
          id: userData?.id ?? authUser?.id ?? null,
          firstName: userData?.firstName ?? authUser?.firstName ?? "",
          lastName: userData?.lastName ?? authUser?.lastName ?? "",
          email: userData?.email ?? authUser?.email ?? "",
          goal: userData?.goal ?? authUser?.goal ?? "",
        };

        setProfile(loadedProfile);
        setGoal(loadedProfile.goal || "");
        setFormData((prev) => ({
          ...prev,
          currentGoal: loadedProfile.goal || "",
        }));

        localStorage.setItem("authUser", JSON.stringify(loadedProfile));

        if (loadedProfile.goal && onGoalUpdate) {
          onGoalUpdate(loadedProfile.goal);
        }
      } catch (err) {
        console.error("Profile load failed:", err);
        setError(err.message || "Failed to load profile.");
      } finally {
        setLoadingProfile(false);
      }
    }

    loadData();
  }, [userId]);

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]:
        name === "moodScore" ||
        name === "stressLevel" ||
        name === "sleepQuality"
          ? Number(value)
          : value,
    }));
  };

  const handleGenerateAi = async () => {
    try {
      setLoadingAi(true);
      setError("");
      setGoalMessage("");

      const promptText = `Mood: ${formData.moodScore}, Stress: ${formData.stressLevel}, Sleep: ${formData.sleepQuality}, Notes: ${formData.journalNotes}`;

      const result = await getAiRecommendation({
        moodScore: formData.moodScore,
        stressLevel: formData.stressLevel,
        sleepQuality: formData.sleepQuality,
        journalNotes: formData.journalNotes,
        currentGoal: goal || formData.currentGoal,
      });

      const generatedSuggestion = result?.suggestion || "No suggestion available";
      const generatedGoal = result?.suggestedGoal || goal;

      setGoal(generatedGoal);

      setFormData((prev) => ({
        ...prev,
        currentGoal: generatedGoal,
      }));

      if (onAiUpdate) {
        onAiUpdate({
          suggestion: generatedSuggestion,
          prompt: promptText,
        });
      }

      if (onGoalUpdate) {
        onGoalUpdate(generatedGoal);
      }
    } catch (err) {
      console.error("AI generation failed:", err);
      setError(err.message || "Could not generate AI suggestion.");
    } finally {
      setLoadingAi(false);
    }
  };

  const handleSaveGoal = async () => {
    try {
      setSavingGoal(true);
      setError("");
      setGoalMessage("");

      if (!profile?.id) {
        throw new Error("User profile not loaded.");
      }

      const updatedUser = await updateUserGoal(profile.id, goal);

      const mergedProfile = {
        ...profile,
        ...updatedUser,
        goal: updatedUser?.goal ?? goal,
      };

      setProfile(mergedProfile);
      setGoal(mergedProfile.goal || "");

      setFormData((prev) => ({
        ...prev,
        currentGoal: mergedProfile.goal || "",
      }));

      localStorage.setItem("authUser", JSON.stringify(mergedProfile));

      if (onGoalUpdate) {
        onGoalUpdate(mergedProfile.goal || "");
      }

      setGoalMessage("Goal saved successfully.");
    } catch (err) {
      console.error("Goal save failed:", err);
      setError(err.message || "Failed to save goal.");
    } finally {
      setSavingGoal(false);
    }
  };

  if (loadingProfile) {
    return (
      <div className="profile-page">
        <div className="card">
          <h2>Loading profile...</h2>
          <p>Please wait while we load your information.</p>
        </div>
      </div>
    );
  }

  if (error && !profile) {
    return (
      <div className="profile-page">
        <div className="card">
          <h2>Profile Error</h2>
          <p className="error-message">{error}</p>
        </div>
      </div>
    );
  }

  return (
    <div className="profile-page">
      <div className="profile-header-card">
        <h1>Welcome{profile?.firstName ? `, ${profile.firstName}` : ""}</h1>

        <div className="profile-info-grid">
          <div>
            <p className="summary-label">Name</p>
            <p className="summary-value">
              {profile?.firstName} {profile?.lastName}
            </p>
          </div>

          <div>
            <p className="summary-label">Email</p>
            <p className="summary-value">{profile?.email}</p>
          </div>

          <div>
            <p className="summary-label">Goal</p>
            <p className="summary-value">{goal || "No goal set yet"}</p>
          </div>
        </div>
      </div>

      {error && <p className="error-message">{error}</p>}
      {goalMessage && <p className="success-message">{goalMessage}</p>}

      <div className="card">
        <h2>Daily Check-In for AI</h2>

        <div className="form-grid">
          <div>
            <label>Mood Score (1-10)</label>
            <input
              type="number"
              name="moodScore"
              min="1"
              max="10"
              value={formData.moodScore}
              onChange={handleChange}
            />
          </div>

          <div>
            <label>Stress Level (1-10)</label>
            <input
              type="number"
              name="stressLevel"
              min="1"
              max="10"
              value={formData.stressLevel}
              onChange={handleChange}
            />
          </div>

          <div>
            <label>Sleep Quality (1-10)</label>
            <input
              type="number"
              name="sleepQuality"
              min="1"
              max="10"
              value={formData.sleepQuality}
              onChange={handleChange}
            />
          </div>
        </div>

        <label>Journal Notes</label>
        <textarea
          name="journalNotes"
          rows="4"
          value={formData.journalNotes}
          onChange={handleChange}
          placeholder="Write how you're feeling today..."
        />

        <button onClick={handleGenerateAi} disabled={loadingAi}>
          {loadingAi ? "Generating..." : "Generate AI Suggestion"}
        </button>
      </div>

      <div className="card">
        <h2>AI Prompt</h2>
        <p>{wellnessState?.lastPrompt || "No AI prompt yet."}</p>
      </div>

      <div className="card">
        <h2>AI-Generated Suggestion</h2>
        <p>{wellnessState?.aiSuggestion || "No suggestion available"}</p>
      </div>

      <div className="card">
        <h2>Your Goal</h2>
        <input
          type="text"
          value={goal}
          onChange={(e) => setGoal(e.target.value)}
          placeholder="Your goal will appear here"
        />
        <p className="helper-text">
          You can edit the AI-generated goal if it does not fit you.
        </p>

        <button onClick={handleSaveGoal} disabled={savingGoal}>
          {savingGoal ? "Saving..." : "Save Goal"}
        </button>
      </div>
    </div>
  );
}