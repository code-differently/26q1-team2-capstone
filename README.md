# 26q1-team2-capstone

# 🧠 Mental Health Tracker

<p align="center">
  A full-stack application for tracking mental wellness, powered by AI insights.
</p>

---

## 👥 Team
- Glenn Tyson
- Mesheik
- Amani

---

## 📖 Overview
**Mental Health Tracker** is a full-stack wellness application designed to help users monitor and improve their mental health through consistent daily check-ins, intelligent insights, and long-term trend tracking.

By combining structured inputs (mood, stress, sleep) with AI-powered recommendations, the platform provides users with actionable guidance to build healthier habits and gain deeper self-awareness over time.

---

## 🚀 Live Demo
- **Frontend:** https://caring-embrace-production.up.railway.app
- **Backend API:** https://26q1-team2-capstone-production.up.railway.app

---

## 🛠️ Tech Stack

| Layer       | Technology |
|------------|-----------|
| Frontend   | React (Vite) |
| Backend    | Spring Boot (Java) |
| Database   | MySQL (Railway) |
| AI         | OpenAI API |

---

## ✨ Features

### 🔐 Authentication
- Secure user registration and login
- Protected user sessions

### 📅 Daily Check-Ins
- Track:
    - Mood
    - Stress levels
    - Sleep quality
- Simple and intuitive input system

### 🤖 AI-Powered Recommendations
- Personalized insights based on user data
- Encourages positive mental health habits

### 🎯 Goal Tracking
- Set and monitor personal wellness goals
- Reinforces consistency and accountability

### 📊 Persistent Data Storage
- Data stored securely in MySQL
- Enables long-term tracking and insights



## ⚙️ Installation

### 1. Clone Repository
```bash
git clone https://github.com/your-repo/mental-health-tracker.git
cd mental-health-tracker
2. Backend Setup (Spring Boot)
cd backend
./mvnw spring-boot:run

Configure your database in application.properties:

spring.datasource.url=jdbc:mysql://<your-db-host>:3306/<db-name>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update

3. Frontend Setup (React + Vite)
cd frontend
npm install
npm run dev

Create a .env file:

VITE_API_BASE_URL=http://localhost:8080

⚠️ Known Issues
AI recommendations may be less detailed with minimal user input
Minor UI inconsistencies on smaller mobile screens

🗺️ Roadmap
📈 Mood analytics dashboard (charts & trends)
🔔 Smart notifications & reminders
📱 Full mobile optimization
🔥 Habit tracking with streak system
🧠 Enhanced AI personalization
🏆 Why This Project Stands Out
Combines AI + mental health tracking in a meaningful way
Focuses on real-world impact and user well-being
Demonstrates full-stack engineering skills:
Frontend UI/UX
Backend API design
Database integration
Full AI implementation

🧱 Architecture (High-Level)
User (Browser)
     ↓
React Frontend (Vite)
     ↓
Spring Boot API
     ↓
MySQL Database (Railway)
     ↓
OpenAI API (AI Recommendations)
🙌 Credits
OpenAI API
Railway (Deployment & Database Hosting)
React & Spring Boot communities

⭐ If you found this project interesting, feel free to star the repo!