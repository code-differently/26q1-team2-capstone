import { useState } from "react";
import { loginUser } from "../api/api";

export default function LoginPage({ onLoginSuccess }) {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  function handleChange(event) {
    const { name, value } = event.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  function validateForm() {
    if (!formData.email.trim()) return "Email is required.";
    if (!formData.password.trim()) return "Password is required.";
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
      const response = await loginUser(formData);

      setSuccessMessage(response?.message || "Login successful.");

      if (onLoginSuccess) {
        onLoginSuccess(response);
      }

      setFormData({
        email: "",
        password: "",
      });
    } catch (err) {
      console.error("Login error:", err);
      setError(err.message || "Login failed");
    } finally {
      setLoading(false);
    }
  }

  return (
    <section className="page-section center-page">
      <div className="auth-card">
        <h2>Welcome back</h2>
        <p>Log in to view your dashboard.</p>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="loginEmail">Email</label>
            <input
              id="loginEmail"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label htmlFor="loginPassword">Password</label>
            <input
              id="loginPassword"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
            />
          </div>

          {error && <p style={{ color: "red", marginTop: "12px" }}>{error}</p>}
          {successMessage && (
            <p style={{ color: "green", marginTop: "12px" }}>
              {successMessage}
            </p>
          )}

          <button type="submit" className="primary-btn full-btn" disabled={loading}>
            {loading ? "Signing In..." : "Login"}
          </button>
        </form>
      </div>
    </section>
  );
}