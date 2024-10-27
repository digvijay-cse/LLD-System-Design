# BookMyShow

## Class structure
```text
User
├── Customer (inherits User)
│   └── Attributes: paymentMethods, favoriteMovies, bookingHistory
├── Admin (inherits User)
│   └── Attributes: role, permissions
├── Movie
│   ├── Attributes: title, genre, duration, rating, description
│   ├── Show (one-to-many)
│   └── Review (one-to-many)
├── Show
│   ├── Attributes: showTime, availableSeats
│   ├── Movie (one-to-one)
│   └── Booking (one-to-many)
├── Booking
│   ├── Attributes: numberOfSeats, totalPrice
│   ├── Customer (one-to-one)
│   └── Show (one-to-one)
└── Review
└── Attributes: rating, feedback
```
## Java classes
```java
// Base class representing a User
class User {
Long id;
String name;
String email;
String phone;
// Constructors, getters, and setters
}

// Customer extends User
class Customer extends User {
List<String> paymentMethods;
List<Movie> favoriteMovies;
List<Booking> bookingHistory; // List of past bookings
// Constructors, getters, and setters
}

// Admin extends User
class Admin extends User {
String role; // e.g., "MODERATOR", "ADMIN"
List<String> permissions; // List of permissions for the admin
// Constructors, getters, and setters
}

// Movie representing a film
class Movie {
Long id;
String title;
String genre;
int duration; // Duration in minutes
double rating; // Average rating
String description; // Brief description of the movie
List<Show> shows; // List of shows for this movie
List<Review> reviews; // List of reviews for this movie
// Constructors, getters, and setters
}

// Show representing a specific showing of a movie
class Show {
Long id;
Movie movie; // Associated movie
String showTime; // Time of the show
int availableSeats; // Number of available seats
List<Booking> bookings; // List of bookings for this show
// Constructors, getters, and setters
}

// Booking representing a customer's booking
class Booking {
Long id;
int numberOfSeats; // Number of seats booked
double totalPrice; // Total price for the booking
Customer customer; // Associated customer
Show show; // Associated show
// Constructors, getters, and setters
}

// Review or rating for a movie
class Review {
Long id;
int rating; // Rating out of 5
String feedback; // Customer feedback
Movie movie; // Associated movie
// Constructors, getters, and setters
}
```
## Database tables
### 1. User Table

| Column      | Type     | Constraints                    |
|-------------|----------|--------------------------------|
| `id`        | BIGINT   | PRIMARY KEY                    |
| `name`      | VARCHAR  | NOT NULL                       |
| `email`     | VARCHAR  | UNIQUE, NOT NULL               |
| `phone`     | VARCHAR  | UNIQUE, NOT NULL               |
| `user_type` | ENUM     | CHECK (values: 'CUSTOMER', 'ADMIN') |

---

### 2. Customer Table

| Column             | Type     | Constraints                      |
|--------------------|----------|----------------------------------|
| `user_id`          | BIGINT   | PRIMARY KEY, FK to User          |
| `payment_methods`  | TEXT     | // Store as JSON or CSV          |
| `favorite_movies`  | TEXT     | // Store as JSON or CSV          |
| `booking_history`  | TEXT     | // Store as JSON or CSV          |

---

### 3. Admin Table

| Column        | Type     | Constraints                     |
|---------------|----------|---------------------------------|
| `user_id`     | BIGINT   | PRIMARY KEY, FK to User         |
| `role`        | VARCHAR  | NOT NULL                        |
| `permissions` | TEXT     | // Store as JSON or CSV         |

---

### 4. Movie Table

| Column       | Type     | Constraints                    |
|--------------|----------|--------------------------------|
| `id`         | BIGINT   | PRIMARY KEY                    |
| `title`      | VARCHAR  | NOT NULL                       |
| `genre`      | VARCHAR  | NOT NULL                       |
| `duration`   | INT      | NOT NULL                       |
| `rating`     | DECIMAL  |                                |
| `description`| TEXT     |                                |

---

### 5. Show Table

| Column             | Type     | Constraints                     |
|--------------------|----------|---------------------------------|
| `id`               | BIGINT   | PRIMARY KEY                     |
| `movie_id`         | BIGINT   | FK to Movie                     |
| `show_time`        | TIMESTAMP| NOT NULL                        |
| `available_seats`  | INT      | NOT NULL                        |

---

### 6. Booking Table

| Column            | Type     | Constraints                     |
|-------------------|----------|---------------------------------|
| `id`              | BIGINT   | PRIMARY KEY                     |
| `number_of_seats` | INT      | NOT NULL                        |
| `total_price`     | DECIMAL  | NOT NULL                        |
| `customer_id`     | BIGINT   | FK to Customer                  |
| `show_id`         | BIGINT   | FK to Show                      |

---

### 7. Review Table

| Column      | Type     | Constraints                     |
|-------------|----------|---------------------------------|
| `id`        | BIGINT   | PRIMARY KEY                     |
| `rating`    | INT      | CHECK (rating between 1 and 5)  |
| `feedback`  | TEXT     |                                 |
| `movie_id`  | BIGINT   | FK to Movie                     |
