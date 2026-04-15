export function buildSuggestionsAndAlert(entry) {
  const suggestions = [];
  let resourceAlert = "";

  const stress = Number(entry.stress);
  const sleep = Number(entry.sleep);
  const mood = entry.mood;

  if (stress >= 8) {
    suggestions.push("Try a short breathing or grounding exercise.");
    suggestions.push("Take a calm break and reduce extra stimulation.");
  }

  if (sleep > 0 && sleep < 6) {
    suggestions.push("Aim for a more restful bedtime routine tonight.");
  }

  if (mood === "Sad" || mood === "Anxious" || mood === "Overwhelmed") {
    suggestions.push("Write down one thing you can control right now.");
    suggestions.push("Reach out to someone you trust for support.");
  }

  if (suggestions.length === 0) {
    suggestions.push("Keep tracking your wellness and checking in daily.");
    suggestions.push("Make space today for rest, water, and reflection.");
  }

  if (stress >= 9 || mood === "Overwhelmed") {
    resourceAlert =
      "Your latest check-in suggests you may need extra support today. Consider reaching out to a trusted person, counselor, or local support resource.";
  }

  return { suggestions, resourceAlert };
}