package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.gui.LoginWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import javax.swing.SwingUtilities;

/**
 * The {@code LoadGUI} class represents a command to load the graphical user interface (GUI) of the flight booking system.
 * It initializes the login window on the event dispatch thread using {@link SwingUtilities}.
 */
public class LoadGUI implements Command {

    /**
     * Executes the command to load the GUI for the flight booking system.
     * This method creates a new {@link LoginWindow} and ensures that the GUI is initialized on the event dispatch thread.
     *
     * @param flightBookingSystem The flight booking system to be used in the GUI.
     * @throws FlightBookingSystemException If there is an error while initializing the GUI.
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // Ensures the GUI is initialized on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new LoginWindow(flightBookingSystem));
    }
}
