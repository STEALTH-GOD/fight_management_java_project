package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.time.LocalDate;

/**
 * The {@code AddBooking} class represents a command to add a booking to the flight booking system.
 * It implements the {@link Command} interface and allows adding a booking for a specific customer
 * on a given flight with a specified booking date.
 */
public class AddBooking implements Command {
    
    /** The ID of the customer making the booking. */
    private final int customerId;

    /** The ID of the flight being booked. */
    private final int flightId;

    /** The date when the booking is created. */
    private final LocalDate bookingDate;

    /**
     * Constructs an {@code AddBooking} command with the specified customer ID, flight ID, and booking date.
     *
     * @param customerId  the ID of the customer making the booking
     * @param flightId    the ID of the flight being booked
     * @param bookingDate the date of the booking
     */
    public AddBooking(int customerId, int flightId, LocalDate bookingDate) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
    }

    /**
     * Executes the command to add a booking to the flight booking system.
     *
     * @param flightBookingSystem the flight booking system where the booking will be added
     * @throws FlightBookingSystemException if there is an error adding the booking
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Booking booking = flightBookingSystem.addBooking(customerId, flightId, bookingDate);
        System.out.println("Booking added: " + booking);
    }
}
