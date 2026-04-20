export default function AiSuggestionCard({ title = "AI-Generated Suggestion", suggestionData }) {
  return (
    <div className="card">
      <h2>{title}</h2>

      <p className="suggestion-text">
        {suggestionData?.suggestion || "No suggestion available yet."}
      </p>

      {suggestionData?.prompt ? (
        <div className="prompt-box">
          <p className="summary-label">AI Prompt Summary</p>
          <p>{suggestionData.prompt}</p>
        </div>
      ) : null}
    </div>
  );
}