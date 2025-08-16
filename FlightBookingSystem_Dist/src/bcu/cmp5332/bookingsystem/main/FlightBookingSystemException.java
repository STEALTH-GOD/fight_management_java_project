package bcu.cmp5332.bookingsystem.main;

/**
 * The {@code FlightBookingSystemException} class is a custom exception that is thrown 
 * when an error occurs in the flight booking system. It extends the {@link Exception} class 
 * and is used to indicate specific issues related to the system's functionality.
 * <p>
 * This exception is used throughout the system to handle errors in a way that is meaningful 
 * to the flight booking process. It can be thrown in various situations, such as when an 
 * invalid command is entered, a required field is missing, or a booking operation fails.
 * </p>
 */
public class FlightBookingSystemException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@code FlightBookingSystemException} with the specified detail message.
     *
     * @param message The detail message which provides additional information about the exception.
     */
    public FlightBookingSystemException(String message) {
        super(message);
    }
}
