package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * The {@code Help} class represents a command to display the help message containing a list of available commands.
 * This class is used to provide users with information on how to use the available commands in the flight booking system.
 */
public class Help implements Command {

    /**
     * Executes the command to display the help message containing a list of available commands.
     * The list of commands is stored in the {@link Command#HELP_MESSAGE} constant.
     *
     * @param flightBookingSystem The flight booking system (not used in this command, but included for consistency).
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) {
        // Prints the available commands listed in the HELP_MESSAGE constant
        System.out.println(Command.HELP_MESSAGE);
    }
}
