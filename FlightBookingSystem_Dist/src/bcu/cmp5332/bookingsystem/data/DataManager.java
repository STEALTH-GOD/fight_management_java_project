package bcu.cmp5332.bookingsystem.data;

/**
 * The {@code DataManager} interface provides methods for loading and storing data in the flight booking system.
 * 
 * Implementing classes should define how the data is persisted (e.g., in files or databases) and 
 * how the data is loaded into the system or saved from the system.
 */
public interface DataManager {
    
    /** The separator used in data files to separate values. */
    public static final String SEPARATOR = "::";
    
    /**
     * Loads data into the flight booking system from an external source.
     * This could be a file, database, or other data sources. The data is added to the system 
     * by updating the relevant model objects such as {@link bcu.cmp5332.bookingsystem.model.Customer} or {@link bcu.cmp5332.bookingsystem.model.Booking}.
     * 
     * @param fbs The flight booking system where the data will be loaded into.
     * @throws Exception If there is an error while loading data.
     */
    void loadData(bcu.cmp5332.bookingsystem.model.FlightBookingSystem fbs) throws Exception;

    /**
     * Stores data from the flight booking system into an external source.
     * This could involve saving data to a file, database, or other storage methods.
     * 
     * @param fbs The flight booking system from which the data will be stored.
     * @throws Exception If there is an error while storing data.
     */
    void storeData(bcu.cmp5332.bookingsystem.model.FlightBookingSystem fbs) throws Exception;
}
