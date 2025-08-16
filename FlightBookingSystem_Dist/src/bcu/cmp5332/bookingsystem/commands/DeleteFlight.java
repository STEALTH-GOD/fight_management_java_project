package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code DeleteFlight} class represents a command to delete a flight from the flight booking system.
 * This command checks if the flight exists before performing the deletion.
 *
 */
public class DeleteFlight implements Command {

    /** The ID of the flight to be deleted. */
    private final int flightId;

    /**
     * Constructs a {@code DeleteFlight} command with the specified flight ID.
     *
     * @param flightId The ID of the flight to be deleted.
     */
    public DeleteFlight(int flightId) {
        this.flightId = flightId;
    }

    /**
     * Executes the command to delete a flight from the flight booking system.
     * The flight is first checked for existence before being deleted.
     *
     * @param fbs The flight booking system in which the flight will be deleted.
     * @throws FlightBookingSystemException If an error occurs while executing the command, 
     *                                       such as if the flight does not exist.
     */
    @Override
    public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
        System.out.println("Executing DeleteFlight command for flight ID: " + flightId);
        
        // Retrieve the flight by its ID
        Flight flight = fbs.getFlightByID(flightId);
        
        // If the flight doesn't exist, throw an exception
        if (flight == null) {
            throw new FlightBookingSystemException("Flight not found for ID: " + flightId);
        }

        // Delete the flight
        fbs.deleteFlight(flightId);
        System.out.println("Flight #" + flightId + " deleted.");
    }
}
