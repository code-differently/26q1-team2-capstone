import { useMemo, useState } from "react";
import "../styles/Dashboard.css";

export default function DashboardPage() {
  const [mood, setMood] = useState("");
  const [stress, setStress] = useState(5);
  const [sleep, setSleep] = useState("");
  const [journal, setJournal] = useState("");
  const [entries, setEntries] = useState([]);

  const moodToHeight = (moodValue) => {
    switch (moodValue) {
      case "Happy":
        return 95;
      case "Calm":
        return 80;
      case "Okay":
        return 60;
      case "Stressed":
        return 40;
      case "Sad":
        return 25;
      default:
        return 20;
    }
  };

  const latestEntry = entries.length > 0 ? entries[entries.length - 1] : null;

  const chartEntries = useMemo(() => {
    return entries.slice(-6);
  }, [entries]);

  const handleSave = () => {
    if (!mood || !sleep || !journal.trim()) {
      alert("Please complete mood, sleep, and journal notes before saving.");
      return;
    }

    const newEntry = {
      id: Date.now(),
      mood,
      stress: Number(stress),
      sleep,
      journal,
    };

    setEntries((prev) => [...prev, newEntry]);

    setMood("");
    setStress(5);
    setSleep("");
    setJournal("");
  };

  return (
    <div className="dashboard-page">
      <h1>NeuroNest Dashboard</h1>

      <div className="stats-row">
        <div className="stat-pill">
          Latest Mood <span>{latestEntry ? latestEntry.mood : "No data"}</span>
        </div>
        <div className="stat-pill">
          Stress Level <span>{latestEntry ? latestEntry.stress : "No data"}</span>
        </div>
        <div className="stat-pill">
          Sleep <span>{latestEntry ? latestEntry.sleep : "No data"}</span>
        </div>
      </div>

      <div className="dashboard-grid">
        <div className="dashboard-main">
          <div className="content-card">
            <h2>Daily Reflection</h2>
            <p>Log how you feel today.</p>

            <div className="form-group">
              <label htmlFor="mood">Mood</label>
              <select
                id="mood"
                value={mood}
                onChange={(e) => setMood(e.target.value)}
              >
                <option value="">Select your mood</option>
                <option value="Happy">Happy</option>
                <option value="Calm">Calm</option>
                <option value="Okay">Okay</option>
                <option value="Stressed">Stressed</option>
                <option value="Sad">Sad</option>
              </select>
            </div>

            <div className="form-group">
              <label htmlFor="stress">Stress Level: {stress}</label>
              <input
                id="stress"
                type="range"
                min="1"
                max="10"
                value={stress}
                onChange={(e) => setStress(Number(e.target.value))}
              />
            </div>

            <div className="form-group">
              <label htmlFor="sleep">Hours of Sleep</label>
              <input
                id="sleep"
                type="number"
                step="0.1"
                placeholder="Example: 8"
                value={sleep}
                onChange={(e) => setSleep(e.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="journal">Journal Notes</label>
              <textarea
                id="journal"
                placeholder="Write a short note about your day..."
                value={journal}
                onChange={(e) => setJournal(e.target.value)}
              />
            </div>

            <button className="primary-btn" onClick={handleSave}>
              Save Check-In
            </button>
          </div>

          <div className="content-card">
            <h2>AI Suggestions</h2>
            {latestEntry ? (
              <p>
                Based on your latest check-in, take a few slow breaths, drink
                some water, and keep noticing your patterns one day at a time.
              </p>
            ) : (
              <p>Complete a check-in to receive suggestions.</p>
            )}
          </div>
        </div>

        <div className="dashboard-side">
          <div className="content-card">
            <h2>Mood Trends</h2>

            <div className="live-chart">
              {chartEntries.length > 0 ? (
                chartEntries.map((entry) => (
                  <div key={entry.id} className="chart-column">
                    <div
                      className="dynamic-bar"
                      style={{ height: `${moodToHeight(entry.mood)}%` }}
                      title={`${entry.mood} | Stress ${entry.stress} | Sleep ${entry.sleep}`}
                    ></div>
                    <span className="chart-entry-label">{entry.mood}</span>
                  </div>
                ))
              ) : (
                <>
                  <div className="chart-column">
                    <div className="dynamic-bar empty-bar" style={{ height: "20%" }}></div>
                    <span className="chart-entry-label">-</span>
                  </div>
                  <div className="chart-column">
                    <div className="dynamic-bar empty-bar" style={{ height: "20%" }}></div>
                    <span className="chart-entry-label">-</span>
                  </div>
                  <div className="chart-column">
                    <div className="dynamic-bar empty-bar" style={{ height: "20%" }}></div>
                    <span className="chart-entry-label">-</span>
                  </div>
                  <div className="chart-column">
                    <div className="dynamic-bar empty-bar" style={{ height: "20%" }}></div>
                    <span className="chart-entry-label">-</span>
                  </div>
                </>
              )}
            </div>

            <p className="chart-status">
              {latestEntry
                ? `Latest entry saved: ${latestEntry.mood}`
                : "No check-ins yet."}
            </p>

            {entries.length > 0 && (
              <div className="history-box">
                <h3>Recent Check-Ins</h3>
                <ul className="history-list">
                  {entries
                    .slice()
                    .reverse()
                    .slice(0, 5)
                    .map((entry) => (
                      <li key={entry.id}>
                        <strong>{entry.mood}</strong> — Stress {entry.stress}, Sleep {entry.sleep}
                      </li>
                    ))}
                </ul>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}