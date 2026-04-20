import { useEffect, useState } from "react";
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
  const [wellnessState, setWellnessState] = useState({
    goal: "",
    aiSuggestion: "",
    lastPrompt: "",
  });

  useEffect(() => {
    const savedUser = localStorage.getItem("authUser");
    const savedWellnessState = localStorage.getItem("wellnessState");

    if (savedUser) {
      try {
        const parsedUser = JSON.parse(savedUser);
        setAuthUser(parsedUser);
      } catch (error) {
        console.error("Failed to parse saved auth user:", error);
        localStorage.removeItem("authUser");
      }
    }

    if (savedWellnessState) {
      try {
        const parsedWellnessState = JSON.parse(savedWellnessState);
        setWellnessState(parsedWellnessState);
      } catch (error) {
        console.error("Failed to parse saved wellness state:", error);
        localStorage.removeItem("wellnessState");
      }
    }
  }, []);

  useEffect(() => {
    localStorage.setItem("wellnessState", JSON.stringify(wellnessState));
  }, [wellnessState]);

  function normalizeUserFromResponse(response) {
    const userData = response?.user || response;

    return {
      id: userData?.userId ?? userData?.id ?? null,
      firstName: userData?.firstName ?? "",
      lastName: userData?.lastName ?? "",
      name:
        userData?.name ??
        `${userData?.firstName ?? ""} ${userData?.lastName ?? ""}`.trim(),
      email: userData?.email ?? "",
      goal: userData?.goal ?? "",
    };
  }

  function handleLoginSuccess(response) {
    const user = normalizeUserFromResponse(response);

    if (!user.id) {
      console.error("No user ID returned from login response:", response);
      return;
    }

    setAuthUser(user);
    localStorage.setItem("authUser", JSON.stringify(user));

    setWellnessState((prev) => ({
      ...prev,
      goal: user.goal || prev.goal || "",
    }));

    setPage("dashboard");
  }

  function handleRegisterSuccess() {
    setPage("login");
  }

  function handleLogout() {
    setAuthUser(null);
    setWellnessState({
      goal: "",
      aiSuggestion: "",
      lastPrompt: "",
    });
    localStorage.removeItem("authUser");
    localStorage.removeItem("wellnessState");
    setPage("home");
  }

  function handleGoalUpdate(newGoal) {
    setWellnessState((prev) => ({
      ...prev,
      goal: newGoal,
    }));

    setAuthUser((prev) =>
      prev
        ? {
            ...prev,
            goal: newGoal,
          }
        : prev
    );

    const savedUser = localStorage.getItem("authUser");
    if (savedUser) {
      try {
        const parsedUser = JSON.parse(savedUser);
        localStorage.setItem(
          "authUser",
          JSON.stringify({
            ...parsedUser,
            goal: newGoal,
          })
        );
      } catch (error) {
        console.error("Failed to update saved auth user:", error);
      }
    }
  }

  function handleAiUpdate({ suggestion, prompt }) {
    setWellnessState((prev) => ({
      ...prev,
      aiSuggestion: suggestion || "",
      lastPrompt: prompt || "",
    }));
  }

  return (
    <div className="app-shell">
      <Navbar
        setPage={setPage}
        authUser={authUser}
        onLogout={handleLogout}
      />

      <main className="page-content">
        {page === "home" && (
          <HomePage setPage={setPage} authUser={authUser} />
        )}

        {page === "dashboard" &&
          (authUser ? (
            <DashboardPage
              userId={authUser.id}
              authUser={authUser}
              wellnessState={wellnessState}
              onGoalUpdate={handleGoalUpdate}
              onAiUpdate={handleAiUpdate}
            />
          ) : (
            <LoginPage onLoginSuccess={handleLoginSuccess} />
          ))}

        {page === "login" && (
          <LoginPage onLoginSuccess={handleLoginSuccess} />
        )}

        {page === "signup" && (
          <SignUp onRegisterSuccess={handleRegisterSuccess} />
        )}

        {page === "profile" &&
          (authUser ? (
            <ProfilePage
              authUser={authUser}
              userId={authUser.id}
              wellnessState={wellnessState}
              onGoalUpdate={handleGoalUpdate}
              onAiUpdate={handleAiUpdate}
            />
          ) : (
            <LoginPage onLoginSuccess={handleLoginSuccess} />
          ))}
      </main>

      <Footer />
    </div>
  );
}

export default App;