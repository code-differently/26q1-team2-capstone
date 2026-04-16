import { useState } from "react";
import Navbar from "./components/Navbar";
import DashboardPage from "./pages/DashboardPage";
import LoginPage from "./pages/LoginPage";
import SignUp from "./pages/SignUp";
import HomePage from "./pages/HomePage";
import ProfilePage from "./pages/ProfilePage";

function App() {
  const [page, setPage] = useState("dashboard");

  return (
    <div className="app-shell">
      <Navbar setPage={setPage} />

      <main className="page-content">
        {page === "home" && <HomePage />}
        {page === "dashboard" && <DashboardPage />}
        {page === "login" && <LoginPage />}
        {page === "signup" && <SignUp />}
        {page === "profile" && <ProfilePage />}
      </main>
    </div>
  );
}


export default App;