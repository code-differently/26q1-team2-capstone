export default function AiSuggestionCard({ suggestions }) {
  return (
    <section className="card suggestion-card glass-card fade-up">
      <div className="section-heading">
        <div>
          <p className="section-kicker">AI Support</p>
          <h2>AI-Generated Suggestions</h2>
        </div>
      </div>

      <p className="muted">
        These suggestions are AI-generated support ideas based on your latest
        check-in.
      </p>

      {suggestions.length === 0 ? (
        <div className="empty-state fancy-empty">
          <div className="empty-orb"></div>
          <p>Complete a check-in to receive calming suggestions.</p>
        </div>
      ) : (
        <ul className="suggestion-list">
          {suggestions.map((tip, index) => (
            <li key={`${tip}-${index}`} className="soft-list-item">
              {tip}
            </li>
          ))}
        </ul>
      )}
    </section>
  );
}