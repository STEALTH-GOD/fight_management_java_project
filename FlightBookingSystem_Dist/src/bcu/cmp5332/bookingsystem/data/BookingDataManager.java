package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The {@code BookingDataManager} class is responsible for loading and storing booking data 
 * to and from a file. It manages the persistence of booking information for the flight booking system.
 * 
 * This class implements the {@link DataManager} interface, providing methods to load and store booking data.
 */
public class BookingDataManager implements DataManager {
    
    /** The path to the bookings data file. */
    private final String RESOURCE = "./resources/data/bookings.txt";
    
    /**
     * Loads booking data from the bookings data file and adds the data to the {@code FlightBookingSystem}.
     * It processes each line in the file, retrieves associated customer and flight data, and handles
     * errors gracefully (such as when a customer or flight cannot be found).
     * 
     * @param fbs The flight booking system where the bookings will be added.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws FlightBookingSystemException If there is an error while processing the booking data.
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        File file = new File(RESOURCE);
        if (!file.exists()) return;
        
        try (Scanner sc = new Scanner(file)) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    line_idx++;
                    continue;
                }
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int bookingId = Integer.parseInt(properties[0]);
                    int customerId = Integer.parseInt(properties[1]);
                    int flightId = Integer.parseInt(properties[2]);
                    LocalDate bookingDate = LocalDate.parse(properties[3]);
                    double bookingFee = Double.parseDouble(properties[4]);
                    
                    // Retrieve customer; if not found, skip (or mark as cancelled)
                    Customer customer;
                    try {
                        customer = fbs.getCustomerByID(customerId);
                    } catch(FlightBookingSystemException ex) {
                        System.err.println("Booking on line " + line_idx + " cancelled: " + ex.getMessage());
                        line_idx++;
                        continue;
                    }
                    
                    // Retrieve flight; if not found, mark booking as cancelled.
                    Flight flight;
                    try {
                        flight = fbs.getFlightByID(flightId);
                    } catch(FlightBookingSystemException ex) {
                        System.err.println("Booking on line " + line_idx + " cancelled: " + ex.getMessage());
                        // Instead of skipping, create a booking and mark it cancelled.
                        Booking cancelledBooking = new Booking(bookingId, customer, new Flight(flightId, "N/A", "N/A", "N/A", LocalDate.now(), 0, 0), bookingDate, bookingFee);
                        cancelledBooking.cancel();
                        fbs.addBookingFromData(cancelledBooking);
                        line_idx++;
                        continue;
                    }
                    
                    Booking booking = new Booking(bookingId, customer, flight, bookingDate, bookingFee);
                    fbs.addBookingFromData(booking);
                } catch (Exception ex) {
                    throw new FlightBookingSystemException("Error loading booking on line " + line_idx + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }
    
    /**
     * Stores all bookings in the {@code FlightBookingSystem} to the bookings data file.
     * Each booking's information is written to the file in a specified format.
     * 
     * @param fbs The flight booking system containing the bookings to be stored.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Booking booking : fbs.getBookings()) {
                out.print(booking.getId() + SEPARATOR);
                out.print(booking.getCustomer().getId() + SEPARATOR);
                out.print(booking.getFlight().getId() + SEPARATOR);
                out.print(booking.getBookingDate() + SEPARATOR);
                out.print(booking.getBookingFee() + SEPARATOR);
                out.println();
            }
            // Optionally, store cancelled bookings separately if desired.
        }
    }
}
