package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.time.LocalDate;

/**
 * The {@code AddFlight} class represents a command to add a new flight to the flight booking system.
 * It implements the {@link Command} interface and creates a flight with a unique ID.
 */
public class AddFlight implements Command {

    /** The flight number of the new flight. */
    private final String flightNumber;

    /** The origin airport or city of the flight. */
    private final String origin;

    /** The destination airport or city of the flight. */
    private final String destination;

    /** The departure date of the flight. */
    private final LocalDate departureDate;

    /** The base price for a ticket on the flight. */
    private final double basePrice;

    /** The seating capacity of the flight. */
    private final int capacity;

    /**
     * Constructs an {@code AddFlight} command with the specified flight details.
     *
     * @param flightNumber   the flight number
     * @param origin         the origin of the flight
     * @param destination    the destination of the flight
     * @param departureDate  the departure date of the flight
     * @param basePrice      the base price for a ticket
     * @param capacity       the seating capacity of the flight
     */
    public AddFlight(String flightNumber, String origin, String destination, 
                     LocalDate departureDate, double basePrice, int capacity) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.basePrice = basePrice;
        this.capacity = capacity;
    }

    /**
     * Executes the command to add a new flight to the flight booking system.
     * A unique flight ID is generated based on the highest existing flight ID.
     *
     * @param flightBookingSystem the flight booking system where the flight will be added
     * @throws FlightBookingSystemException if there is an error adding the flight
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = flightBookingSystem.getAllFlights().stream()
                .mapToInt(Flight::getId)
                .max()
                .orElse(0);
        
        Flight flight = new Flight(maxId + 1, flightNumber, origin, destination, departureDate, basePrice, capacity);
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight #" + flight.getId() + " added.");
    }
}
