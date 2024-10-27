# System Design Overview

## Class Structure

```plaintext
User
├── Rider (inherits User)
│   └── Attributes: paymentPreferences, favoriteLocations, rating
├── Driver (inherits User)
│   └── Attributes: driverLicense, isAvailable, currentLocation
│       └── Vehicle (one-to-one)
├── Location
├── Trip
│   ├── Rider (one-to-one)
│   ├── Driver (one-to-one)
│   ├── startLocation (one-to-one with Location)
│   ├── endLocation (one-to-one with Location)
│   ├── tripStatus (enum)
│   ├── Payment (one-to-one)
│   ├── Review (one-to-one for rating)
├── Vehicle
├── Payment
└── Review
```
## Java Classes 
```java
// Base class representing a User
class User {
    Long id;
    String name;
    String email;
    String phone;
    // Constructors, getters, and setters
}

// Rider extends User
class Rider extends User {
    String paymentPreferences;
    List<Location> favoriteLocations;
    Double rating;
    // Constructors, getters, and setters
}

// Driver extends User
class Driver extends User {
    String driverLicense;
    boolean isAvailable;
    Location currentLocation; // Current location of driver
    Vehicle vehicle; // Association with a Vehicle class
    // Constructors, getters, and setters
}

// Location representing a geographical coordinate
class Location {
    Double latitude;
    Double longitude;
    String address; // Optional, if reverse geocoding is needed
    // Constructors, getters, and setters
}

// Vehicle owned by Driver
class Vehicle {
    String licensePlate;
    String make;
    String model;
    Driver driver; // Association with Driver
    // Constructors, getters, and setters
}

// Enum representing the status of a trip
enum TripStatus {
    REQUESTED,
    MATCHED,
    ONGOING,
    COMPLETED,
    CANCELED
}

// Trip details
class Trip {
    Long id;
    Rider rider;
    Driver driver;
    Location startLocation;
    Location endLocation;
    TripStatus tripStatus;
    Payment payment;
    Review review; // Optional, for rating
    // Constructors, getters, and setters
}

// Payment details for a trip
class Payment {
    Long id;
    Double amount;
    String paymentMethod; // e.g., credit card, cash
    String status; // e.g., PAID, PENDING
    Trip trip; // Association with Trip
    // Constructors, getters, and setters
}

// Review or rating for a trip
class Review {
    Long id;
    int rating; // out of 5
    String feedback;
    Trip trip; // Association with Trip
    // Constructors, getters, and setters
}

// Match class to match Riders with Drivers based on criteria
class Match {
    Rider rider;
    List<Driver> availableDrivers;
    // Method to find and match the nearest driver
    Driver findMatch() {
        // Matching logic
        return null; // Placeholder return
    }
}
```
## Database Tables
### 1. User Table

| Column       | Type     | Constraints                               |
|--------------|----------|-------------------------------------------|
| `id`         | BIGINT   | PRIMARY KEY                               |
| `name`       | VARCHAR  | NOT NULL                                  |
| `email`      | VARCHAR  | UNIQUE, NOT NULL                          |
| `phone`      | VARCHAR  | UNIQUE, NOT NULL                          |
| `user_type`  | ENUM     | CHECK (values: 'RIDER', 'DRIVER')         |

### 2. Rider Table

| Column                  | Type     | Constraints                         |
|-------------------------|----------|-------------------------------------|
| `user_id`               | BIGINT   | PRIMARY KEY, FK to User             |
| `payment_preferences`   | VARCHAR  |                                     |
| `rating`                | FLOAT    |                                     |

### 3. Driver Table

| Column                   | Type     | Constraints                           |
|--------------------------|----------|---------------------------------------|
| `user_id`                | BIGINT   | PRIMARY KEY, FK to User               |
| `driver_license`         | VARCHAR  | UNIQUE, NOT NULL                      |
| `is_available`           | BOOLEAN  |                                       |
| `current_location_id`    | BIGINT   | FK to Location                        |

### 4. Location Table

| Column       | Type     | Constraints                         |
|--------------|----------|-------------------------------------|
| `id`         | BIGINT   | PRIMARY KEY                         |
| `latitude`   | DECIMAL  | NOT NULL                            |
| `longitude`  | DECIMAL  | NOT NULL                            |
| `address`    | VARCHAR  |                                     |

### 5. Vehicle Table

| Column         | Type     | Constraints                         |
|----------------|----------|-------------------------------------|
| `id`           | BIGINT   | PRIMARY KEY                         |
| `license_plate`| VARCHAR  | UNIQUE, NOT NULL                    |
| `make`         | VARCHAR  |                                     |
| `model`        | VARCHAR  |                                     |
| `driver_id`    | BIGINT   | UNIQUE, FK to Driver                |

### 6. Trip Table

| Column                  | Type     | Constraints                            |
|-------------------------|----------|----------------------------------------|
| `id`                    | BIGINT   | PRIMARY KEY                            |
| `rider_id`              | BIGINT   | FK to Rider                            |
| `driver_id`             | BIGINT   | FK to Driver                           |
| `start_location_id`     | BIGINT   | FK to Location                         |
| `end_location_id`       | BIGINT   | FK to Location                         |
| `trip_status`           | ENUM     | CHECK (values: 'REQUESTED', 'MATCHED', |
|                         |          | 'ONGOING', 'COMPLETED', 'CANCELED')    |
| `start_time`            | TIMESTAMP|                                        |
| `end_time`              | TIMESTAMP|                                        |

### 7. Payment Table

| Column          | Type     | Constraints                       |
|-----------------|----------|-----------------------------------|
| `id`            | BIGINT   | PRIMARY KEY                       |
| `trip_id`       | BIGINT   | FK to Trip, UNIQUE                |
| `amount`        | DECIMAL  |                                   |
| `payment_method`| VARCHAR  |                                   |
| `status`        | ENUM     | CHECK (values: 'PAID', 'PENDING') |

### 8. Review Table

| Column          | Type     | Constraints                     |
|-----------------|----------|---------------------------------|
| `id`            | BIGINT   | PRIMARY KEY                     |
| `trip_id`       | BIGINT   | FK to Trip, UNIQUE              |
| `rating`        | INT      | CHECK (rating between 1 and 5)  |
| `feedback`      | TEXT     |                                 |