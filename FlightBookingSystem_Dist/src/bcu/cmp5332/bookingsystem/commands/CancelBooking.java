package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code CancelBooking} class represents a command to cancel an existing booking
 * in the flight booking system. It applies a cancellation fee upon execution.
 */
public class CancelBooking implements Command {
    
    /** The ID of the booking to be canceled. */
    private final int bookingId;

    /** The fee charged for canceling the booking. */
    private final double cancellationFee;

    /**
     * Constructs a {@code CancelBooking} command with the specified booking ID and cancellation fee.
     *
     * @param bookingId       the ID of the booking to be canceled
     * @param cancellationFee the fee charged for canceling the booking
     */
    public CancelBooking(int bookingId, double cancellationFee) {
        this.bookingId = bookingId;
        this.cancellationFee = cancellationFee;
    }

    /**
     * Executes the command to cancel a booking in the flight booking system.
     * A cancellation fee is applied when the booking is canceled.
     *
     * @param fbs the flight booking system where the booking will be canceled
     * @throws FlightBookingSystemException if there is an error canceling the booking
     */
    @Override
    public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
        fbs.cancelBooking(bookingId, cancellationFee);
        System.out.println("Booking " + bookingId + " cancelled with cancellation fee: $" + cancellationFee);
    }
}
