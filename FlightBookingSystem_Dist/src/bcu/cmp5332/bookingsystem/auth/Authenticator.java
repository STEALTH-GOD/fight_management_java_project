package bcu.cmp5332.bookingsystem.auth;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Authenticator} class provides functionality for authenticating users based on their username and password.
 * It maintains a list of users, including demo users, and verifies the credentials during the login process.
 */
public class Authenticator {
    
    /** A list of users stored in the system. */
    private List<User> users = new ArrayList<>();
    
    /**
     * Constructs an {@code Authenticator} object and pre-populates the list with demo users.
     * The demo users include an admin and a customer for testing purposes.
     */
    public Authenticator() {
        // Pre-populate with demo users
        users.add(new User("admin", "admin123", Role.ADMIN));
        users.add(new User("customer", "cust123", Role.CUSTOMER));
    }
    
    /**
     * Attempts to authenticate a user by matching the provided username and password.
     * If a match is found, the corresponding {@code User} object is returned.
     *
     * @param username The username entered by the user attempting to log in.
     * @param password The password entered by the user attempting to log in.
     * @return The authenticated {@code User} object if credentials match, or {@code null} if authentication fails.
     */
    public User login(String username, String password) {
        for (User user : users) {
            // Check if username matches and if the password is correct
            if (user.getUsername().equalsIgnoreCase(username) && user.checkPassword(password)) {
                return user;
            }
        }
        return null; // Return null if no matching user is found
    }
}
