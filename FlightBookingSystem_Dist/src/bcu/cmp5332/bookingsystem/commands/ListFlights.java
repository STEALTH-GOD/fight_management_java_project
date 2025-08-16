package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code ListFlights} class represents a command to list all flights in the flight booking system.
 * It prints the short details of all flights stored in the system.
 */
public class ListFlights implements Command {

    /**
     * Executes the command to list all flights in the flight booking system.
     * Each flight's short details are printed to the console.
     *
     * @param fbs The flight booking system containing the list of flights.
     */
    @Override
    public void execute(FlightBookingSystem fbs) {
        // Iterates over the list of flights and prints their short details
        fbs.getFlights().forEach(flight -> System.out.println(flight.getDetailsShort()));
    }
}
