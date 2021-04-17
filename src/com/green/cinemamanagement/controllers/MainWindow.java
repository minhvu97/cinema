package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

public class MainWindow extends BaseController {
    public MainWindow(ViewFactory viewFactory, String fxmlName)
    {
        super(viewFactory, fxmlName);
    }

    private String TAG = "MainWindow";

    @FXML
    private MenuBar menuMain;

    @FXML
    void onOptionClicked(ActionEvent event) {
        System.out.println(TAG + "::menuOptionAction()");
        viewFactory.showOptionWindow();
    }

    @FXML
    void onStaffClicked(ActionEvent event) {
        System.out.println(TAG + "::menuStaffMember");
        viewFactory.showStaffWindow();
    }

    @FXML
    void onMovieClicked(ActionEvent event) {
        System.out.println(TAG + "::menuStaffMember");
        viewFactory.showMovieTheaterWindow();
    }

    @FXML
    void onBanVeClicked(ActionEvent event) {
        System.out.println(TAG + "::Ban Ve");
        viewFactory.showBanVeWindow();
    }

}
