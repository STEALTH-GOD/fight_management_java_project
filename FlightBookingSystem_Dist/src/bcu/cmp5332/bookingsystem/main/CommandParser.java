package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The {@code CommandParser} class is responsible for parsing user input commands
 * in the flight booking system. It interprets command strings, extracts the required
 * arguments, and creates the appropriate {@link Command} objects for further processing.
 * <p>
 * This class handles commands related to flight, customer, and booking operations,
 * such as adding flights, showing flight details, adding bookings, updating bookings, 
 * deleting entities, and loading the graphical user interface (GUI).
 * </p>
 */
public class CommandParser {

    /**
     * Parses a given command line string and returns the corresponding {@link Command} object.
     * It extracts the command and its parameters, and depending on the command type,
     * creates the appropriate command object with the provided data.
     *
     * @param line The input command string.
     * @return A {@link Command} object corresponding to the parsed command.
     * @throws IOException If an error occurs while reading user input.
     * @throws FlightBookingSystemException If the command is invalid or there are errors
     *         while parsing the command.
     */
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            if (cmd.equals("addflight")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Flight Number: ");
                String flightNumber = reader.readLine();
                System.out.print("Origin: ");
                String origin = reader.readLine();
                System.out.print("Destination: ");
                String destination = reader.readLine();
                LocalDate departureDate = parseDateWithAttempts(reader);
                System.out.print("Base Price: ");
                double basePrice = Double.parseDouble(reader.readLine());
                System.out.print("Capacity: ");
                int capacity = Integer.parseInt(reader.readLine());
                return new AddFlight(flightNumber, origin, destination, departureDate, basePrice, capacity);
            } else if (cmd.equals("addcustomer")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Customer Name: ");
                String name = reader.readLine();
                System.out.print("Phone: ");
                String phone = reader.readLine();
                System.out.print("Email: ");
                String email = reader.readLine();
                System.out.print("Password: ");
                String password = reader.readLine();
                return new AddCustomer(name, phone, email,password);
            } else if (cmd.equals("listflights")) {
                return new ListFlights();
            } else if (cmd.equals("listcustomers")) {
                return new ListCustomers();
            } else if (cmd.equals("showflight") && parts.length == 2) {
                int id = Integer.parseInt(parts[1]);
                return new ShowFlight(id);
            } else if (cmd.equals("showcustomer") && parts.length == 2) {
                int id = Integer.parseInt(parts[1]);
                return new ShowCustomer(id);
            } else if (cmd.equals("addbooking") && parts.length == 3) {
                int customerId = Integer.parseInt(parts[1]);
                int flightId = Integer.parseInt(parts[2]);
                return new AddBooking(customerId, flightId, LocalDate.now());
            } else if (cmd.equals("updatebooking") && parts.length == 3) {
                int bookingId = Integer.parseInt(parts[1]);
                int newFlightId = Integer.parseInt(parts[2]);
                return new UpdateBooking(bookingId, newFlightId);
            } else if (cmd.equals("cancelbooking") && parts.length == 2) {
                int bookingId = Integer.parseInt(parts[1]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Cancellation Fee: ");
                double cancellationFee = Double.parseDouble(reader.readLine());
                return new CancelBooking(bookingId, cancellationFee);
            } else if (cmd.equals("deleteflight") && parts.length == 2) {
                int flightId = Integer.parseInt(parts[1]);
                return new DeleteFlight(flightId);
            } else if (cmd.equals("deletecustomer") && parts.length == 2) {
                int customerId = Integer.parseInt(parts[1]);
                return new DeleteCustomer(customerId);
            } else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (cmd.equals("help")) {
                return new Help();
            }
        } catch (NumberFormatException ex) {
            // fall through to error
        }
        throw new FlightBookingSystemException("Invalid command.");
    }
    
    /**
     * Parses a date input from the user with multiple attempts, ensuring the correct
     * date format (YYYY-MM-DD). The method gives the user a specified number of attempts
     * to enter the date correctly.
     *
     * @param br The buffered reader to read user input.
     * @param attempts The number of attempts the user is allowed to enter the date.
     * @return The parsed departure date.
     * @throws IOException If an error occurs while reading the input.
     * @throws FlightBookingSystemException If the user fails to enter a valid date within
     *         the allowed attempts.
     */
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher than 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (YYYY-MM-DD): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    
    /**
     * Parses a date input from the user with a default of 3 attempts, ensuring the correct
     * date format (YYYY-MM-DD).
     *
     * @param br The buffered reader to read user input.
     * @return The parsed departure date.
     * @throws IOException If an error occurs while reading the input.
     * @throws FlightBookingSystemException If the user fails to enter a valid date within
     *         the allowed attempts.
     */
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
