import { useState } from "react";
import { validateForm } from "../utils/validation";

const initialForm = {
  mood: "",
  stress: 5,
  sleep: "",
  notes: ""
};

export default function DailyCheckInForm({ onSubmit }) {
  const [form, setForm] = useState(initialForm);
  const [error, setError] = useState("");

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const validationMessage = validateForm(form);
    if (validationMessage) {
      setError(validationMessage);
      return;
    }

    setError("");
    onSubmit(form);
    setForm(initialForm);
  };

  return (
    <section className="card form-card glass-card fade-up">
      <div className="section-heading">
        <div>
          <p className="section-kicker">Check-In</p>
          <h2>Daily Reflection</h2>
        </div>
      </div>

      <p className="muted">
        Log how you feel today and let NeuroNest help you spot patterns over time.
      </p>

      <form onSubmit={handleSubmit} className="checkin-form">
        <label>
          Mood
          <select name="mood" value={form.mood} onChange={handleChange}>
            <option value="">Select your mood</option>
            <option value="Happy">Happy</option>
            <option value="Calm">Calm</option>
            <option value="Okay">Okay</option>
            <option value="Sad">Sad</option>
            <option value="Anxious">Anxious</option>
            <option value="Overwhelmed">Overwhelmed</option>
          </select>
        </label>

        <label>
          Stress Level: <strong>{form.stress}</strong>
          <input
            type="range"
            name="stress"
            min="1"
            max="10"
            value={form.stress}
            onChange={handleChange}
          />
        </label>

        <label>
          Hours of Sleep
          <input
            type="number"
            name="sleep"
            min="0"
            max="24"
            step="0.5"
            value={form.sleep}
            onChange={handleChange}
            placeholder="Example: 7.5"
          />
        </label>

        <label>
          Journal Notes
          <textarea
            name="notes"
            rows="4"
            value={form.notes}
            onChange={handleChange}
            placeholder="Write a short note about your day..."
          />
        </label>

        {error && <p className="error-text">{error}</p>}

        <button type="submit" className="primary-btn glow-btn">
          Save Check-In
        </button>
      </form>
    </section>
  );
}