package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.MovieTheaterDAO;
import com.green.cinemamanagement.models.MovieTheater;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class BanVeWindow extends BaseController implements Initializable {

    public BanVeWindow(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    private String TAG = "BanVeWindow";
    private Connection connection;
    private MovieTheaterDAO movieTheaterDAO;
    private ArrayList<MovieTheater> listMovieTheater;
    private Set<String> city = new HashSet<String>();
//    private Set<String> cumRap = new HashSet<String>();
//    private Set<String> phim = new HashSet<String>();
//    private Set<String> time = new HashSet<String>();
    private int theater_id;
    @FXML
    private Button btnSeat;

    @FXML
    private ComboBox<String> cmbCity;

    @FXML
    private ComboBox<String> cmbCumRap;

    @FXML
    private ComboBox<String> cmbPhim;

    @FXML
    private ComboBox<String> cmbTime;

    @FXML
    void onButtonSeatClicked(ActionEvent event) {
        viewFactory.showSeatWindow(theater_id);
    }

    @FXML
    void onCityChosen(ActionEvent event) {
        if (cmbCity != null) {
            btnSeat.setDisable(true);
            ArrayList<String> cumRap = movieTheaterDAO.getAllTheaterAtCity(connection, cmbCity.getValue());
            if (cmbCumRap != null) {
                cmbCumRap.getItems().removeAll(cmbCumRap.getItems());
            }
            if (cmbPhim != null) {
                cmbPhim.getItems().removeAll(cmbPhim.getItems());
            }
            if (cmbTime != null) {
                cmbTime.getItems().removeAll(cmbTime.getItems());
            }
            cmbCumRap.getItems().addAll(cumRap);
        }
    }

    @FXML
    void onTheaterChosen(ActionEvent event) {
        if (cmbCumRap != null) {
            btnSeat.setDisable(true);
            ArrayList<String> phim = movieTheaterDAO.getAllTheaterAtCityAtCumRap(connection, cmbCity.getValue(), cmbCumRap.getValue());
            if (cmbPhim != null) {
                cmbPhim.getItems().removeAll(cmbPhim.getItems());
            }
            if (cmbTime != null) {
                cmbTime.getItems().removeAll(cmbTime.getItems());
            }
            cmbPhim.getItems().addAll(phim);
        }

    }

    @FXML
    void onMovieChosen(ActionEvent event) {
        if (cmbPhim != null) {
            btnSeat.setDisable(true);
            ArrayList<String> suatChieu = movieTheaterDAO.getAllTheaterAtCityAtCumRapWithMovie(connection, cmbCity.getValue(), cmbCumRap.getValue(), cmbPhim.getValue());
            if (cmbTime != null) {
                cmbTime.getItems().removeAll(cmbTime.getItems());
            }
            cmbTime.getItems().addAll(suatChieu);
        }
    }

    @FXML
    void onTimeChosen(ActionEvent event) {
        if (cmbTime != null) {
            btnSeat.setDisable(false);
            theater_id = movieTheaterDAO.getTheaterID(connection, cmbCity.getValue(), cmbCumRap.getValue(), cmbPhim.getValue(), cmbTime.getValue());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // hien thi cac dong du lieu
        connection = new DBConnector().getDBMovieTheaterConnection();
        movieTheaterDAO = new MovieTheaterDAO();
        listMovieTheater = movieTheaterDAO.getAllTheater(connection);

        for(MovieTheater mv : listMovieTheater)
        {
            city.add(mv.getThanhPho());
        }

        if (cmbCity != null)
        {
            cmbCity.getItems().removeAll(cmbCity.getItems());
        }
        if (cmbCumRap != null)
        {
            cmbCumRap.getItems().removeAll(cmbCumRap.getItems());
        }
        if (cmbPhim != null) {
            cmbPhim.getItems().removeAll(cmbPhim.getItems());
        }
        if (cmbTime != null) {
            cmbTime.getItems().removeAll(cmbTime.getItems());
        }

        // init variables
        cmbCity.getItems().addAll(city);
        btnSeat.setDisable(true);

    }
}
