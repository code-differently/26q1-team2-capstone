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

  return (
    <div className="app-shell">
      <Navbar setPage={setPage} />

      <main className="page-content">
        {page === "home" && <HomePage setPage={setPage} />}
        {page === "dashboard" && <DashboardPage />}
        {page === "login" && <LoginPage />}
        {page === "signup" && <SignUp />}
        {page === "profile" && <ProfilePage />}
        {page === "about" && <AboutPage />}
      </main>
    </div>
  );
}




export default App;