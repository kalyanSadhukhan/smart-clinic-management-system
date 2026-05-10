// Common API helpers
const API_URL = 'http://localhost:8080/api';

function getToken() {
    return localStorage.getItem('token');
}

function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    window.location.href = '/patient-login.html';
}

async function apiFetch(endpoint, options = {}) {
    const token = getToken();
    const headers = {
        'Content-Type': 'application/json',
        ...(token ? { 'Authorization': `Bearer ${token}` } : {})
    };

    const response = await fetch(`${API_URL}${endpoint}`, {
        ...options,
        headers
    });
    return response.json();
}
