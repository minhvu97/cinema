package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.MovieTheaterDAO;
import com.green.cinemamanagement.models.MovieTheater;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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

public class ShowMovieTheaterList extends BaseController implements Initializable{
    public ShowMovieTheaterList(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    private ObservableList<MovieTheater> data = FXCollections.observableArrayList();

    private String TAG = "ShowMovieTheaterList";
    private ArrayList<MovieTheater> listTheater;
    private Connection connection;
    private MovieTheaterDAO movieTheaterDAO;

    @FXML
    private TableView<MovieTheater> tbvMovieList;

    @FXML
    private ComboBox<String> combTheater;

    @FXML
    private ComboBox<String> combCity;

    @FXML
    private ComboBox<Integer> combPhim;


    @FXML
    private TableColumn<?, ?> colTheater;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colPhim;

    @FXML
    private TableColumn<?, ?> colSuatChieu;

    private void initColumnName()
    {
        colTheater.setCellValueFactory(new PropertyValueFactory<>("cumRap"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("thanhPho"));
        colPhim.setCellValueFactory(new PropertyValueFactory<>("phim"));
        colSuatChieu.setCellValueFactory(new PropertyValueFactory<>("suatChieu"));

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
    }

}
