export default function ResourceAlertCard({ message }) {
  return (
    <section className="card resource-card glass-card fade-up">
      <div className="section-heading">
        <div>
          <p className="section-kicker">Support</p>
          <h2>Helpful Resources</h2>
        </div>
      </div>

      {message ? (
        <div className="resource-alert-box pulse-soft">
          <p>{message}</p>
        </div>
      ) : (
        <div>
          <p className="muted">
            Support resources will show here when your recent check-in suggests
            extra care may help.
          </p>
          <ul className="resource-list">
            <li className="soft-list-item">
              Reach out to a trusted adult, counselor, or support person.
            </li>
            <li className="soft-list-item">
              Try a short grounding break, hydration, and rest.
            </li>
            <li className="soft-list-item">
              Use breathing, journaling, or a calm reset routine.
            </li>
          </ul>
        </div>
      )}
    </section>
  );
}