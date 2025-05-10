// src/components/PrivateRoute.js
import React from 'react';
import { Navigate } from 'react-router-dom';
import authService from '../api/authService';

const PrivateRoute = ({ children }) => {
  //console.log("is auth? ", authService.isAuthenticated() );
  return authService.isAuthenticated() ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
