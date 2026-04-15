import {
  AreaChart,
  Area,
  LineChart,
  Line,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer
} from "recharts";

const moodMap = {
  Overwhelmed: 2,
  Anxious: 4,
  Sad: 3,
  Okay: 6,
  Calm: 8,
  Happy: 10
};

export default function MoodTrendsDashboard({ data }) {
  const chartData = data
    .slice()
    .reverse()
    .map((entry) => ({
      date: entry.date,
      moodScore: moodMap[entry.mood] || 5,
      stress: Number(entry.stress),
      sleep: Number(entry.sleep)
    }));

  return (
    <section className="card dashboard-card glass-card fade-up">
      <div className="section-heading">
        <div>
          <p className="section-kicker">Wellness Overview</p>
          <h2>Mood Trends</h2>
        </div>
        <div className="soft-pill">Last {data.length || 0} entries</div>
      </div>

      {data.length === 0 ? (
        <div className="empty-state fancy-empty">
          <div className="empty-orb"></div>
          <p>No check-ins yet. Your chart will show after your first entry.</p>
        </div>
      ) : (
        <>
          <div className="chart-card">
            <h3>Mood Pattern</h3>
            <div className="chart-wrap">
              <ResponsiveContainer width="100%" height={260}>
                <AreaChart data={chartData}>
                  <defs>
                    <linearGradient id="moodGradient" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="5%" stopColor="#7bc8b6" stopOpacity={0.45} />
                      <stop offset="95%" stopColor="#7bc8b6" stopOpacity={0.04} />
                    </linearGradient>
                  </defs>
                  <CartesianGrid strokeDasharray="3 3" stroke="#d8ebe7" />
                  <XAxis dataKey="date" tick={{ fontSize: 12 }} stroke="#6c8b86" />
                  <YAxis domain={[0, 10]} tick={{ fontSize: 12 }} stroke="#6c8b86" />
                  <Tooltip />
                  <Area
                    type="monotone"
                    dataKey="moodScore"
                    stroke="#5fb6a3"
                    strokeWidth={3}
                    fill="url(#moodGradient)"
                  />
                </AreaChart>
              </ResponsiveContainer>
            </div>
          </div>

          <div className="chart-card">
            <h3>Stress Pattern</h3>
            <div className="chart-wrap">
              <ResponsiveContainer width="100%" height={240}>
                <LineChart data={chartData}>
                  <CartesianGrid strokeDasharray="3 3" stroke="#d8ebe7" />
                  <XAxis dataKey="date" tick={{ fontSize: 12 }} stroke="#6c8b86" />
                  <YAxis domain={[0, 10]} tick={{ fontSize: 12 }} stroke="#6c8b86" />
                  <Tooltip />
                  <Line
                    type="monotone"
                    dataKey="stress"
                    stroke="#7aaed6"
                    strokeWidth={3}
                    dot={{ r: 4 }}
                    activeDot={{ r: 6 }}
                  />
                </LineChart>
              </ResponsiveContainer>
            </div>
          </div>

          <div className="history-list">
            {data.map((entry) => (
              <article className="history-item slide-in-card" key={entry.id}>
                <div className="history-top">
                  <div>
                    <strong>{entry.date}</strong>
                    <p className="history-subtext">Daily reflection</p>
                  </div>
                  <span className="mood-badge">{entry.mood}</span>
                </div>

                <div className="stats-grid">
                  <div className="mini-stat">
                    <span className="mini-stat-label">Stress</span>
                    <strong>{entry.stress}/10</strong>
                  </div>
                  <div className="mini-stat">
                    <span className="mini-stat-label">Sleep</span>
                    <strong>{entry.sleep} hrs</strong>
                  </div>
                </div>

                <p className="history-notes">
                  <strong>Notes:</strong> {entry.notes || "No notes added."}
                </p>
              </article>
            ))}
          </div>
        </>
      )}
    </section>
  );
}