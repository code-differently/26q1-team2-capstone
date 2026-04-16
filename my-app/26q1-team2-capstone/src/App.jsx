import { useState } from "react";
import Navbar from "./components/Navbar";
import DashboardPage from "./pages/DashboardPage";
import LoginPage from "./pages/LoginPage";
import SignUp from "./pages/SignUp";
import HomePage from "./pages/HomePage";
import ProfilePage from "./pages/ProfilePage";
import AboutPage from "./pages/AboutPage";

function App() {
  const [page, setPage] = useState("home");
  const [loggedIn, setLoggedIn] = useState(false);

  const handleSignIn = () => {
    setLoggedIn(true);
    setPage("dashboard");
  };

  const handleCreateAccount = () => {
    setLoggedIn(true);
    setPage("dashboard");
  };

  const handleLogout = () => {
    setLoggedIn(false);
    setPage("home");
  };

  return (
    <div className="app-shell">
      <Navbar
        setPage={setPage}
        loggedIn={loggedIn}
        onLogout={handleLogout}
      />

      <main className="page-content">
        {page === "home" && <HomePage setPage={setPage} />}
        {page === "about" && <AboutPage />}
        {page === "dashboard" && <DashboardPage />}
        {page === "login" && (
          <LoginPage
            onSignIn={handleSignIn}
            setPage={setPage}
          />
        )}
        {page === "signup" && (
          <SignUp
            onCreateAccount={handleCreateAccount}
            setPage={setPage}
          />
        )}
        {page === "profile" && <ProfilePage />}
      </main>
    </div>
  );
}

export default App;




