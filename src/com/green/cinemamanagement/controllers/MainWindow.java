package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow extends BaseController implements Initializable {
    public MainWindow(ViewFactory viewFactory, String fxmlName)
    {
        super(viewFactory, fxmlName);
    }

    private String TAG = "MainWindow";

    @FXML
    private MenuBar menuMain;

    @FXML
    private MenuItem menuMovieList;

    @FXML
    private MenuItem menuMovieManagement;

    @FXML
    private MenuItem menuBanVe;

    @FXML
    private MenuItem menuStaffManagement;

    @FXML
    private MenuItem menuStaffList;

    @FXML
    void onOptionClicked(ActionEvent event) {
        System.out.println(TAG + "::menuOptionAction()");
        viewFactory.showOptionWindow();
    }

    @FXML
    void onStaffManagementClicked(ActionEvent event) {
        System.out.println(TAG + "::menuStaffMember");
        viewFactory.showStaffWindow();
    }

    @FXML
    void onStaffListClicked(ActionEvent event) {
        System.out.println(TAG+ "::staff list");
        viewFactory.showStaffList();
    }

    @FXML
    void onMovieManagementClicked(ActionEvent event) {
        System.out.println(TAG + "::menuStaffMember");
        viewFactory.showMovieTheaterWindow();
    }

    @FXML
    void onMovieListClicked(ActionEvent event) {
        System.out.println(TAG+ "::movie list");
        viewFactory.showMovieTheaterList();
    }

    @FXML
    void onBanVeClicked(ActionEvent event) {
        System.out.println(TAG + "::Ban Ve");
        viewFactory.showBanVeWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (currentUser.getRole().equals("Staff"))
        {
            menuMovieManagement.setDisable(true);
            menuStaffManagement.setDisable(true);
        }
    }
}
