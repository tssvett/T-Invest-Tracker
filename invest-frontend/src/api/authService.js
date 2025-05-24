import axios from './axiosConfig';
import { jwtDecode } from 'jwt-decode';

const login = async (username, password) => {
  try {
    console.log("logging...");
    const response = await axios.post('/auth/login', {
      login: username,
      password: password
    });
    console.log("logging response: ", response);

    const decoded = jwtDecode(response.data.accessToken);
    if (response.data.accessToken) {
      localStorage.setItem('token', response.data.accessToken);
      localStorage.setItem('user', decoded.userId);
    }
    console.log("successful auth, response: ", response.data);
    return { user: decoded.userId, token: response.data.accessToken };
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
};

const getCurrentUser = () => {
  return localStorage.getItem('user');
};

const isAuthenticated = () => {
  return !!localStorage.getItem('token');
};

const register = async (login, email, password) => {
  try {
    const response = await axios.post('/auth/register', {
      email: email,
      login: login,
      password: password
    });
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

const authService = {
  login,
  logout,
  register,
  getCurrentUser,
  isAuthenticated
};

export default authService;