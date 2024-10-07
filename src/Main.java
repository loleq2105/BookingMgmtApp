import java.util.List;

public class Main {


    public static void main(String[] args) {

//        Guest ga = new Guest("Richard Fuckleston", "+43 291 221 234", "xd@gmail.com");
//        Guest gb = new Guest("Richardine Stonewall", "+58 923 482 034", "yeah@yahoo.com");
//
//        Database db = new Database();
//
//        db.removeGuestById(2);
//        db.removeGuestById(3);
//
//        db.addGuest(ga);
//        db.addGuest(gb);
//
//        List<Guest> guests = db.getAllGuests();
//
//        if (guests.isEmpty()) {
//            System.out.println("No customers found.");
//        } else {
//            for (Guest guest : guests) {
//                System.out.println(guest);
//            }
//        }

        CLI cli = new CLI();

        cli.start();


    }
}