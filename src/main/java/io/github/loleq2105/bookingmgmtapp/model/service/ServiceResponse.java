package io.github.loleq2105.bookingmgmtapp.model.service;

import java.util.List;

/**
 * Represents a response from a service operation.
 */
public class ServiceResponse {
    private boolean success;
    private List<String> messages;

    /**
     * Constructs a new ServiceResponse with the specified success status and messages.
     *
     * @param success the success status of the service operation
     * @param messages the list of messages related to the service operation
     */
    public ServiceResponse(boolean success, List<String> messages) {
        this.success = success;
        this.messages = messages;
    }

    /**
     * Checks if the service operation was successful.
     *
     * @return true if the service operation was successful, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the list of messages related to the service operation.
     *
     * @return the list of messages
     */
    public List<String> getMessage() {
        return messages;
    }
}