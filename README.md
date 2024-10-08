
---

# Hotel-Management Application

## Overview

**Hotel-Management** is a Spring Boot web application designed to manage the various aspects of hotel operations, including managing guests, rooms, and reservations. It exposes both web-based views using Thymeleaf and RESTful endpoints for data management and retrieval. The application utilizes Spring Data JPA for database interactions and H2 as an in-memory database.

## Features

- **Guest Management**: Add, view, and list hotel guests.
- **Room Management**: View a list of rooms in the hotel.
- **Reservation Management**: Manage and retrieve room reservations for specific dates.
- **Web and REST API**: Provides a web interface for guests and reservations, as well as RESTful APIs for accessing and managing data.
- **H2 Database**: An in-memory database for development and testing purposes.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [Endpoints](#endpoints)
- [Database Initialization](#database-initialization)
- [License](#license)

## Technologies Used

- **Spring Boot 3.3.4**
    - Spring Web
    - Spring Data JPA
    - Spring Boot Starter Thymeleaf
- **H2 Database**
- **Maven** (for build and dependency management)
- **Java 21**

## Prerequisites

Ensure you have the following installed on your machine:

- Java 21+
- Maven 3.8+
- Git (optional, for cloning the repository)

## Installation

1. Clone the repository (if applicable):
   ```bash
   git clone <repository-url>
   cd hotel-management
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Running the Application

1. After building the project, you can run it using:
   ```bash
   mvn spring-boot:run
   ```

2. The application will be accessible at: `http://localhost:8080`.

3. H2 Database console is available at: `http://localhost:8080/h2-console` (Make sure the H2 database is configured correctly in the `application.properties`).

## Project Structure

- **business**: Contains service classes (`ReservationService`, `RoomReservation`).
- **data**: Contains entity classes and repositories (`Guest`, `Room`, `Reservation`, etc.).
- **util**: Contains utility classes (`AppStartupEvent`, `DateUtils`).
- **web**: Contains web controllers for Thymeleaf-based web interface (`GuestController`, `RoomReservationController`).
- **webservice**: Exposes RESTful endpoints (`WebServiceController`).

```
src
├── main
│   ├── java
│   │   └── com.hotelmanagement
│   │       ├── business
│   │       ├── data
│   │       ├── util
│   │       ├── web
│   │       └── webservice
│   ├── resources
│   │   ├── application.properties
│   │   ├── data.sql
│   │   ├── schema.sql
│   │   └── templates (for Thymeleaf views)
└── pom.xml
```

## Endpoints

### Web-based Endpoints (Thymeleaf Views)

- **Guests**: `GET /guests`
    - Displays a list of all guests in the hotel.

- **Room Reservations**: `GET /reservations?date=yyyy-MM-dd`
    - Displays room reservations for a specific date. If no date is provided, today's date is used.

### RESTful Endpoints

- **Get Reservations**: `GET /api/reservations?date=yyyy-MM-dd`
    - Retrieves a list of room reservations for the provided date.

- **Get Rooms**: `GET /api/rooms`
    - Returns a list of all rooms in the hotel.

- **Add Guest**: `POST /api/guests`
    - Adds a new guest to the hotel. Accepts a `Guest` object in the request body.

## Database Initialization

The application uses the **H2** in-memory database. The schema and initial data are populated using the `schema.sql` and `data.sql` files located in the `src/main/resources` directory.

### Schema (`schema.sql`)

Defines the structure of the database tables (`GUEST`, `ROOM`, and `RESERVATION`).

### Data (`data.sql`)

Provides initial data for the application, including a set of rooms and guests.