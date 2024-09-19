# Room Booking Backend

This is the backend for the Room Booking application, built with **Spring Boot**. It provides a RESTful API for the frontend to interact with, using **Spring Security** for authentication and **JWT Tokens** for authorization. It integrates with an SQL database and **Google Firebase** for additional functionality.

## Features

- **RESTful API**: Provides endpoints for room booking, user management, and more.
- **Authentication**: Uses JWT tokens for securing endpoints and OAuth2 for third-party logins.
- **Database Integration**: Connects to an SQL database using JPA repositories.
- **Google Firebase**: Utilized for adding and fetching images

## Tech Stack

- **Spring Boot**: Main backend framework
- **Spring Security**: For authentication and authorization
- **JWT**: For token-based authentication
- **OAuth2**: For third-party login integrations (e.g., Google)
- **JPA**: For database access and management
- **MySQL**: Database for storing user and booking data
- **Google Firebase**: For storing images
