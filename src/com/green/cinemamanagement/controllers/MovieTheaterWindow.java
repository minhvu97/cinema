package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.MovieTheaterDAO;
import com.green.cinemamanagement.models.MovieTheater;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieTheaterWindow extends BaseController implements Initializable , AddMovieTheaterWindow.AddMovieTheaterInterface {
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
    private ComboBox<Integer> combPhim;


    @FXML
    private TableColumn<?, ?> colTheater;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colPhim;

    @FXML
    private TableColumn<?, ?> colSuatChieu;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    void onAddClicked(ActionEvent event) {
        viewFactory.showAddMovieTheaterWindow(this);
    }

    @FXML
    void onDeleteClicked(ActionEvent event) {
        if ( !data.isEmpty())
        {
//            int id_to_delete = data.get(currentIndex).getId();
            MovieTheater objectToDelete = tbvMovieList.getSelectionModel().getSelectedItem();
            System.out.println("current index = "+currentIndex+" , object ID = "+objectToDelete.getId());
//            listTheater.remove(tbvMovieList.getSelectionModel().getSelectedItem());
            data.remove(currentIndex);
            movieTheaterDAO.deleteMovieTheater(connection,objectToDelete.getId());


        }
    }

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

//        if (combTheater != null)
//        {
//            combTheater.getItems().removeAll(combTheater.getItems());
//        }
//        if (combCity != null)
//        {
//            combCity.getItems().removeAll(combCity.getItems());
//        }
//        if (combRate != null) {
//            combRate.getItems().removeAll(combRate.getItems());
//        }
//
//        // init variables
//        combTheater.getItems().addAll("CGV", "Lotte", "BHD", "Galaxy", "MegaStar");
//        combCity.getItems().addAll("TpHCM","Hà Nội","Hải Phòng","Huế","Vũng Tàu","Cần Thơ");
//        combRate.getItems().addAll(5,4,3,2,1);

        currentIndex = tbvMovieList.getSelectionModel().getSelectedIndex();

        tbvMovieList.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> newValue) {
                System.out.println("Selected indices : " + newValue);
                if ( newValue != null )
                {
                    int selectIndex = tbvMovieList.getSelectionModel().getSelectedIndex();
                    currentIndex = selectIndex;
                }
            }
        });

        data.addListener(new ListChangeListener<MovieTheater>() {
            @Override
            public void onChanged(Change<? extends MovieTheater> change) {
                if (change != null)
                {
                    if (!listTheater.isEmpty()) {
                        tbvMovieList.getSelectionModel().select(listTheater.size() - 1);
                        currentIndex = tbvMovieList.getSelectionModel().getSelectedIndex();
                    } else {
                        currentIndex = 0;
                    }
                }
            }
        });
    }

    @Override
    public void onMovieTheaterAdded(MovieTheater movieTheater) {
        // add to db
        movieTheaterDAO.insertTableMovieTheater(connection, movieTheater.getCumRap(), movieTheater.getThanhPho(), movieTheater.getPhim(), movieTheater.getSuatChieu());
        // add to list
        listTheater = movieTheaterDAO.getAllTheater(connection);
        data.setAll(listTheater);
    }
}
