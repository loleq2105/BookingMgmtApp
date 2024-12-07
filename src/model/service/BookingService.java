package model.service;

import model.dao.BookingDao;
import model.dao.GuestDao;
import model.dao.RoomDao;
import model.dao.RoomTypeDao;
import model.dto.BookingDetails;
import model.dto.RoomDetails;
import model.dto.RoomWithBookings;
import model.entities.Booking;
import model.entities.Guest;
import model.entities.Room;
import model.entities.RoomType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {

    private RoomDao roomDao;
    private RoomTypeDao roomTypeDao;
    private GuestDao guestDao;
    private BookingDao bookingDao;

    public BookingService(RoomDao roomDao, RoomTypeDao roomTypeDao, GuestDao guestDao, BookingDao bookingDao) {
        this.roomDao = roomDao;
        this.roomTypeDao = roomTypeDao;
        this.guestDao = guestDao;
        this.bookingDao = bookingDao;
    }

    public List<RoomDetails> findAllRooms(){
        List<Room> rooms = roomDao.findAll();
        List<RoomDetails> roomDetailsList = new ArrayList<>();

        for(Room room : rooms){
            roomDetailsList.add(assembleRoomDetails(room));
        }

        return roomDetailsList;
    }

    public RoomDetails findRoom(int roomId){
        Room room = roomDao.findById(roomId);
        RoomDetails roomDetails = assembleRoomDetails(room);

        return roomDetails;
    }

    public void insertRoom(Room room){
        roomDao.insert(room);
    }

    public void deleteRoom(int roomId){
        roomDao.deleteById(roomId);
    }

    public List<BookingDetails> findAllBookings(){
        List<Booking> bookings = bookingDao.findAll();
        List<BookingDetails> bookingDetailsList = new ArrayList<>();

        for(Booking booking : bookings){
            bookingDetailsList.add(assembleBookingDetails(booking));
        }

        return bookingDetailsList;
    }

    public BookingDetails findBooking(int bookingId){
        Booking booking = bookingDao.findById(bookingId);
        BookingDetails bookingDetails = assembleBookingDetails(booking);

        return bookingDetails;

    }

    public List<RoomWithBookings> findAllRoomsWithBookings(){
        List<Room> rooms = roomDao.findAll();
        List<RoomWithBookings> roomsWithBookingsList = new ArrayList<>();

        for(Room room : rooms){
            List<Booking> bookings = bookingDao.findByRoom(room.getId());
            roomsWithBookingsList.add(new RoomWithBookings(room, bookings));
        }

        return roomsWithBookingsList;
    }

    public List<RoomDetails> findRoomsFreeInRange(LocalDate start, LocalDate end){

        ArrayList<Room> rooms = (ArrayList<Room>) roomDao.findAll();
        ArrayList<RoomDetails> roomDetailsList = new ArrayList<>();

        for(Room room : rooms) {
            if (roomIsFreeInRange(room, start, end)) {
                roomDetailsList.add(assembleRoomDetails(room));
            }
        }

//        Wrote this before the roomIsFreeInRange method. Oh, well.

//        ArrayList<RoomWithBookings> roomsWithBookingsList = (ArrayList<RoomWithBookings>) findAllRoomsWithBookings();
//        ArrayList<RoomDetails> roomDetailsList = new ArrayList<>();
//
//        Iterator<RoomWithBookings> i = roomsWithBookingsList.iterator();
//        while(i.hasNext()){
//            RoomWithBookings rwb = i.next();
//            Iterator<Booking> j = rwb.getBookings().iterator();
//            while(j.hasNext()){
//                Booking b = j.next();
//                if(doDateRangesOverlap(start, end, b.getStartDate(), b.getEndDate())){
//                    i.remove();
//                    break;
//                }
//            }
//        }
//
//        for(RoomWithBookings roomWithBookings : roomsWithBookingsList){
//            roomDetailsList.add(assembleRoomDetails(roomWithBookings.getRoom()));
//        }

        return roomDetailsList;

    }

    public boolean roomIsFreeInRange(Room room, LocalDate start, LocalDate end){
        List<Booking> bookings = bookingDao.findByRoom(room.getId());

        for(Booking booking : bookings){
            if(doDateRangesOverlap(start, end, booking.getStartDate(), booking.getEndDate())){
                return false;
            }
        }

        return true;
    }

    public RoomDetails assembleRoomDetails(Room room){
        RoomType roomType = roomTypeDao.findById(room.getTypeId());
        return new RoomDetails(room, roomType);
    }

    public BookingDetails assembleBookingDetails(Booking booking){
        Guest guest = guestDao.findById(booking.getGuestId());
        RoomDetails roomDetails = findRoom(booking.getRoomId());
        return new BookingDetails(booking, guest, roomDetails);
    }

    private boolean doDateRangesOverlap(LocalDate startA, LocalDate endA, LocalDate startB, LocalDate endB) {
        // Check if the start of range A is before or on the end of range B
        // and the start of range B is before or on the end of range A
        return !startA.isAfter(endB) && !startB.isAfter(endA);
    }


}
