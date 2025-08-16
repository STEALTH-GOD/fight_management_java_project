package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code Command} interface represents an executable command within the flight booking system.
 * Classes implementing this interface define specific operations that can be performed.
 */
public interface Command {
    
    /**
     * A help message containing a list of all available commands and their usage.
     */
    public static final String HELP_MESSAGE = "Commands:\n"
            + "\tlistflights                               print all flights\n"
            + "\tlistcustomers                             print all customers\n"
            + "\taddflight                                 add a new flight\n"
            + "\taddcustomer                               add a new customer\n"
            + "\tshowflight [flight id]                    show flight details\n"
            + "\tshowcustomer [customer id]                show customer details\n"
            + "\taddbooking [customer id] [flight id]      add a new booking\n"
            + "\tcancelbooking [customer id] [flight id]   cancel a booking\n"
            + "\tupdatebooking [booking id] [flight id]    update a booking\n"
            + "\tdeleteflight [flight id]                  delete a flight\n"
            + "\tdeletecustomer [customer id]              delete a customer\n"
            + "\tupdatecustomer [customer id] [name] [phone] [email] [password] update a customer\n"
            + "\tloadgui                                   loads the GUI version of the app\n"
            + "\thelp                                      prints this help message\n"
            + "\texit                                      exits the program";

    /**
     * Executes the command using the given flight booking system.
     *
     * @param flightBookingSystem the flight booking system where the command will be executed
     * @throws FlightBookingSystemException if there is an error during execution
     */
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException;
}
