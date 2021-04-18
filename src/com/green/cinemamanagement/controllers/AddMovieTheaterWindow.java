package com.green.cinemamanagement.controllers;
import com.green.cinemamanagement.managers.DBManager;
import com.green.cinemamanagement.models.MovieTheater;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddMovieTheaterWindow extends BaseController implements Initializable {
    public interface AddMovieTheaterInterface {
        void onMovieTheaterAdded(MovieTheater staff);
    }

    public AddMovieTheaterWindow(ViewFactory viewFactory, String fxmlName, AddMovieTheaterWindow.AddMovieTheaterInterface listener) {
        super(viewFactory, fxmlName);
        this.listener = listener;
    }

    private ArrayList<MovieTheater> listTheater;

    private AddMovieTheaterWindow.AddMovieTheaterInterface listener;

    @FXML
    private TextField tfCumRap;

    @FXML
    private TextField tfThanhPho;

    @FXML
    private TextField tfPhim;

    @FXML
    private TextField tfSuatChieu;

    @FXML
    private Button btnSave;

    private String getInputCumRap()
    {
        return tfCumRap.getText();
    }
    private String getInputThanhPho()
    {
        return tfThanhPho.getText();
    }
    private String getInputPhim()
    {
        return tfPhim.getText();
    }
    private String getInputSuatChieu()
    {
        return tfSuatChieu.getText();
    }

    @FXML
    void onSaveClicked(ActionEvent event) {

        MovieTheater theater = new MovieTheater(getInputCumRap(), getInputThanhPho(), getInputPhim(), getInputSuatChieu());

        if (listener != null) {
            listener.onMovieTheaterAdded(theater);
        }

        closeStage();
    }

    private void getListMovieTheater()
    {
        // hien thi cac dong du lieu
        DBManager dbManager = new DBManager();
        listTheater = dbManager.initMovieTheaterDB();
    }

    private void closeStage()
    {
        Stage stage = (Stage)btnSave.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getListMovieTheater();
        btnSave.setDefaultButton(true);
    }
}
