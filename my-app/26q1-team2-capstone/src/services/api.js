const API_BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8080";

export async function registerUser(userData) {
  const response = await fetch(`${API_BASE_URL}/api/auth/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(userData)
  });

  if (!response.ok) {
    throw new Error("Failed to register user");
  }

  return response.json();
}

export async function loginUser(credentials) {
  const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(credentials)
  });

  if (!response.ok) {
    throw new Error("Failed to login");
  }

  return response.json();
}

export async function getUserProfile(userId) {
  const response = await fetch(`${API_BASE_URL}/api/users/${userId}`);

  if (!response.ok) {
    throw new Error("Failed to fetch user profile");
  }

  return response.json();
}

export async function updateUserProfile(userId, data) {
  const response = await fetch(`${API_BASE_URL}/api/users/${userId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });

  if (!response.ok) {
    throw new Error("Failed to update user profile");
  }

  return response.json();
}

export async function createCheckIn(userId, data) {
  const response = await fetch(`${API_BASE_URL}/api/checkins/${userId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });

  if (!response.ok) {
    throw new Error("Failed to create check-in");
  }

  return response.json();
}

export async function getCheckIns(userId) {
  const response = await fetch(`${API_BASE_URL}/api/checkins/${userId}`);

  if (!response.ok) {
    throw new Error("Failed to fetch check-ins");
  }

  return response.json();
}

export async function getAiRecommendation(userId) {
  const response = await fetch(
    `${API_BASE_URL}/api/ai/recommendations/${userId}`,
    {
      method: "POST"
    }
  );

  if (!response.ok) {
    throw new Error("Failed to fetch AI recommendation");
  }

  return response.json();
}

export async function getResources() {
  const response = await fetch(`${API_BASE_URL}/api/resources`);

  if (!response.ok) {
    throw new Error("Failed to fetch resources");
  }

  return response.json();
}