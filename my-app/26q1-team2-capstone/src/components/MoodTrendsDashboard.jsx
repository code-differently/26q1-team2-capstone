export default function MoodTrendsDashboard() {
  return (
    <div className="content-card trend-card">
      <p className="section-label">Wellness Overview</p>
      <h2>Mood Trends</h2>
      <p className="trend-subtext">Last 0 entries</p>

      <div className="chart-box">
        <div className="chart-bars">
          <div className="chart-bar" style={{ height: "45%" }}></div>
          <div className="chart-bar" style={{ height: "70%" }}></div>
          <div className="chart-bar" style={{ height: "55%" }}></div>
          <div className="chart-bar" style={{ height: "82%" }}></div>
          <div className="chart-bar" style={{ height: "60%" }}></div>
          <div className="chart-bar" style={{ height: "76%" }}></div>
          <div className="chart-bar" style={{ height: "50%" }}></div>
        </div>

        <div className="chart-labels">
          <span>M</span>
          <span>T</span>
          <span>W</span>
          <span>T</span>
          <span>F</span>
          <span>S</span>
          <span>S</span>
        </div>
      </div>

      <div className="empty-state">No check-ins yet.</div>
    </div>
  );
}