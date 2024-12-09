package io.github.loleq2105.bookingmgmtapp;

/**
 * Enum representing different views in the Booking Management Application.
 * Each view is associated with an FXML file.
 */
public enum View {
    BOOKING("booking.fxml"),
    ROOMS("rooms.fxml");

    private String fileName;

    /**
     * Constructor for the View enum.
     *
     * @param fileName the name of the FXML file associated with the view.
     */
    View(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the name of the FXML file associated with the view.
     *
     * @return the name of the FXML file.
     */
    public String getFileName() {
        return fileName;
    }
}