export default function ResourceAlertCard() {
  return (
    <div className="content-card">
      <p className="section-label">Support</p>
      <h2>Helpful Resources</h2>
      <p>
        Support resources will show here when your recent check-in suggests
        extra care may help.
      </p>

      <ul className="resource-list">
        <li>Reach out to a trusted adult, counselor, or support person.</li>
        <li>Try a short grounding break, hydration, and rest.</li>
        <li>Use breathing, journaling, or a calm reset routine.</li>
      </ul>
    </div>
  );
}