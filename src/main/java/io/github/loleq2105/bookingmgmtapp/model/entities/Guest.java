package io.github.loleq2105.bookingmgmtapp.model.entities;


public class Guest{

    private int id;
    private String name;
    private String phone;
    private String email;

    public Guest() {
    }

    public Guest(String email, String phone, String name) {
        this.email = email;
        this.phone = phone;
        this.name = name;
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
        return String.format("Guest [id=%s, name=%s, phone=%s, email=%s]", id, name, phone, email);
    }
}
