package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.MovieTheaterDAO;
import com.green.cinemamanagement.models.MovieTheater;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieTheaterWindow extends BaseController implements Initializable {
    public MovieTheaterWindow(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    private ObservableList<MovieTheater> data = FXCollections.observableArrayList();
    private ArrayList<MovieTheater> listTheater;
    private String TAG = "MovieTheaterWindow";
    private int currentIndex = 0;
    private Connection connection;
    private MovieTheaterDAO movieTheaterDAO;

    @FXML
    private TableView<MovieTheater> tbvMovieList;

    @FXML
    private ComboBox<String> combTheater;

    @FXML
    private ComboBox<String> combCity;

    @FXML
    private ComboBox<Integer> combRate;

    @FXML
    private TableColumn<?, ?> colTheater;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colRate;

    private void initColumnName()
    {
        colTheater.setCellValueFactory(new PropertyValueFactory<>("cumRap"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("thanhPho"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
    }

    private void initListTheater()
    {
        // hien thi cac dong du lieu
        connection = new DBConnector().getDBMovieTheaterConnection();
        movieTheaterDAO = new MovieTheaterDAO();
        listTheater = movieTheaterDAO.getAllTheater(connection);
    }

    public void uploadTheaterToTableView()
    {
        data.setAll(listTheater);
        tbvMovieList.setItems(data);
        tbvMovieList.getSelectionModel().selectLast();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnName();
        initListTheater();
        uploadTheaterToTableView();

        if (combTheater != null)
        {
            combTheater.getItems().removeAll(combTheater.getItems());
        }
        if (combCity != null)
        {
            combCity.getItems().removeAll(combCity.getItems());
        }
        if (combRate != null) {
            combRate.getItems().removeAll(combRate.getItems());
        }

        combTheater.getItems().addAll("CGV", "Lotte", "BHD", "Galaxy", "MegaStar");
        combTheater.getSelectionModel().select("CGV");

        combCity.getItems().addAll("TpHCM","Hà Nội","Hải Phòng","Huế","Vũng Tàu","Cần Thơ");
        combCity.getSelectionModel().select("TpHCM");

        combRate.getItems().addAll(5,4,3,2,1);
        combRate.getSelectionModel().select(5);
    }
}
