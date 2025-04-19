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
  // ��������
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        //������ �������� ������� ������
        if (username === 'admin' && password === 'admin123') {
          const mockUser = { id: 1, username: 'admin', email: 'admin@example.com' };
          const mockToken = 'mock-jwt-token-' + Math.random().toString(36).substring(2);
          
          localStorage.setItem('token', mockToken);
          localStorage.setItem('user', JSON.stringify(mockUser));
          
          resolve({ user: mockUser, token: mockToken });
        } else {
          reject({ response: { data: { message: '�������� ��� ������������ ��� ������' } } });
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
//     // �������� API-������
//     return new Promise((resolve, reject) => {
//       setTimeout(() => {
//         // �������� �������� �����������
//         resolve({ success: true, message: '������������ ������� ���������������' });
        
//         // if (username === 'existingUser') {
//         //   reject({ response: { data: { message: '������������ � ����� ������ ��� ����������' } } });
//         // }
//       }, 500); // �������� �������� ����
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
