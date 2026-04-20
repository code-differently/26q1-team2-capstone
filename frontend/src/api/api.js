const API_BASE_URL = "http://localhost:8080/api";

async function handleResponse(response) {
  if (!response.ok) {
    let message = "Request failed";

    try {
      const errorData = await response.json();
      message = errorData.message || message;
    } catch {
      try {
        message = await response.text();
      } catch {
        message = "Request failed";
      }
    }

    throw new Error(message);
  }

  const contentType = response.headers.get("content-type");

  if (contentType && contentType.includes("application/json")) {
    return response.json();
  }

  return {};
}

export async function registerUser(data) {
  const response = await fetch(`${API_BASE_URL}/auth/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  return handleResponse(response);
}

export async function loginUser(data) {
  const response = await fetch(`${API_BASE_URL}/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  return handleResponse(response);
}

export async function getUserProfile(userId) {
  const response = await fetch(`${API_BASE_URL}/users/${userId}`);
  return handleResponse(response);
}

export async function updateUserGoal(userId, goal) {
  const response = await fetch(`${API_BASE_URL}/users/${userId}/goal`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ goal }),
  });

  return handleResponse(response);
}

export async function getAiRecommendation(data) {
  const response = await fetch(`${API_BASE_URL}/ai/recommendation`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  return handleResponse(response);
}

export async function submitCheckIn(userId, data) {
  const response = await fetch(`${API_BASE_URL}/checkins/${userId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  return handleResponse(response);
}

export async function getCheckIns(userId) {
  const response = await fetch(`${API_BASE_URL}/checkins/${userId}`);
  return handleResponse(response);
}

export async function getSupportResources() {
  const response = await fetch(`${API_BASE_URL}/resources`);
  return handleResponse(response);
}