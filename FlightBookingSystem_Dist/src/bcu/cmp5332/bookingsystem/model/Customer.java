package bcu.cmp5332.bookingsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Customer} class represents a customer in the booking system.
 * It stores customer details such as ID, name, phone number, email, password,
 * and a list of bookings made by the customer.
 */
public class Customer {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String password; // New field for password
    private final List<Booking> bookings;
    private boolean isDeleted = false;

    /**
     * Constructs a new {@code Customer} object with the specified details.
     *
     * @param id       the unique identifier of the customer
     * @param name     the name of the customer
     * @param phone    the phone number of the customer
     * @param email    the email address of the customer
     * @param password the password of the customer
     */
    public Customer(int id, String name, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.bookings = new ArrayList<>();
    }

    /**
     * Gets the customer's ID.
     *
     * @return the customer's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the customer's name.
     *
     * @return the customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer's phone number.
     *
     * @return the customer's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the customer's email address.
     *
     * @return the customer's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the customer's password.
     *
     * @return the customer's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if the customer is marked as deleted.
     *
     * @return {@code true} if the customer is deleted, otherwise {@code false}
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Gets the list of bookings made by the customer.
     * A copy of the list is returned to maintain encapsulation.
     *
     * @return a list of the customer's bookings
     */
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    /**
     * Sets the customer's name.
     *
     * @param name the new name of the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phone the new phone number of the customer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the customer's email address.
     *
     * @param email the new email address of the customer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the customer's password.
     *
     * @param password the new password of the customer
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the deleted status of the customer.
     *
     * @param deleted {@code true} to mark the customer as deleted, otherwise {@code false}
     */
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    /**
     * Adds a booking to the customer's list of bookings.
     *
     * @param booking the booking to add
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Cancels a booking by removing it from the customer's list of bookings.
     *
     * @param booking the booking to remove
     */
    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Returns a short summary of the customer's details.
     *
     * @return a string containing the customer's ID, name, phone, and email
     */
    public String getDetailsShort() {
        return "Customer #" + id + " - " + name + " - " + phone + " - " + email;
    }
}
