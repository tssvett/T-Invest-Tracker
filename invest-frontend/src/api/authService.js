// src/api/authService.js
import axios from './axiosConfig';

// const login = async (username, password) => {
//   try {
//     const response = await axios.post('/auth/login', { username, password });
//     if (response.data.token) {
//       localStorage.setItem('token', response.data.token);
//       localStorage.setItem('user', JSON.stringify(response.data.user));
//     }
//     return response.data;
//   } catch (error) {
//     throw error;
//   }
// };

const login = async (username, password) => {
  // тестовый
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        //пример проверки учетных данных
        if (username === 'admin' && password === 'admin123') {
          const mockUser = { id: 1, username: 'admin', email: 'admin@example.com' };
          const mockToken = 'mock-jwt-token-' + Math.random().toString(36).substring(2);
          
          localStorage.setItem('token', mockToken);
          localStorage.setItem('user', JSON.stringify(mockUser));
          
          resolve({ user: mockUser, token: mockToken });
        } else {
          reject({ response: { data: { message: 'Неверное имя пользователя или пароль' } } });
        }
      }, 500); 
    });
};


const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem('user'));
};

const isAuthenticated = () => {
  return !!localStorage.getItem('token');
};


const register = async (username, email, password) => {
  try {
    const response = await axios.post('/register', {
      username,
      email,
      password
    });
    return response.data;
  } catch (error) {
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
