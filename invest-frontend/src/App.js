// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

// Компоненты
import Navbar from './components/Navbar';
import PrivateRoute from './components/PrivateRoute';

// Страницы
import Login from './pages/Login';
import StocksList from './pages/StocksList';
import Portfolio from './pages/Portfolio';
import Analytics from './pages/Analytics';
import NotFound from './pages/NotFound';
import Register from './pages/Register';

// Сервисы
import authService from './api/authService';

function App() {
  return (
    <Router>
      <div className="app">
        <Navbar />
        <main className="main-content">
          <Routes>
            <Route path="/login" element={
              authService.isAuthenticated() ? 
                <Navigate to="/stocks" /> : 
                <Login />
            } />

            <Route path="/register" element={
              authService.isAuthenticated() ? 
                <Navigate to="/stocks" /> : 
                <Register />
            } />
            
            <Route path="/stocks" element={
              <PrivateRoute>
                <StocksList />
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
            
            <Route path="/" element={<Navigate to="/stocks" />} />
            
            <Route path="*" element={<NotFound />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
