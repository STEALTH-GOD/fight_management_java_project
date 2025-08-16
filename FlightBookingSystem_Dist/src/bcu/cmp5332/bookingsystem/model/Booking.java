package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

/**
 * The {@code Booking} class represents a booking made by a customer for a specific flight.
 * It contains details about the customer, the flight, the booking date, the associated booking fee, 
 * and the cancellation status of the booking.
 * <p>
 * A booking can be cancelled, which will remove the customer from the flight's list of passengers
 * and update the customer’s bookings accordingly.
 * </p>
 */
public class Booking {
    private int id;
    private final Customer customer;
    private final Flight flight;
    private final LocalDate bookingDate;
    private boolean isCancelled = false;
    private double bookingFee;

    /**
     * Constructs a new {@code Booking} object with the specified details.
     * 
     * @param id The unique identifier for this booking.
     * @param customer The {@link Customer} who made the booking.
     * @param flight The {@link Flight} associated with this booking.
     * @param bookingDate The date the booking was made.
     * @param bookingFee The fee associated with the booking.
     */
    public Booking(int id, Customer customer, Flight flight, LocalDate bookingDate, double bookingFee) {
        this.id = id;
        this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.bookingFee = bookingFee;
    }

    /**
     * Gets the unique identifier of this booking.
     * 
     * @return The booking ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the customer who made this booking.
     * 
     * @return The {@link Customer} associated with this booking.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the flight associated with this booking.
     * 
     * @return The {@link Flight} for this booking.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Gets the date the booking was made.
     * 
     * @return The {@link LocalDate} when the booking was made.
     */
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    /**
     * Checks if this booking has been cancelled.
     * 
     * @return {@code true} if the booking is cancelled, otherwise {@code false}.
     */
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Gets the fee associated with this booking.
     * 
     * @return The booking fee.
     */
    public double getBookingFee() {
        return bookingFee;
    }

    /**
     * Cancels this booking. This will remove the customer from the flight’s list of passengers 
     * and mark the booking as cancelled.
     */
    public void cancel() {
        isCancelled = true;
        flight.getPassengers().remove(customer);
        customer.cancelBooking(this);
    }

    /**
     * Returns a string representation of the booking.
     * 
     * @return A string describing the booking with its ID, customer name, flight number, 
     *         booking date, and fee.
     */
    @Override
    public String toString() {
        return "Booking #" + id + " for " + customer.getName() + " on flight " + flight.getFlightNumber() +
               " (" + bookingDate + "), Fee: $" + bookingFee;
    }
}
