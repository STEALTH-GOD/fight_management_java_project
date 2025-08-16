package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code ShowCustomer} class represents a command to display details of a specific customer in the flight booking system.
 * The customer's details are shown based on their unique customer ID.
 */
public class ShowCustomer implements Command {
    
    /** The ID of the customer whose details are to be shown. */
    private final int customerId;
    
    /**
     * Constructs a {@code ShowCustomer} command with the specified customer ID.
     *
     * @param customerId The ID of the customer whose details will be displayed.
     */
    public ShowCustomer(int customerId) {
        this.customerId = customerId;
    }
    
    /**
     * Executes the command to retrieve and display the details of the customer with the given ID.
     * If the customer is found, their short details are printed to the console.
     *
     * @param fbs The flight booking system used to retrieve the customer.
     * @throws FlightBookingSystemException If an error occurs while retrieving the customer or if the customer is not found.
     */
    @Override
    public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
        // Retrieve the customer by their ID
        Customer customer = fbs.getCustomerByID(customerId);
        
        // If customer is found, print their short details
        if (customer != null) {
            System.out.println(customer.getDetailsShort());
        } else {
            throw new FlightBookingSystemException("Customer not found for ID: " + customerId);
        }
    }
}
