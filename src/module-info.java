module CinemaManagement {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens com.green.cinemamanagement;
    opens com.green.cinemamanagement.controllers;
    opens com.green.cinemamanagement.models;
}