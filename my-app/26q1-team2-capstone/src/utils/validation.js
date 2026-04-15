export function validateForm(form) {
  if (!form.mood) {
    return "Please select a mood.";
  }

  if (form.sleep === "") {
    return "Please enter your hours of sleep.";
  }

  if (Number(form.sleep) < 0) {
    return "Sleep hours cannot be negative.";
  }

  return "";
}