package com.green.cinemamanagement.controllers;
import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.StaffDAO;
import com.green.cinemamanagement.models.Login;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;

public class LoginWindow extends BaseController {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lbError;

    public LoginWindow(ViewFactory viewFactory, String fxmlName)
    {
        super(viewFactory, fxmlName);
    }

    public boolean checkLogin()
    {
        Connection connection = new DBConnector().getDBConnection();
        StaffDAO staffDAO = new StaffDAO();
        Login user = staffDAO.getLoginInfo(connection);
        if ( txtUser.getText().equals(user.getEmail())
                && txtPassword.getText().equals(user.getPassword()))
        {
            return true;
        }
        return false;
    }

    @FXML
    void actLogin(ActionEvent event) {
        if (checkLogin())
        {
            System.out.println("you have login successfully");
            ViewFactory viewFactory = new ViewFactory();
            Stage stage = (Stage)lbError.getScene().getWindow();
            viewFactory.closeStage(stage);
            viewFactory.showMainWindow();
        }
        else if (txtUser.getText().equals(""))
        {
            lbError.setText("Please input Email!");
        }
        else if (txtPassword.getText().equals(""))
        {
            lbError.setText("Please input Password!");
        }
        else
        {
            lbError.setText("Wrong account!");
        }
    }

}
