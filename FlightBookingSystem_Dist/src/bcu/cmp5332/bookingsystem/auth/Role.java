package bcu.cmp5332.bookingsystem.auth;

/**
 * The {@code Role} enum represents the different user roles in the system.
 * It defines two roles: {@code ADMIN} and {@code CUSTOMER}.
 * These roles are used to differentiate the access levels and permissions of users.
 */
public enum Role {
    
    /** Represents an admin user with full access to the system. */
    ADMIN,
    
    /** Represents a customer user with limited access to the system. */
    CUSTOMER
}
