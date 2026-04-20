import { useEffect, useMemo, useState } from "react";
import { getCheckIns } from "../api/api";

export default function MoodTrendsDashboard({ userId }) {
  const [entries, setEntries] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadCheckIns() {
      if (!userId) return;

      try {
        setLoading(true);
        setError("");

        const data = await getCheckIns(userId);
        setEntries(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error("Failed to load check-ins:", err);
        setError(err.message || "Failed to load mood history.");
      } finally {
        setLoading(false);
      }
    }

    loadCheckIns();
  }, [userId]);

  const chartData = useMemo(() => {
    return entries
      .slice(0, 7)
      .reverse()
      .map((entry, index) => ({
        label: entry.createdAt
          ? new Date(entry.createdAt).toLocaleDateString()
          : `Entry ${index + 1}`,
        moodScore: Number(entry.moodScore || 0),
        stressLevel: Number(entry.stressLevel || 0),
        sleepQuality: entry.sleepQuality || ""
      }));
  }, [entries]);

  return (
    <div className="content-card">
      <p className="section-label">Progress</p>
      <h2>Mood Trends</h2>

      {loading && <p>Loading chart...</p>}
      {error && !loading && <p>{error}</p>}

      {!loading && !error && chartData.length === 0 && (
        <p>No check-ins yet. Submit your first daily reflection.</p>
      )}

      {!loading && !error && chartData.length > 0 && (
        <>
          <div className="live-chart">
            {chartData.map((entry, index) => (
              <div className="chart-column" key={index}>
                <div
                  className="dynamic-bar"
                  style={{ height: `${entry.moodScore * 18}px` }}
                  title={`Mood: ${entry.moodScore}`}
                />
                <div className="chart-entry-label">{entry.label}</div>
              </div>
            ))}
          </div>

          <div className="history-box">
            <h3>Recent Entries</h3>
            <ul className="history-list">
              {entries.slice(0, 5).map((entry) => (
                <li key={entry.id}>
                  Mood: {entry.moodScore}, Stress: {entry.stressLevel}, Sleep:{" "}
                  {entry.sleepQuality}
                </li>
              ))}
            </ul>
          </div>
        </>
      )}
    </div>
  );
}