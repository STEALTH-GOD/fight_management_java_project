package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code UpdateCustomer} class represents a command to update the details of an existing customer in the flight booking system.
 * It allows updating the customer's name, phone number, email, and password.
 */
public class UpdateCustomer implements Command {

    /** The ID of the customer to be updated. */
    private final int customerId;
    
    /** The new name of the customer. */
    private final String name;
    
    /** The new phone number of the customer. */
    private final String phone;
    
    /** The new email address of the customer. */
    private final String email;
    
    /** The new password of the customer. */
    private final String password;
    
    /**
     * Constructs an {@code UpdateCustomer} command with the specified customer details.
     *
     * @param customerId The ID of the customer to be updated.
     * @param name The new name for the customer.
     * @param phone The new phone number for the customer.
     * @param email The new email address for the customer.
     * @param password The new password for the customer.
     */
    public UpdateCustomer(int customerId, String name, String phone, String email, String password) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    
    /**
     * Executes the command to update the details of the customer with the given ID.
     * If the customer is found, their details are updated with the new values provided.
     *
     * @param flightBookingSystem The flight booking system containing the customer data.
     * @throws FlightBookingSystemException If an error occurs while updating the customer or if the customer is not found.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // Retrieve the customer by their ID
        Customer customer = flightBookingSystem.getCustomerByID(customerId);
        
        // If customer is found, update their details
        if (customer != null) {
            customer.setName(name);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setPassword(password);
            System.out.println("Customer #" + customerId + " updated.");
        } else {
            throw new FlightBookingSystemException("Customer not found for ID: " + customerId);
        }
    }
}
