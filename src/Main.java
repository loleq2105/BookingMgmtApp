import model.dao.DaoFactory;
import model.dao.GuestDao;
import model.entities.Guest;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        GuestDao guestDao = DaoFactory.createGuestDao();

        ArrayList<Guest> guests = (ArrayList<Guest>)guestDao.findAll();

        for(Guest guest : guests){
            System.out.println(guest);
        }

        guestDao.insert(new Guest("jgonzales@yahoo.biz", "+57 943 034 445", "Jeronimo Goznales"));

        guests = (ArrayList<Guest>)guestDao.findAll();

        for(Guest guest : guests){
            System.out.println(guest);
        }

        guestDao.deleteById(2);

        guests = (ArrayList<Guest>)guestDao.findAll();

        for(Guest guest : guests){
            System.out.println(guest);
        }

        Guest guestThree = guestDao.findById(3);

        System.out.println(guestThree);

    }
}