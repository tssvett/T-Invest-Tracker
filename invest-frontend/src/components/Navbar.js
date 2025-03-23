// src/components/Navbar.js
import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import authService from '../api/authService';

const Navbar = () => {
  const navigate = useNavigate();
  const currentUser = authService.getCurrentUser();
  
  const handleLogout = () => {
    authService.logout();
    navigate('/login');
  };
  
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container">
        <Link className="navbar-brand" to="/">Инвестиционная платформа</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav me-auto">
            {currentUser && (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/stocks">Акции</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/portfolio">Мой портфель</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/analytics">Аналитика</Link>
                </li>
              </>
            )}
          </ul>
          <ul className="navbar-nav">
            {currentUser ? (
              <>
                <li className="nav-item">
                  <span className="nav-link">Привет, {currentUser.username}</span>
                </li>
                <li className="nav-item">
                  <button className="btn btn-outline-light" onClick={handleLogout}>Выйти</button>
                </li>
              </>
            ) : (
              <li className="nav-item">
                <Link className="nav-link" to="/login">Войти</Link>
              </li>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
