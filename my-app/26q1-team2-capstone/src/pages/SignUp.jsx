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
    if (!formData.firstName.trim()) {
      return "First name is required.";
    }

    if (!formData.lastName.trim()) {
      return "Last name is required.";
    }

    if (!formData.email.trim()) {
      return "Email is required.";
    }

    if (!formData.password.trim()) {
      return "Password is required.";
    }

    if (formData.password.length < 6) {
      return "Password must be at least 6 characters long.";
    }

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

      setSuccessMessage("Registration successful.");

      if (onRegisterSuccess) {
        onRegisterSuccess(response);
      }

      setFormData({
        firstName: "",
        lastName: "",
        email: "",
        password: ""
      });
    } catch (registerError) {
      console.error("Registration error:", registerError);
      setError("Registration failed. Please try a different email.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <section className="page-section center-page">
      <div className="auth-card">
        <h2>Create your NeuroNest account</h2>
        <p>Start tracking your wellness journey with a secure account.</p>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="firstName">First Name</label>
            <input
              id="firstName"
              name="firstName"
              type="text"
              placeholder="Enter your first name"
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
              placeholder="Enter your last name"
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
              placeholder="Enter your email"
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
              placeholder="Create a password"
              value={formData.password}
              onChange={handleChange}
            />
          </div>

          {error && <p style={{ color: "red", marginTop: "12px" }}>{error}</p>}
          {successMessage && (
            <p style={{ color: "green", marginTop: "12px" }}>{successMessage}</p>
          )}

          <button className="primary-btn full-btn" type="submit" disabled={loading}>
            {loading ? "Creating Account..." : "Sign Up"}
          </button>
        </form>
      </div>
    </section>
  );
}