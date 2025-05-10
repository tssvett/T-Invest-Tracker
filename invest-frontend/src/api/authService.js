// src/api/authService.js
import axios from './axiosConfig';
import { jwtDecode } from 'jwt-decode';

const login = async (username, password) => {
  try {
    const response = await axios.post('/auth/login', {
      login: username,
      password: password
    });
    const decoded = jwtDecode(response.data.accessToken);
    console.log("user: ", decoded.userId, "token: ", response.data.accessToken);
    if (response.data.accessToken) {
      localStorage.setItem('token', response.data.accessToken);
      localStorage.setItem('user', decoded.userId);//JSON.stringify(decoded.userId));
    }
    console.log("successful auth");
    return { user: decoded.userId, token: response.data.accessToken};
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

// const login = async (username, password) => {
//   // mock
//     return new Promise((resolve, reject) => {
//       setTimeout(() => {
//         //пример проверки учетных данных
//         if (username === 'admin' && password === 'admin123') {
//           const mockUser = { id: 1, username: 'admin', email: 'admin@example.com' };
//           const mockToken = 'mock-jwt-token-' + Math.random().toString(36).substring(2);

//           localStorage.setItem('token', mockToken);
//           localStorage.setItem('user', JSON.stringify(mockUser));

//           resolve({ user: mockUser, token: mockToken });
//         } else {
//           reject({ response: { data: { message: 'Неверное имя пользователя или пароль' } } });
//         }
//       }, 500); 
//     });
// };


const logout = () => {
  //console.log("logout");
  localStorage.removeItem('token');
  localStorage.removeItem('user');
  
};

const getCurrentUser = () => {
  return localStorage.getItem('user');
};

const isAuthenticated = () => {
  //console.log("isauth ", localStorage.getItem('token'));
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

// const register = async (username, email, password) => {
//     // Имитация API-вызова
//     return new Promise((resolve, reject) => {
//       setTimeout(() => {
//         // Имитация успешной регистрации
//         resolve({ success: true, message: 'Пользователь успешно зарегистрирован' });

//         // if (username === 'existingUser') {
//         //   reject({ response: { data: { message: 'Пользователь с таким именем уже существует' } } });
//         // }
//       }, 500); // Имитация задержки сети
//     });
//   };


const authService = {
  login,
  logout,
  register,
  getCurrentUser,
  isAuthenticated
};

export default authService;
