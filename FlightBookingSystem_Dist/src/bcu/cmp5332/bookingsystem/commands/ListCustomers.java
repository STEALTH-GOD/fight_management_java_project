package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code ListCustomers} class represents a command to list all customers in the flight booking system.
 * It prints the short details of all customers stored in the system.
 */
public class ListCustomers implements Command {

    /**
     * Executes the command to list all customers in the flight booking system.
     * Each customer's short details are printed to the console.
     *
     * @param fbs The flight booking system containing the list of customers.
     */
    @Override
    public void execute(FlightBookingSystem fbs) {
        // Iterates over the list of customers and prints their short details
        fbs.getCustomers().forEach(customer -> System.out.println(customer.getDetailsShort()));
    }
}
