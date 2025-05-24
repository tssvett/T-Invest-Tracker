import React, { useState, useContext } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

// Компоненты
import Navbar from './components/Navbar';
import PrivateRoute from './components/PrivateRoute';

// Страницы
import Login from './pages/Login';
import SharesList from './pages/SharesList';
import Portfolio from './pages/Portfolio';
import Analytics from './pages/Analytics';
import NotFound from './pages/NotFound';
import Register from './pages/Register';

// Сервисы
import authService from './api/authService';

// Создаем контекст аутентификации
export const AuthContext = React.createContext();

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(authService.isAuthenticated());

  const login = (token, user) => {
    localStorage.setItem('token', token);
    localStorage.setItem('user', user);
    setIsAuthenticated(true);
  };

  const logout = () => {
    authService.logout();
    setIsAuthenticated(false);
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
      <Router>
        <div className="app">
          <Navbar />
          <main className="main-content">
            <Routes>
              <Route path="/login" element={
                isAuthenticated ? 
                  <Navigate to="/shares" /> : 
                  <Login />
              } />

              <Route path="/register" element={
                isAuthenticated ? 
                  <Navigate to="/shares" /> : 
                  <Register />
              } />
              
              <Route path="/shares" element={
                <PrivateRoute>
                  <SharesList />
                </PrivateRoute>
              } />
              
              <Route path="/portfolio" element={
                <PrivateRoute>
                  <Portfolio />
                </PrivateRoute>
              } />
              
              <Route path="/analytics" element={
                <PrivateRoute>
                  <Analytics />
                </PrivateRoute>
              } />
              
              <Route path="/" element={<Navigate to="/shares" />} />
              
              <Route path="*" element={<NotFound />} />
            </Routes>
          </main>
        </div>
      </Router>
    </AuthContext.Provider>
  );
}

export default App;