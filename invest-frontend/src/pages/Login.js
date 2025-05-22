// src/pages/Login.js
import React, { useState, useEffect, useContext } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { AuthContext } from '../App';
import authService from '../api/authService';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    
    const navigate = useNavigate();
    const location = useLocation();
    const { login } = useContext(AuthContext);

    // Обработка сообщений из state navigation
    useEffect(() => {
        const registrationMessage = location.state?.registrationSuccess;
        const sessionExpiredMessage = location.state?.sessionExpired;
        
        if (registrationMessage) {
            setError('Регистрация прошла успешно! Войдите используя свои данные');
            navigate(location.pathname, { replace: true, state: {} });
        }
        
        if (sessionExpiredMessage) {
            setError('Сессия истекла. Пожалуйста, войдите снова');
            navigate(location.pathname, { replace: true, state: {} });
        }
    }, [location, navigate]);

    const handleLogin = async (e) => {
        e.preventDefault();
        
        if (!username.trim() || !password.trim()) {
            setError('Заполните все обязательные поля');
            return;
        }

        try {
            setLoading(true);
            setError('');
            
            const { token, user } = await authService.login(username, password);
            
            login(token, user);
            
            navigate('/shares', { replace: true });
            
        } catch (err) {
            console.error('Ошибка авторизации:', err);
            setError(
                err.response?.data?.message ||
                'Ошибка входа. Проверьте подключение к интернету'
            );
        } finally {
            setLoading(false);
        }
    };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6 col-lg-4">
          <div className="card">
            <div className="card-header">
              <h4 className="text-center">Вход в систему</h4>
            </div>
            <div className="card-body">
              {error && (
                <div className="alert alert-danger" role="alert">
                  {error}
                </div>
              )}
              <form onSubmit={handleLogin}>
                <div className="mb-3">
                  <label htmlFor="username" className="form-label">Имя пользователя</label>
                  <input
                    type="text"
                    className="form-control"
                    id="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    disabled={loading}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="password" className="form-label">Пароль</label>
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    disabled={loading}
                    required
                  />
                </div>
                <div className="d-grid">
                  <button 
                    type="submit" 
                    className="btn btn-primary" 
                    disabled={loading}
                  >
                    {loading ? 'Вход...' : 'Войти'}
                  </button>
                </div>
                <div className="text-center mt-3">
                    <p>Нет аккаунта? <Link to="/register">Зарегистрироваться</Link></p>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
