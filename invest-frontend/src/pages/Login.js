// src/pages/Login.js
import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import authService from '../api/authService';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const location = useLocation();
    
    // Проверка сообщения о успешной регистрации
    useEffect(() => {
      if (location.state?.message) {
        setSuccess(location.state.message);
        // Очистка состояния после отображения сообщения
        navigate(location.pathname, { replace: true, state: {} });
      }
    }, [location, navigate]);
    
  const handleLogin = async (e) => {
    e.preventDefault();
    
    if (!username || !password) {
      setError('Пожалуйста, введите имя пользователя и пароль');
      return;
    }
    
    try {
      setLoading(true);
      setError('');
      console.log("do navbar");

      await authService.login(username, password);
      //console.log("navbar data", localStorage.getItem('token'));
      await new Promise(resolve => setTimeout(resolve, 50)); // Задержка

      navigate('/shares');
      console.log("shares");
    } catch (error) {
      console.log("error", error);

      setError(
        error.response?.data?.message || 
        'Не удалось войти. Проверьте имя пользователя и пароль.'
      );
    } finally {
      console.log("finally");
      setLoading(false);
    }
  };
  console.log("pre return");

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
                {/* Добавьте ссылку на регистрацию */}
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
