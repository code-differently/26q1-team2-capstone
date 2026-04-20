import { useState } from "react";
import { submitCheckIn, getAiRecommendation } from "../api/api";

export default function DailyCheckInForm({
  userId,
  currentGoal,
  onCheckInSaved,
  onGoalUpdate,
  onAiSuggestionReceived,
}) {
  const [formData, setFormData] = useState({
    moodScore: 5,
    stressLevel: 5,
    sleepQuality: "OKAY",
    journalNotes: "",
    triggers: "",
    copingStrategiesUsed: "",
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  function handleChange(event) {
    const { name, value } = event.target;

    setFormData((prev) => ({
      ...prev,
      [name]:
        name === "moodScore" || name === "stressLevel"
          ? Number(value)
          : value,
    }));
  }

  function mapSleepQualityToNumber(value) {
    if (value === "GOOD") return 8;
    if (value === "OKAY") return 5;
    if (value === "POOR") return 2;
    return 5;
  }

  async function handleSubmit(event) {
    event.preventDefault();
    setError("");

    try {
      setLoading(true);

      const savedCheckIn = await submitCheckIn(userId, formData);

      if (onCheckInSaved) {
        onCheckInSaved(savedCheckIn);
      }

      const promptText = `Mood: ${formData.moodScore}, Stress: ${formData.stressLevel}, Sleep: ${formData.sleepQuality}, Notes: ${formData.journalNotes}`;

      const aiResult = await getAiRecommendation({
        moodScore: formData.moodScore,
        stressLevel: formData.stressLevel,
        sleepQuality: mapSleepQualityToNumber(formData.sleepQuality),
        journalNotes: formData.journalNotes,
        currentGoal: currentGoal || "",
      });

      if (onAiSuggestionReceived) {
        onAiSuggestionReceived({
          suggestion: aiResult?.suggestion || "",
          prompt: promptText,
        });
      }

      if (aiResult?.suggestedGoal && onGoalUpdate) {
        onGoalUpdate(aiResult.suggestedGoal);
      }
    } catch (err) {
      console.error("Check-in submit failed:", err);
      setError(err.message || "Failed to submit check-in.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="card">
      <h2>Daily Check-In</h2>

      <form onSubmit={handleSubmit} className="daily-checkin-form">
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
            <label>Sleep Quality</label>
            <select
              name="sleepQuality"
              value={formData.sleepQuality}
              onChange={handleChange}
            >
              <option value="GOOD">GOOD</option>
              <option value="OKAY">OKAY</option>
              <option value="POOR">POOR</option>
            </select>
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

        <div className="form-grid">
          <div>
            <label>Triggers</label>
            <input
              type="text"
              name="triggers"
              value={formData.triggers}
              onChange={handleChange}
            />
          </div>

          <div>
            <label>Coping Strategies Used</label>
            <input
              type="text"
              name="copingStrategiesUsed"
              value={formData.copingStrategiesUsed}
              onChange={handleChange}
            />
          </div>
        </div>

        {error && <p className="error-message">{error}</p>}

        <button type="submit" disabled={loading}>
          {loading ? "Submitting..." : "Submit Check-In"}
        </button>
      </form>
    </div>
  );
}