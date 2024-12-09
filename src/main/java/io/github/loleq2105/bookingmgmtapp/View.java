package io.github.loleq2105.bookingmgmtapp;

public enum View {
    BOOKING("booking.fxml"),
    ROOMS("rooms.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}