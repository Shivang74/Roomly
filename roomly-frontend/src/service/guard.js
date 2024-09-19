
// src/ProtectedRoute.js
import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import ApiService from './ApiService';


export const ProtectedRoute = ({ element: Component }) => {
  const location = useLocation();

  return ApiService.isAuthenticated() ? (
    Component
  ) : (
    <Navigate to="/login" replace state={{ from: location }} />
  );
};


export const AdminRoute = ({ element: Component }) => {
  const location = useLocation();

  return ApiService.isAdmin() ? (
    Component
  ) : (
    <Navigate to="/login" replace state={{ from: location }} />
  );
};
/*
ProtectedRoute:

It checks if the user is logged in using ApiService.isAuthenticated().
If the user is authenticated, they are allowed to access the page/component that was requested (Component).
If the user is not authenticated, they are redirected to the /login page using the Navigate component. 
The replace prop prevents the user from going back to the protected page using the browser's back button, and state={{ from: location }} remembers where the user was trying to go, so after login, they can be redirected there.
*/