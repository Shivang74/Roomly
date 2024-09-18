# Roomly-  Full Stack Room Booking Application

This is a full-stack Room Booking application that uses **React.js** for the frontend, **Spring Boot** for the backend, **SQL** for the database, and integrates with **Google Firebase**. The project implements **Spring Security** with **JWT Tokens** and **OAuth2 Authentication** for secure access and authorization. The frontend communicates with the backend using **Axios**, and the backend uses **JPA** for database repository connectivity.

## Features

- **Frontend**: Built using React.js
  - User-friendly interface for room booking
  - Axios used for communication between frontend and backend
  - React-Router-DOM implemented for routing
  - Admin Dashboard enabled 
- **Backend**: Spring Boot Application
  - RESTful APIs for room booking, user management, etc.
  - Security with Spring Security and JWT
  - OAuth2 authentication flow for third-party logins
  - JWT token management for secure API calls
  - OAuth2 integration with third-party services (e.g., Google, Facebook)
  - JPA for database interactions
- **Database**: SQL (MySQL) 
  - Stores user information, booking details, and room data
  - Secure storage of user credentials using Bcrypt hashing
- **Firebase**: 
  - Firebase authentication for user management
  - Real-time updates for images uploaded by user

## Tech Stack

### Frontend:
- **React.js**: Main frontend framework
- **Axios**: HTTP client for API requests
- **JWT**: Token-based authentication for secure communication

### Backend:
- **Spring Boot**: Backend framework to create REST APIs
- **Spring Security**: For securing APIs and managing authentication
  - JWT Token authentication
  - OAuth2 integration for third-party logins
- **JPA (Java Persistence API)**: For ORM and database interaction
- **SQL**: MySQL or PostgreSQL for data storage
- **Firebase**: Integration for additional authentication options
