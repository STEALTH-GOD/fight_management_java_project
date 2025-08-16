package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code UpdateBooking} class represents a command to update an existing booking in the flight booking system.
 * It involves cancelling the old booking and creating a new booking with the updated flight details.
 */
public class UpdateBooking implements Command {
    
    /** The ID of the booking to be updated. */
    private final int bookingId;
    
    /** The ID of the new flight to replace the old one in the booking. */
    private final int newFlightId;
    
    /**
     * Constructs an {@code UpdateBooking} command with the specified booking ID and new flight ID.
     *
     * @param bookingId The ID of the booking to be updated.
     * @param newFlightId The ID of the new flight to be booked.
     */
    public UpdateBooking(int bookingId, int newFlightId) {
        this.bookingId = bookingId;
        this.newFlightId = newFlightId;
    }
    
    /**
     * Executes the command to update an existing booking by cancelling the old booking
     * and creating a new booking with the specified flight.
     * A cancellation fee is applied when cancelling the old booking.
     *
     * @param fbs The flight booking system containing the bookings and flight data.
     * @throws FlightBookingSystemException If an error occurs during the booking update process.
     */
    @Override
    public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
        // Retrieve the old booking using the booking ID
        var oldBooking = fbs.getBookingByID(bookingId);
        
        // Get the customer ID from the old booking
        int customerId = oldBooking.getCustomer().getId();
        
        // Calculate the cancellation fee (15% of the old booking fee)
        double cancellationFee = 0.15 * oldBooking.getBookingFee();
        
        // Cancel the old booking
        fbs.cancelBooking(bookingId, cancellationFee);
        
        // Add a new booking for the customer with the new flight ID
        fbs.addBooking(customerId, newFlightId, oldBooking.getBookingDate());
        
        // Output the result
        System.out.println("Booking updated: " + bookingId + " updated to new flight: " + newFlightId + " with cancellation fee: $" + cancellationFee);
    }
}
