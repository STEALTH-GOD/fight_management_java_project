package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code FlightBookingSystemData} class is responsible for loading and storing 
 * the entire {@link FlightBookingSystem} data by utilizing multiple {@link DataManager} implementations.
 * It manages interactions with different data sources (such as files or databases) to persist or retrieve system data.
 * 
 * This class centralizes the loading and storing of flight booking system data for flights, customers, and bookings.
 */
public class FlightBookingSystemData {

    /** A list of {@link DataManager} instances to handle the loading and storing of data. */
    private static final List<DataManager> dataManagers = new ArrayList<>();

    static {
        // Initialize the list with different data manager implementations.
        dataManagers.add(new FlightDataManager());
        dataManagers.add(new CustomerDataManager());
        dataManagers.add(new BookingDataManager());
    }

    /**
     * Loads all relevant data into the flight booking system by utilizing the 
     * different {@link DataManager} implementations.
     * This method loads data for flights, customers, and bookings.
     *
     * @return The {@link FlightBookingSystem} object with the loaded data.
     * @throws FlightBookingSystemException If an error occurs during the loading of the system data.
     * @throws IOException If an I/O error occurs while reading the data from the source.
     */
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {
        FlightBookingSystem fbs = new FlightBookingSystem();
        // Load data from each manager
        for (DataManager dm : dataManagers) {
            try {
                dm.loadData(fbs);
            } catch (Exception e) {
                // Log or handle the error appropriately
                e.printStackTrace();
            }
        }
        return fbs;
    }

    /**
     * Stores all data from the flight booking system to the respective data sources
     * using the registered {@link DataManager} implementations.
     * 
     * @param fbs The {@link FlightBookingSystem} object that holds the data to be stored.
     * @throws IOException If an I/O error occurs while saving the data to the source.
     */
    public static void store(FlightBookingSystem fbs) throws IOException {
        // Store data using each manager
        for (DataManager dm : dataManagers) {
            try {
                dm.storeData(fbs);
            } catch (Exception e) {
                // Log or handle the error appropriately
                e.printStackTrace();
            }
        }
    }
}
