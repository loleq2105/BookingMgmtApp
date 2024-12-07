package io.github.loleq2105.bookingmgmtapp;

import io.github.loleq2105.bookingmgmtapp.model.dto.RoomDetails;
import io.github.loleq2105.bookingmgmtapp.model.service.BookingService;
import io.github.loleq2105.bookingmgmtapp.model.service.ServiceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        BookingService bookingService = ServiceFactory.createBookingService();

//        ArrayList<Guest> guests = (ArrayList<Guest>)guestDao.findAll();
//
//        for(Guest guest : guests){
//            System.out.println(guest);
//        }
//
//        guestDao.insert(new Guest("jgonzales@yahoo.biz", "+57 943 034 445", "Jeronimo Goznales"));
//
//        guests = (ArrayList<Guest>)guestDao.findAll();
//
//        for(Guest guest : guests){
//            System.out.println(guest);
//        }
//
//        guestDao.deleteById(2);
//
//        guests = (ArrayList<Guest>)guestDao.findAll();
//
//        for(Guest guest : guests){
//            System.out.println(guest);
//        }
//
//        Guest guestThree = guestDao.findById(3);
//
//        System.out.println(guestThree);
//
//        ArrayList<Booking> bookings = (ArrayList<Booking>)bookingDao.findAll();
//
//        for(Booking booking : bookings){
//            System.out.println(booking);
//        }
//
//        Booking bookingTwo = bookingDao.findById(2);
//
//        System.out.println(bookingTwo);
//
//        bookingDao.insert(new Booking(LocalDate.parse("2024-12-20"), LocalDate.parse("2024-12-09"), 2, 3));
//
//        bookings = (ArrayList<Booking>)bookingDao.findAll();
//
//        for(Booking booking : bookings){
//            System.out.println(booking);
//        }
//
//        ArrayList<RoomDetails> roomDetailsList = (ArrayList<RoomDetails>) bookingService.findAllRooms();
//
//        for (RoomDetails roomDetails : roomDetailsList){
//            System.out.println(roomDetails);
//        }
//
//        waitForEnterKey();
//
//        for (int i = 1; i <= 10; i++) {
//            Room room = new Room("44", i, 2, i);
//            roomDao.insert(room);
//        }
//
//        waitForEnterKey();
//
//        roomDetailsList = (ArrayList<RoomDetails>) bookingService.findAllRooms();
//
//        for (RoomDetails roomDetails : roomDetailsList){
//            System.out.println(roomDetails);
//        }
//
//        waitForEnterKey();
//
//        bookingService.deleteRoom(1);
//
//        bookings = (ArrayList<Booking>)bookingDao.findAll();
//
//        for(Booking booking : bookings){
//            System.out.println(booking);
//        }

//        ArrayList<BookingDetails> bookingDetailsList = (ArrayList<BookingDetails>) bookingService.findAllBookings();
//
//        for (BookingDetails bookingDetails : bookingDetailsList){
//            System.out.println(bookingDetails);
//        }
//
//        waitForEnterKey();
//
//        for (int i = 1; i <= 250; i++) {
//            Booking booking = new Booking(LocalDate.parse("2024-12-20"), LocalDate.parse("2024-12-09"), 2, 3);
//            bookingDao.insert(booking);
//        }
//
//        waitForEnterKey();
//
//        bookingDetailsList = (ArrayList<BookingDetails>) bookingService.findAllBookings();
//
//        waitForEnterKey();
//
//        for (BookingDetails bookingDetails : bookingDetailsList){
//            System.out.println(bookingDetails);
//        }
//
//        waitForEnterKey();

//        ArrayList<Booking> testBookings = (ArrayList<Booking>) bookingDao.findByRoom(2);
//
//        System.out.println(testBookings);
//
//        ArrayList<BookingDetails> bookingDetailsList = (ArrayList<BookingDetails>) bookingService.findAllBookings();
//
//        for (BookingDetails bookingDetails : bookingDetailsList){
//            System.out.println(bookingDetails);
//        }

        System.out.println("Free rooms:");

        ArrayList<RoomDetails> freeRooms =
                (ArrayList<RoomDetails>) bookingService.findRoomsFreeInRange(
                        LocalDate.parse("2024-10-24"),
                        LocalDate.parse("2024-10-30"));

        for (RoomDetails roomDetails : freeRooms){
            System.out.println(roomDetails);
        }

    }


    private static long lastCallTime = System.currentTimeMillis();

    public static void waitForEnterKey() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastCallTime;
        System.out.println("Time elapsed since last call: " + elapsedTime + " milliseconds");
        System.out.println("Press Enter to continue...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastCallTime = System.currentTimeMillis();
    }

}