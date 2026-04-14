import React, { useMemo, useState } from "react";
import "./App.css";

const starterEntries = [
  {
    date: "Mon",
    mood: 6,
    stress: 7,
    anxiety: 6,
    sleep: 5,
    trigger: "School workload",
    note: "Felt overwhelmed in the afternoon.",
    coping: "Breathing exercise",
  },
  {
    date: "Tue",
    mood: 7,
    stress: 5,
    anxiety: 4,
    sleep: 7,
    trigger: "Busy schedule",
    note: "A little tired, but okay.",
    coping: "Walk outside",
  },
  {
    date: "Wed",
    mood: 5,
    stress: 8,
    anxiety: 7,
    sleep: 4,
    trigger: "Argument with friend",
    note: "Could not focus much today.",
    coping: "Journaled",
  },
];

const aiSuggestions = {
  low: [
    "Try a 5-minute breathing exercise.",
    "Take a short break and step away from stressful tasks.",
    "Write down one thing you can control today.",
  ],
  medium: [
    "A short walk or journaling session may help.",
    "Drink water and take a small reset break.",
    "Keep your routine steady and check in again later.",
  ],
  good: [
    "You seem more balanced today. Keep using what works.",
    "Consider writing down what helped today.",
    "A healthy routine can help maintain progress.",
  ],
};

const resources = [
  "Talk to a trusted adult, friend, teacher, or counselor.",
  "Reach out to a licensed mental health professional for added support.",
  "If you feel unsafe or are in immediate danger, contact local emergency services right away.",
];

function average(items, key) {
  if (!items.length) return 0;
  const total = items.reduce((sum, item) => sum + Number(item[key] || 0), 0);
  return (total / items.length).toFixed(1);
}

export default function App() {
  const [entries, setEntries] = useState(starterEntries);
  const [form, setForm] = useState({
    mood: 5,
    stress: 5,
    anxiety: 5,
    sleep: 5,
    trigger: "",
    note: "",
    coping: "",
  });

  const summary = useMemo(() => {
    const mood = Number(average(entries, "mood"));
    const stress = Number(average(entries, "stress"));
    const anxiety = Number(average(entries, "anxiety"));
    const sleep = Number(average(entries, "sleep"));

    let level = "good";
    if (mood <= 4 || stress >= 7 || anxiety >= 7) {
      level = "low";
    } else if (mood <= 6 || stress >= 5 || anxiety >= 5) {
      level = "medium";
    }

    return { mood, stress, anxiety, sleep, level };
  }, [entries]);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const newEntry = {
      ...form,
      date: "Today",
    };

    setEntries((prev) => [...prev, newEntry]);

    setForm({
      mood: 5,
      stress: 5,
      anxiety: 5,
      sleep: 5,
      trigger: "",
      note: "",
      coping: "",
    });
  };

  return (
    <div className="app">
      <section className="hero">
        <div className="hero-panel">
          <div className="hero-kicker">Mental Health Check-In & Support</div>
          <h1>Track how you feel and spot patterns over time</h1>
          <p className="hero-text">
            Log daily wellness check-ins, review emotional trends, and view
            AI-generated coping suggestions in a calm, supportive dashboard.
          </p>

          <div className="hero-stats">
            <div className="hero-stat">
              <span>Entries</span>
              <strong>{entries.length}</strong>
            </div>
            <div className="hero-stat">
              <span>Avg Mood</span>
              <strong>{summary.mood}</strong>
            </div>
            <div className="hero-stat">
              <span>Avg Sleep</span>
              <strong>{summary.sleep}</strong>
            </div>
          </div>
        </div>

        <aside className="disclaimer">
          <strong>Disclaimer:</strong> This app is not a substitute for
          professional mental health care. AI-generated suggestions are for
          general wellness support only.
        </aside>
      </section>

      <section className="dashboard">
        <div className="left-column">
          <div className="card">
            <div className="card-header">
              <h2>Daily Check-In</h2>
              <span className="badge badge-primary">Today</span>
            </div>

            <form onSubmit={handleSubmit}>
              <div className="range-wrap">
                <div className="range-top">
                  <span>Mood</span>
                  <span className="range-value">{form.mood}/10</span>
                </div>
                <input
                  type="range"
                  name="mood"
                  min="1"
                  max="10"
                  value={form.mood}
                  onChange={handleChange}
                />
              </div>

              <div className="range-wrap">
                <div className="range-top">
                  <span>Stress</span>
                  <span className="range-value">{form.stress}/10</span>
                </div>
                <input
                  type="range"
                  name="stress"
                  min="1"
                  max="10"
                  value={form.stress}
                  onChange={handleChange}
                />
              </div>

              <div className="range-wrap">
                <div className="range-top">
                  <span>Anxiety</span>
                  <span className="range-value">{form.anxiety}/10</span>
                </div>
                <input
                  type="range"
                  name="anxiety"
                  min="1"
                  max="10"
                  value={form.anxiety}
                  onChange={handleChange}
                />
              </div>

              <div className="range-wrap">
                <div className="range-top">
                  <span>Sleep Quality</span>
                  <span className="range-value">{form.sleep}/10</span>
                </div>
                <input
                  type="range"
                  name="sleep"
                  min="1"
                  max="10"
                  value={form.sleep}
                  onChange={handleChange}
                />
              </div>

              <label className="form-span-2">
                Trigger
                <input
                  type="text"
                  name="trigger"
                  value={form.trigger}
                  onChange={handleChange}
                  placeholder="What affected your mood today?"
                />
              </label>

              <label className="form-span-2">
                Journal Note
                <textarea
                  name="note"
                  rows="4"
                  value={form.note}
                  onChange={handleChange}
                  placeholder="Write a short reflection..."
                />
              </label>

              <label className="form-span-2">
                Coping Strategy Used
                <input
                  type="text"
                  name="coping"
                  value={form.coping}
                  onChange={handleChange}
                  placeholder="Breathing, music, walk, journaling..."
                />
              </label>

              <button type="submit">Save Check-In</button>
            </form>
          </div>

          <div className="card">
            <div className="card-header">
              <h2>Recent Check-Ins</h2>
              <span className="badge badge-secondary">History</span>
            </div>

            <div className="history-list">
              {entries
                .slice()
                .reverse()
                .map((entry, index) => (
                  <div className="history" key={`${entry.date}-${index}`}>
                    <div className="history-top">
                      <strong>{entry.date}</strong>
                      <span className="muted">Mood {entry.mood}/10</span>
                    </div>
                    <p>
                      <strong>Stress:</strong> {entry.stress} |{" "}
                      <strong>Anxiety:</strong> {entry.anxiety} |{" "}
                      <strong>Sleep:</strong> {entry.sleep}
                    </p>
                    <p>
                      <strong>Trigger:</strong> {entry.trigger || "None recorded"}
                    </p>
                    <p>
                      <strong>Note:</strong> {entry.note || "No note added"}
                    </p>
                    <p>
                      <strong>Coping:</strong>{" "}
                      {entry.coping || "No coping strategy recorded"}
                    </p>
                  </div>
                ))}
            </div>
          </div>
        </div>

        <div className="right-column">
          <div className="card">
            <div className="card-header">
              <h2>Wellness Snapshot</h2>
              <span className="badge badge-primary">Summary</span>
            </div>

            <div className="summary-grid">
              <div className="summary-box">
                <span>Average Mood</span>
                <strong>{summary.mood}</strong>
              </div>
              <div className="summary-box">
                <span>Average Stress</span>
                <strong>{summary.stress}</strong>
              </div>
              <div className="summary-box">
                <span>Average Anxiety</span>
                <strong>{summary.anxiety}</strong>
              </div>
              <div className="summary-box">
                <span>Average Sleep</span>
                <strong>{summary.sleep}</strong>
              </div>
            </div>

            <div className="insight">
              Based on recent check-ins, this dashboard can help surface mood
              trends, repeated triggers, and changes in sleep or stress over
              time.
            </div>
          </div>

          <div className="card ai-card">
            <div className="card-header">
              <h2>AI Suggestions</h2>
              <span className="badge badge-secondary">AI-generated</span>
            </div>

            <ul className="ai-list">
              {aiSuggestions[summary.level].map((tip) => (
                <li key={tip}>{tip}</li>
              ))}
            </ul>
          </div>

          <div className="card">
            <div className="card-header">
              <h2>Support Resources</h2>
              <span className="badge badge-secondary">Help</span>
            </div>

            <ul className="resource-list">
              {resources.map((item) => (
                <li key={item}>{item}</li>
              ))}
            </ul>
          </div>
        </div>
      </section>
    </div>
  );
}
