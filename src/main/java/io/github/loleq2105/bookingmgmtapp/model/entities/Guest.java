package io.github.loleq2105.bookingmgmtapp.model.entities;

/**
 * Represents a guest entity.
 */
public class Guest {

    private int id;
    private String name;
    private String phone;
    private String email;

    /**
     * Default constructor.
     */
    public Guest() {
    }

    /**
     * Constructs a new Guest with the specified details.
     *
     * @param email the email of the guest
     * @param phone the phone number of the guest
     * @param name the name of the guest
     */
    public Guest(String email, String phone, String name) {
        this.email = email;
        this.phone = phone;
        this.name = name;
    }

    /**
     * Gets the ID of the guest.
     *
     * @return the guest ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the guest.
     *
     * @param id the guest ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the guest.
     *
     * @return the guest name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the guest.
     *
     * @param name the guest name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the phone number of the guest.
     *
     * @return the guest phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the guest.
     *
     * @param phone the guest phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email of the guest.
     *
     * @return the guest email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the guest.
     *
     * @param email the guest email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the guest.
     *
     * @return a string representation of the guest
     */
    @Override
    public String toString() {
        return String.format("Guest [id=%s, name=%s, phone=%s, email=%s]", id, name, phone, email);
    }
}