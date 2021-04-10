package com.green.cinemamanagement.controllers;
import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.StaffDAO;
import com.green.cinemamanagement.models.Login;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginWindow extends BaseController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField tfUser;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Label lbError;

    public LoginWindow(ViewFactory viewFactory, String fxmlName)
    {
        super(viewFactory, fxmlName);
    }

    public boolean checkLogin()
    {
        Connection connection = new DBConnector().getDBStaffConnection();
        StaffDAO staffDAO = new StaffDAO();
        Login user = staffDAO.getLoginInfo(connection, tfUser.getText());
        if ( tfUser.getText().equals(user.getEmail())
                && tfPassword.getText().equals(user.getPassword()))
        {
            return true;
        }
        return false;
    }

    @FXML
    void onButtonLoginClicked(ActionEvent event) {
        if (tfUser.getText().equals(""))
        {
            lbError.setText("Please input Email!");
        }
        else if (tfPassword.getText().equals(""))
        {
            lbError.setText("Please input Password!");
        }
        else if (checkLogin())
        {
            System.out.println("you have login successfully");
            Stage stage = (Stage)lbError.getScene().getWindow();
            viewFactory.closeStage(stage);
            viewFactory.showMainWindow();
        }
        else
        {
            lbError.setText("Wrong account!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setDefaultButton(true);
        tfUser.setPromptText("Input email");
        tfPassword.setPromptText("Input password");
        repeatFocus(tfUser);
    }

    private void repeatFocus(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                repeatFocus(node);
            }
        });
    }
}
