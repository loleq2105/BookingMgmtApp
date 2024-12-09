package io.github.loleq2105.bookingmgmtapp.model.service;

import java.util.List;

public class ServiceResponse {
    private boolean success;
    private List<String> messages;

    public ServiceResponse(boolean success, List<String> messages) {
        this.success = success;
        this.messages = messages;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<String> getMessage() {
        return messages;
    }
}
