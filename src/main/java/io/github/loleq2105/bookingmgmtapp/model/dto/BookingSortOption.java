package io.github.loleq2105.bookingmgmtapp.model.dto;

import io.github.loleq2105.bookingmgmtapp.model.dto.BookingDetails;

import java.util.Comparator;

public enum BookingSortOption {

    ID("ID", Comparator.comparingInt(bd -> bd.getBooking().getId())),
    ROOM_NUMBER("Nr pokoju", Comparator.comparing(bd -> bd.getRoomDetails().getRoom().getNumber())),
    GUEST_NAME("Gość", Comparator.comparing(bd -> bd.getGuest().getName())),
    FROM_DATE("Data Od", Comparator.comparing(bd -> bd.getBooking().getStartDate())),
    TO_DATE("Data Do", Comparator.comparing(bd -> bd.getBooking().getEndDate()));

    private final String displayName;
    private final Comparator<BookingDetails> comparator;

    BookingSortOption(String displayName, Comparator<BookingDetails> comparator) {
        this.displayName = displayName;
        this.comparator = comparator;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Comparator<BookingDetails> getComparator() {
        return comparator;
    }

    @Override
    public String toString() {
        return displayName;
    }
}