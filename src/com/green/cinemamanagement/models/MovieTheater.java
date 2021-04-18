package com.green.cinemamanagement.models;

public class MovieTheater {
    private int id;
    private String cumRap;
    private String thanhPho;
    private String phim;
    private String suatChieu;

    public MovieTheater( String cumRap, String thanhPho, String phim, String suatChieu) {
        this.cumRap = cumRap;
        this.thanhPho = thanhPho;
        this.phim = phim;
        this.suatChieu = suatChieu;
    }

    public MovieTheater(){}

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

    public String getPhim() {
        return phim;
    }

    public void setPhim(String phim) {
        this.phim = phim;
    }

    public String getSuatChieu() {
        return suatChieu;
    }

    public void setSuatChieu(String suatChieu) {
        this.suatChieu = suatChieu;
    }
}
