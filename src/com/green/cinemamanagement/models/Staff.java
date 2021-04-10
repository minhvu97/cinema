package com.green.cinemamanagement.models;
import com.green.cinemamanagement.constant.*;

public class Staff {
    private int ID;
    private String firstName;
    private String lastName;
    private String role;

    public Staff(int ID, String firstName, String lastName, String role) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Staff(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
