// Simple auth service without token/session
export const login = async (email, password) => {
  // Call backend login endpoint
  const res = await fetch('http://localhost:8080/auth/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password })
  });
  const data = await res.json();
  return data; // just return backend response
};

export const register = async (user) => {
  const res = await fetch('http://localhost:8080/auth/register', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(user)
  });
  const data = await res.json();
  return data;
};