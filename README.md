#  Flight Management System (Everest Airlines)

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()

##  Project Overview

The **Everest Airlines Flight Management System** is a **Java-based application** designed to handle the core functionalities of an airline’s booking process. It offers both a **Command Line Interface (CLI)** and a **Graphical User Interface (GUI)**, ensuring flexibility for different types of users.

The system was built following **Object-Oriented Programming (OOP) principles** such as abstraction, encapsulation, inheritance, and polymorphism. It also incorporates data persistence, exception handling, and modular programming for scalability and maintainability.

This project is ideal for learning **Java OOP, data management, and GUI development** while solving a practical real-world problem.

---

### Features

### Flight Management

* Add new flights with details such as flight number, origin, destination, departure date, price, and capacity.
* View a list of all available flights.
* Search for and display flight details using flight ID.
* Delete flights safely while ensuring system data integrity.

###  Customer Management

* Register new customers with details (name, phone, email, password).
* View all registered customers.
* Fetch customer details by ID.
* Delete customers and handle associated bookings.

### Booking Management

* Create new bookings linking flights and customers.
* Cancel bookings with refund calculation and cancellation fee handling.
* Display booking details (customer, flight, date, fees).
* Manage and update existing bookings.

### Graphical User Interface (GUI)

* User-friendly GUI with login page, forms, and structured panels.
* Visual booking and flight management through menus and buttons.
* Filtering and searching options for flights.
* Progress bar with animated loading screen.

###  Data Persistence

* Data stored in `.txt` files for simplicity and accessibility.
* Classes handle saving and loading of `Flights`, `Customers`, and `Bookings`.
* Managers ensure **data integrity** and consistency across sessions.

###  Testing

* JUnit test cases for **Flights**, **Customers**, **Bookings**, and **System functions**.
* Edge case handling (invalid inputs, duplicate IDs, missing data).

---

## Project Structure

```
fight_management_java_project/
 ├── src/
 │   ├── Main.java                     # Entry point
 │   ├── commands/                     # CLI command handlers
 │   │   ├── AddBooking.java
 │   │   ├── AddCustomer.java
 │   │   ├── AddFlight.java
 │   │   ├── CancelBooking.java
 │   │   ├── DeleteCustomer.java
 │   │   ├── DeleteFlight.java
 │   │   ├── Help.java
 │   │   ├── ListCustomers.java
 │   │   ├── ListFlights.java
 │   │   ├── ShowCustomer.java
 │   │   ├── ShowFlight.java
 │   │   └── LoadGUI.java
 │   │
 │   ├── model/                        # Core business logic
 │   │   ├── Booking.java
 │   │   ├── Customer.java
 │   │   ├── Flight.java
 │   │   └── FlightBookingSystem.java
 │   │
 │   ├── data/                         # Data persistence
 │   │   ├── BookingDataManager.java
 │   │   ├── CustomerDataManager.java
 │   │   ├── FlightDataManager.java
 │   │   ├── FlightBookingSystemData.java
 │   │   └── DataManager.java
 │   │
 │   ├── gui/                          # GUI classes
 │   │   └── MainWindow.java
 │   │
 │   └── tests/                        # JUnit tests
 │       ├── BookingTest.java
 │       ├── CustomerTest.java
 │       ├── FlightTest.java
 │       └── FlightBookingSystemTest.java
 │
 └── resources/data/                   # Data storage
     ├── Bookings.txt
     ├── Customers.txt
     └── Flights.txt
```

---

##  Requirements

* **Java 17 or higher**
* IDE of your choice (Eclipse, IntelliJ IDEA, or VS Code with Java extension)
* JUnit for testing

---

##  Installation & Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/STEALTH-GOD/fight_management_java_project.git
   cd fight_management_java_project
   ```

2. **Compile the project**

   ```bash
   javac -d bin src/**/*.java
   ```

3. **Run in CLI mode**

   ```bash
   java -cp bin Main
   ```

4. **Switch to GUI mode**
   Inside the CLI, type:

   ```
   loadgui
   ```

---

##  Example CLI Commands

* `addcustomer` → Add a new customer
* `addflight` → Add a new flight
* `listflights` → Show all flights
* `listcustomers` → Show all customers
* `showflight <id>` → Show specific flight details
* `showcustomer <id>` → Show customer details
* `addbooking` → Create a booking
* `cancelbooking <id>` → Cancel a booking
* `deletecustomer <id>` → Delete a customer
* `deleteflight <id>` → Delete a flight
* `help` → Display all commands

---

##  Data Storage

All data is stored in text files under the `resources/data/` directory:

* `Bookings.txt` → Active and cancelled bookings
* `Customers.txt` → Customer records with unique IDs
* `Flights.txt` → Flight information including schedules and prices

---

##  Testing

To run tests:

```bash
javac -cp .:junit-4.13.2.jar org.junit.runner.JUnitCore tests/FlightBookingSystemTest
```

Test classes include:

* **BookingTest** – validates booking creation and cancellation.
* **CustomerTest** – checks customer data handling.
* **FlightTest** – verifies flight details and price calculations.
* **FlightBookingSystemTest** – tests overall system integrity.

---

##  Authors

* **Ayush Tamang**
* **Samyak Lal Maharjan**

---


## Future Improvements

* Integration with a relational database (MySQL/PostgreSQL).
* Real-time seat availability and price updates.
* REST API for external system integration.
* More robust authentication and user roles (admin, customer).

