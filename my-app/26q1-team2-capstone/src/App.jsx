import { useState } from "react";
import "./styles/App.css";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import HomePage from "./pages/HomePage";
import DashboardPage from "./pages/DashboardPage";
import LoginPage from "./pages/LoginPage";
import SignUp from "./pages/SignUp";
import ProfilePage from "./pages/ProfilePage";

function App() {
  const [page, setPage] = useState("home");
  const [authUser, setAuthUser] = useState(null);

  function handleLoginSuccess(response) {
    console.log("Login response:", response);

    setAuthUser({
      email: response.message || "Logged In User"
    });

    setPage("dashboard");
  }

  function handleRegisterSuccess(response) {
    console.log("Register response:", response);
    setPage("login");
  }

  return (
    <div className="app-shell">
      <Navbar setPage={setPage} />

      <main className="page-content">
        {page === "home" && <HomePage />}
        {page === "dashboard" && <DashboardPage userId={1} />}
        {page === "login" && (
          <LoginPage onLoginSuccess={handleLoginSuccess} />
        )}
        {page === "signup" && (
          <SignUp onRegisterSuccess={handleRegisterSuccess} />
        )}
        {page === "profile" && <ProfilePage authUser={authUser} />}
      </main>

      <Footer />
    </div>
  );
}

export default App;
