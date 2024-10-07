import java.util.List;

public class Guest extends Entity implements EntityService{

    private int id;
    private String name;
    private String phone;
    private String email;

    private Field nameField = new Field("name", (value) -> value.length() >= 3 && value.length() <= 50);
    private Field phoneField = new Field("phone", (value) -> { return true; });
    private Field emailField = new Field("email", (value) -> value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));

    private Database db = new Database();

    public Guest() {}

    public Guest(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public void listAll() {
        List<Guest> guests = db.getAllGuests();
        if (guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            for (Guest guest : guests) {
                System.out.println(guest);
            }
        }
    }

    @Override
    public void addNew() {
        inputData();  // Collect data from input
        db.addGuest(this);  // Insert into the database
        System.out.println("Guest added successfully.");
    }

    @Override
    public void removeById(int id) {
        if (db.removeGuestById(id)) {
            System.out.println("Guest removed successfully.");
        } else {
            System.out.println("Guest not found.");
        }
    }

    @Override
    public void viewById(int id) {
        Guest guest = db.getGuestById(id);
        if (guest != null) {
            System.out.println(guest);
        } else {
            System.out.println("Guest not found.");
        }
    }

    public void inputData() {
        name = nameField.inputAndValidate();
        phone = phoneField.inputAndValidate();
        email = emailField.inputAndValidate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Guest [ID=" + id + ", Name=" + name + ", Phone=" + phone + ", E-mail=" + email + "]";
    }
}
