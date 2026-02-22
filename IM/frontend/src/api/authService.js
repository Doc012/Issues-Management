/**
 * Mock authentication service.
 * Simulates login/register with a hardcoded user stored in localStorage.
 */

const STORAGE_KEY = 'issuetracker_users';
const SESSION_KEY = 'issuetracker_session';

// Seed a default admin user
const DEFAULT_USERS = [
  {
    id: 1,
    name: 'Admin User',
    email: 'admin@test.com',
    password: 'password123',
  },
];

const getUsers = () => {
  const raw = localStorage.getItem(STORAGE_KEY);
  if (!raw) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(DEFAULT_USERS));
    return [...DEFAULT_USERS];
  }
  return JSON.parse(raw);
};

const saveUsers = (users) => {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(users));
};

/**
 * Simulate network delay (200-500ms).
 */
const delay = (ms = 400) => new Promise((r) => setTimeout(r, ms));

/**
 * Login with email & password.
 * Returns { user } on success, throws on failure.
 */
export const login = async (email, password) => {
  await delay();
  const users = getUsers();
  const user = users.find(
    (u) => u.email.toLowerCase() === email.toLowerCase() && u.password === password,
  );
  if (!user) {
    throw new Error('Invalid email or password.');
  }
  const session = { id: user.id, name: user.name, email: user.email };
  localStorage.setItem(SESSION_KEY, JSON.stringify(session));
  return session;
};

/**
 * Register a new user.
 * Returns { user } on success, throws if email already taken.
 */
export const register = async (name, email, password) => {
  await delay();
  const users = getUsers();
  const exists = users.some((u) => u.email.toLowerCase() === email.toLowerCase());
  if (exists) {
    throw new Error('An account with this email already exists.');
  }
  const newUser = {
    id: Date.now(),
    name,
    email,
    password,
  };
  users.push(newUser);
  saveUsers(users);
  const session = { id: newUser.id, name: newUser.name, email: newUser.email };
  localStorage.setItem(SESSION_KEY, JSON.stringify(session));
  return session;
};

/**
 * Get the currently logged-in user from localStorage, or null.
 */
export const getCurrentUser = () => {
  const raw = localStorage.getItem(SESSION_KEY);
  return raw ? JSON.parse(raw) : null;
};

/**
 * Log out — clears session.
 */
export const logout = () => {
  localStorage.removeItem(SESSION_KEY);
};
