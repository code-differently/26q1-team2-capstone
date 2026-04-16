import { useState } from "react";
import "../styles/Form.css";
import { createCheckIn, getAiRecommendation } from "../api/api";

export default function DailyCheckInForm({
  userId = 1,
  onCheckInSaved,
  onAiSuggestionReceived
}) {
  const [formData, setFormData] = useState({
    moodScore: 5,
    stressLevel: 5,
    sleepQuality: "OKAY",
    journalNotes: "",
    triggers: "",
    copingStrategiesUsed: ""
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  function handleChange(event) {
    const { name, value } = event.target;

    setFormData((prev) => ({
      ...prev,
      [name]:
        name === "moodScore" || name === "stressLevel"
          ? Number(value)
          : value
    }));
  }

  function validateForm() {
    if (!formData.moodScore || formData.moodScore < 1 || formData.moodScore > 10) {
      return "Mood score must be between 1 and 10.";
    }

    if (!formData.stressLevel || formData.stressLevel < 1 || formData.stressLevel > 10) {
      return "Stress level must be between 1 and 10.";
    }

    if (!formData.sleepQuality) {
      return "Sleep quality is required.";
    }

    return "";
  }

  async function handleSubmit(event) {
    event.preventDefault();
    setError("");
    setSuccessMessage("");

    const validationError = validateForm();
    if (validationError) {
      setError(validationError);
      return;
    }

    try {
      setLoading(true);

      const savedCheckIn = await createCheckIn(userId, formData);

      setSuccessMessage("Check-in saved successfully.");

      if (onCheckInSaved) {
        onCheckInSaved(savedCheckIn);
      }

      try {
        const aiResponse = await getAiRecommendation(userId);

        if (onAiSuggestionReceived) {
          onAiSuggestionReceived(aiResponse);
        }
      } catch (aiError) {
        console.error("AI recommendation error:", aiError);
      }

      setFormData({
        moodScore: 5,
        stressLevel: 5,
        sleepQuality: "OKAY",
        journalNotes: "",
        triggers: "",
        copingStrategiesUsed: ""
      });
    } catch (submitError) {
      console.error("Check-in submission error:", submitError);
      setError("Failed to save check-in. Please try again.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="content-card">
      <p className="section-label">Check-In</p>
      <h2>Daily Reflection</h2>
      <p>
        Log how you feel today and let NeuroNest help you spot patterns over
        time.
      </p>

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="moodScore">Mood Score: {formData.moodScore}</label>
          <input
            id="moodScore"
            name="moodScore"
            type="range"
            min="1"
            max="10"
            value={formData.moodScore}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="stressLevel">Stress Level: {formData.stressLevel}</label>
          <input
            id="stressLevel"
            name="stressLevel"
            type="range"
            min="1"
            max="10"
            value={formData.stressLevel}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="sleepQuality">Sleep Quality</label>
          <select
            id="sleepQuality"
            name="sleepQuality"
            value={formData.sleepQuality}
            onChange={handleChange}
          >
            <option value="GOOD">Good</option>
            <option value="OKAY">Okay</option>
            <option value="POOR">Poor</option>
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="journalNotes">Journal Notes</label>
          <textarea
            id="journalNotes"
            name="journalNotes"
            placeholder="Write a short note about your day..."
            value={formData.journalNotes}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="triggers">Triggers</label>
          <textarea
            id="triggers"
            name="triggers"
            placeholder="What affected your mood or stress today?"
            value={formData.triggers}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="copingStrategiesUsed">Coping Strategies Used</label>
          <textarea
            id="copingStrategiesUsed"
            name="copingStrategiesUsed"
            placeholder="What helped you cope today?"
            value={formData.copingStrategiesUsed}
            onChange={handleChange}
          />
        </div>

        {error && <p style={{ color: "red", marginTop: "12px" }}>{error}</p>}
        {successMessage && (
          <p style={{ color: "green", marginTop: "12px" }}>{successMessage}</p>
        )}

        <button className="primary-btn full-btn" type="submit" disabled={loading}>
          {loading ? "Saving..." : "Save Check-In"}
        </button>
      </form>
    </div>
  );
}