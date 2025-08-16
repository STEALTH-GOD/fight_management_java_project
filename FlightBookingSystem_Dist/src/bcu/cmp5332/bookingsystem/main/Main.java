package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The {@code Main} class serves as the entry point for the flight booking system application. 
 * It is responsible for initializing the flight booking system, loading the data, 
 * and providing a command-line interface (CLI) for user interaction.
 * <p>
 * The main functionality includes:
 * <ul>
 *     <li>Loading the flight booking system data.</li>
 *     <li>Providing an interactive command-line interface for the user to interact with the system.</li>
 *     <li>Parsing user commands and executing corresponding actions via the {@link CommandParser} and {@link Command} interface.</li>
 *     <li>Saving the data back to the storage upon exit.</li>
 * </ul>
 * </p>
 * <p>
 * The application continues to run until the user types 'exit' or 'quit'.
 * </p>
 */
public class Main {

    /**
     * The main method is the entry point for the flight booking system application. 
     * It initializes the system, listens for user input, processes commands, and handles system exit.
     * Author [Samyak Lal Maharjan / Ayush Tamang]
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        try {
            // Load the flight booking system data from storage
            FlightBookingSystem fbs = FlightBookingSystemData.load();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // Welcome message and instructions
            System.out.println("Welcome to Everest Airlines");
            System.out.println("Type 'help' for available commands, or 'exit'/'quit' to close the application.");
            
            // Command loop
            while (true) {
                System.out.print("> ");
                String line = br.readLine();
                
                // Exit the loop if 'exit' or 'quit' is entered
                if (line == null || line.trim().equalsIgnoreCase("exit") || line.trim().equalsIgnoreCase("quit")) {
                    System.out.println("Exiting...");
                    break;
                }
                
                try {
                    // Parse and execute the command
                    bcu.cmp5332.bookingsystem.commands.Command command = CommandParser.parse(line);
                    command.execute(fbs);
                } catch (Exception ex) {
                    // Print error if command fails
                    System.err.println("Error: " + ex.getMessage());
                }
            }
            
            // Optionally, save the system data on exit
            FlightBookingSystemData.store(fbs);
        } catch (Exception e) {
            // Handle initialization errors
            System.err.println("Failed to initialize system: " + e.getMessage());
        }
    }
}
