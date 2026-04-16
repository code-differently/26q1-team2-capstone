import "../styles/Form.css";

export default function DailyCheckInForm() {
  return (
    <div className="content-card">
      <p className="section-label">Check-In</p>
      <h2>Daily Reflection</h2>
      <p>
        Log how you feel today and let NeuroNest help you spot patterns over
        time.
      </p>

      <div className="form-group">
        <label htmlFor="mood">Mood</label>
        <select id="mood">
          <option>Select your mood</option>
          <option>Happy</option>
          <option>Calm</option>
          <option>Okay</option>
          <option>Stressed</option>
          <option>Sad</option>
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="stress">Stress Level: 5</label>
        <input id="stress" type="range" min="1" max="10" defaultValue="5" />
      </div>

      <div className="form-group">
        <label htmlFor="sleep">Hours of Sleep</label>
        <input id="sleep" type="number" placeholder="Example: 8" />
      </div>

      <div className="form-group">
        <label htmlFor="journal">Journal Notes</label>
        <textarea
          id="journal"
          placeholder="Write a short note about your day..."
        ></textarea>
      </div>

      <button className="primary-btn full-btn">Save Check-In</button>
    </div>
  );
}