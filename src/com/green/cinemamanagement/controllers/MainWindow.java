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
    void actMenuOption(ActionEvent event) {
        System.out.println(TAG + "::menuOptionAction()");
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showOptionWindow();
    }

    @FXML
    void actStaff(ActionEvent event) {
        System.out.println(TAG + "::menuStaffMember");
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showStaffWindow();
    }

}
