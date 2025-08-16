package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code ShowFlight} class represents a command to display detailed information of a specific flight in the flight booking system.
 * The flight's details are shown based on its unique flight ID.
 */
public class ShowFlight implements Command {
    
    /** The ID of the flight whose details are to be shown. */
    private final int flightId;
    
    /**
     * Constructs a {@code ShowFlight} command with the specified flight ID.
     *
     * @param flightId The ID of the flight whose details will be displayed.
     */
    public ShowFlight(int flightId) {
        this.flightId = flightId;
    }
    
    /**
     * Executes the command to retrieve and display the details of the flight with the given ID.
     * If the flight is found, its long details are printed to the console.
     *
     * @param fbs The flight booking system used to retrieve the flight.
     * @throws FlightBookingSystemException If an error occurs while retrieving the flight or if the flight is not found.
     */
    @Override
    public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
        // Retrieve the flight by its ID
        Flight flight = fbs.getFlightByID(flightId);
        
        // If flight is found, print its long details
        if (flight != null) {
            System.out.println(flight.getDetailsLong());
        } else {
            throw new FlightBookingSystemException("Flight not found for ID: " + flightId);
        }
    }
}
