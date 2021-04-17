package com.green.cinemamanagement.models;

public class Seat {
    private int id;
    private boolean taken;
    private int theaterid;
    public static final int NUMBEROFSEAT = 32;

    public Seat(int id, boolean taken, int theaterid) {
        this.id = id;
        this.taken = taken;
        this.theaterid = theaterid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public int getTheaterId() {
        return theaterid;
    }

    public void setTheaterId(int theaterid) {
        this.theaterid = theaterid;
    }
}
