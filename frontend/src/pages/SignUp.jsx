import { useState } from "react";
import { registerUser } from "../api/api";

export default function SignUp({ onRegisterSuccess }) {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: ""
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  function handleChange(event) {
    const { name, value } = event.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  }

  function validateForm() {
    if (!formData.firstName.trim()) return "First name is required.";
    if (!formData.lastName.trim()) return "Last name is required.";
    if (!formData.email.trim()) return "Email is required.";
    if (!formData.password.trim()) return "Password is required.";
    if (formData.password.length < 6) return "Password must be at least 6 characters.";
    return "";
  }

  async function handleSubmit(event) {
    event.preventDefault();
    setError("");
    setSuccessMessage("");

    const validationError = validateForm();
    if (validationError) {
      setError(validationError);
      return;
    }

    try {
      setLoading(true);

      const response = await registerUser(formData);
      setSuccessMessage(response?.message || "Registration successful.");

      setFormData({
        firstName: "",
        lastName: "",
        email: "",
        password: ""
      });

      if (onRegisterSuccess) {
        onRegisterSuccess(response);
      }
    } catch (err) {
      console.error("Signup error:", err);
      setError(err.message || "Signup failed");
    } finally {
      setLoading(false);
    }
  }

  return (
    <section className="page-section center-page">
      <div className="auth-card">
        <h2>Create your account</h2>
        <p>Start your NeuroNest wellness journey.</p>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="firstName">First Name</label>
            <input
              id="firstName"
              name="firstName"
              type="text"
              value={formData.firstName}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label htmlFor="lastName">Last Name</label>
            <input
              id="lastName"
              name="lastName"
              type="text"
              value={formData.lastName}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input
              id="email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input
              id="password"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
            />
          </div>

          {error && <p style={{ color: "red", marginTop: "12px" }}>{error}</p>}
          {successMessage && (
            <p style={{ color: "green", marginTop: "12px" }}>{successMessage}</p>
          )}

          <button type="submit" className="primary-btn full-btn" disabled={loading}>
            {loading ? "Creating Account..." : "Sign Up"}
          </button>
        </form>
      </div>
    </section>
  );
}