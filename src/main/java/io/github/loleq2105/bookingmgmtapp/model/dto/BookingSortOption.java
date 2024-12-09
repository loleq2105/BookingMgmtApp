package io.github.loleq2105.bookingmgmtapp.model.dto;

import io.github.loleq2105.bookingmgmtapp.model.dto.BookingDetails;

import java.util.Comparator;

/**
 * Enum representing different options for sorting bookings.
 */
public enum BookingSortOption {

    /**
     * Sort by booking ID.
     */
    ID("ID", Comparator.comparingInt(bd -> bd.getBooking().getId())),

    /**
     * Sort by room number.
     */
    ROOM_NUMBER("Nr pokoju", Comparator.comparing(bd -> bd.getRoomDetails().getRoom().getNumber())),

    /**
     * Sort by guest name.
     */
    GUEST_NAME("Gość", Comparator.comparing(bd -> bd.getGuest().getName())),

    /**
     * Sort by start date of the booking.
     */
    FROM_DATE("Data Od", Comparator.comparing(bd -> bd.getBooking().getStartDate())),

    /**
     * Sort by end date of the booking.
     */
    TO_DATE("Data Do", Comparator.comparing(bd -> bd.getBooking().getEndDate()));

    private final String displayName;
    private final Comparator<BookingDetails> comparator;

    /**
     * Constructs a BookingSortOption with the specified display name and comparator.
     *
     * @param displayName the display name of the sort option
     * @param comparator the comparator used for sorting
     */
    BookingSortOption(String displayName, Comparator<BookingDetails> comparator) {
        this.displayName = displayName;
        this.comparator = comparator;
    }

    /**
     * Gets the display name of the sort option.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the comparator for the sort option.
     *
     * @return the comparator
     */
    public Comparator<BookingDetails> getComparator() {
        return comparator;
    }

    /**
     * Returns the display name of the sort option.
     *
     * @return the display name
     */
    @Override
    public String toString() {
        return displayName;
    }
}