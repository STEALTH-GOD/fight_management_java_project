package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code DeleteCustomer} class represents a command to delete a customer from the flight booking system.
 * This command checks if the customer exists before performing the deletion.
 *
 */
public class DeleteCustomer implements Command {

    /** The ID of the customer to be deleted. */
    private final int customerId;

    /**
     * Constructs a {@code DeleteCustomer} command with the specified customer ID.
     *
     * @param customerId The ID of the customer to be deleted.
     */
    public DeleteCustomer(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Executes the command to delete a customer from the flight booking system.
     * The customer is first checked for existence before being deleted.
     *
     * @param fbs The flight booking system in which the customer will be deleted.
     * @throws FlightBookingSystemException If an error occurs while executing the command, 
     *                                       such as if the customer does not exist.
     */
    @Override
    public void execute(FlightBookingSystem fbs) throws FlightBookingSystemException {
        System.out.println("Executing DeleteCustomer command for customer ID: " + customerId);
        
        // Retrieve the customer by their ID
        Customer customer = fbs.getCustomerByID(customerId);
        
        // If the customer doesn't exist, throw an exception
        if (customer == null) {
            throw new FlightBookingSystemException("Customer not found for ID: " + customerId);
        }

        // Delete the customer
        fbs.deleteCustomer(customerId);
        System.out.println("Customer #" + customerId + " deleted.");
    }
}
