package com.green.cinemamanagement.models;

public class MovieTheater {
    private int id;
    private String cumRap;
    private String thanhPho;
    private int rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCumRap() {
        return cumRap;
    }

    public void setCumRap(String cumRap) {
        this.cumRap = cumRap;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
