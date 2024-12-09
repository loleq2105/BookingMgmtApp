package io.github.loleq2105.bookingmgmtapp.db;

/**
 * Custom exception class for database integrity-related errors.
 */
public class DbIntegrityException extends RuntimeException {

    /**
     * Constructs a new DbIntegrityException with the specified detail message.
     *
     * @param msg the detail message
     */
    public DbIntegrityException(String msg) {
        super(msg);
    }
}