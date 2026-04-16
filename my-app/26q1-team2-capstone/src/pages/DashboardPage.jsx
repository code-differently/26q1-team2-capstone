import "../styles/Dashboard.css";

export default function DashboardPage() {
  return (
    <div className="dashboard-page">

     
      <h1>NeuroNest Dashboard</h1>

     
      <div className="stats-row">
        <div className="stat-pill">Latest Mood</div>
        <div className="stat-pill">Stress Level</div>
        <div className="stat-pill">Sleep</div>
      </div>

    
      <div className="dashboard-grid">

       
        <div className="dashboard-main">

          <div className="content-card">
            <h2>Daily Reflection</h2>
            <p>Log how you feel today.</p>
            <button className="primary-btn">Save Check-In</button>
          </div>

          <div className="content-card">
            <h2>AI Suggestions</h2>
            <p>Complete a check-in to receive suggestions.</p>
          </div>

        </div>

       
        <div className="dashboard-side">
          <div className="content-card">
            <h2>Mood Trends</h2>

            <div className="chart">
              <div className="bar" style={{ height: "50%" }}></div>
              <div className="bar" style={{ height: "70%" }}></div>
              <div className="bar" style={{ height: "40%" }}></div>
              <div className="bar" style={{ height: "80%" }}></div>
              <div className="bar" style={{ height: "60%" }}></div>
            </div>

          </div>
        </div>

      </div>

    </div>
  );
}