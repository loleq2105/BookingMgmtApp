package io.github.loleq2105.bookingmgmtapp.db;

/**
 * Custom exception class for database-related errors.
 */
public class DbException extends RuntimeException {

    /**
     * Constructs a new DbException with the specified detail message.
     *
     * @param msg the detail message
     */
    public DbException(String msg) {
        super(msg);
    }
}