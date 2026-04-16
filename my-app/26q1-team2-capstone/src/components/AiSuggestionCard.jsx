export default function AiSuggestionCard({ suggestionData }) {
  const suggestion =
    suggestionData?.suggestion ||
    "Complete a daily check-in to receive an AI-generated coping suggestion.";

  const label =
    suggestionData?.label ||
    "AI-Generated Suggestion";

  return (
    <div className="content-card">
      <p className="section-label">{label}</p>
      <h2>Supportive Guidance</h2>
      <p>{suggestion}</p>
    </div>
  );
}