package bcu.cmp5332.bookingsystem.auth;

/**
 * The {@code User} class represents a user in the system with a username, password, and associated role.
 * This class provides methods to retrieve user details and check if the provided password matches the stored one.
 */
public class User {
    
    /** The username of the user. */
    private String username;
    
    /** The password of the user (in plaintext for simplicity). */
    private String password;
    
    /** The role of the user, which determines the level of access. */
    private Role role;
    
    /**
     * Constructs a {@code User} object with the specified username, password, and role.
     *
     * @param username The username of the user.
     * @param password The password of the user (in plaintext).
     * @param role The role of the user (either ADMIN or CUSTOMER).
     */
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gets the role of the user.
     *
     * @return The role of the user.
     */
    public Role getRole() {
        return role;
    }
    
    /**
     * Checks if the provided password matches the user's stored password.
     *
     * @param input The password input to check against the stored password.
     * @return {@code true} if the provided password matches, {@code false} otherwise.
     */
    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }
}
