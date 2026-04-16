import { useEffect, useState } from "react";
import { getCheckIns } from "../api/api";

export default function MoodTrendsDashboard({ userId = 1 }) {
  const [checkIns, setCheckIns] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadCheckIns() {
      try {
        setLoading(true);
        setError("");

        const data = await getCheckIns(userId);
        setCheckIns(data);
      } catch (err) {
        console.error("Failed to load check-ins:", err);
        setError("Failed to load mood trends.");
      } finally {
        setLoading(false);
      }
    }

    loadCheckIns();
  }, [userId]);

  const recentCheckIns = checkIns.slice(-7);

  return (
    <div className="content-card trend-card">
      <p className="section-label">Wellness Overview</p>
      <h2>Mood Trends</h2>
      <p className="trend-subtext">
        Last {recentCheckIns.length} entr{recentCheckIns.length === 1 ? "y" : "ies"}
      </p>

      {loading && <div className="empty-state">Loading mood trends...</div>}

      {error && !loading && <div className="empty-state">{error}</div>}

      {!loading && !error && recentCheckIns.length > 0 && (
        <div className="chart-box">
          <div className="chart-bars">
            {recentCheckIns.map((entry) => (
              <div
                key={entry.id}
                className="chart-bar"
                style={{ height: `${entry.moodScore * 10}%` }}
                title={`Mood: ${entry.moodScore}, Stress: ${entry.stressLevel}`}
              ></div>
            ))}
          </div>

          <div className="chart-labels">
            {recentCheckIns.map((entry, index) => (
              <span key={entry.id}>
                {index + 1}
              </span>
            ))}
          </div>
        </div>
      )}

      {!loading && !error && recentCheckIns.length === 0 && (
        <div className="empty-state">No check-ins yet.</div>
      )}
    </div>
  );
}