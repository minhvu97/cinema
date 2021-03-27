package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.StaffDAO;
import com.green.cinemamanagement.managers.DBManager;
import com.green.cinemamanagement.models.Staff;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddMemberWindow extends BaseController implements Initializable {
    public interface AddMemberInterface {
        void onMemberAdded(Staff staff);
    }

    public AddMemberWindow(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    private ArrayList<Staff> listStaff;

    public void setListener(AddMemberInterface listener) {
        this.listener = listener;
    }

    private AddMemberInterface listener;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtFirstName;

    @FXML
    private Label lblError;

    public int getTxtID() {
        String text = txtID.getText();
        return Integer.parseInt(text);
    }

    public String getTxtLastName() {
        return txtLastName.getText();
    }

    public String getTxtFirstName() {
        return txtFirstName.getText();
    }

    @FXML
    void actSave(ActionEvent event) {

        for (Staff staffTemp : listStaff)
        {
            if (staffTemp.getID() == getTxtID())
            {
                lblError.setText("this ID has been used");
                return;
            }
        }

        // add to list
        Staff staff = new Staff(getTxtID(), getTxtFirstName(), getTxtLastName());
        listStaff.add(staff);

        // add to database
        Connection connection = new DBConnector().getDBConnection();
        StaffDAO staffDAO = new StaffDAO();
        staffDAO.insertTableStaff(connection, getTxtFirstName(),getTxtLastName());

        closeStage();

        if (listener != null) {
            listener.onMemberAdded(staff);
        }
    }

    private void getListStaff()
    {
        // hien thi cac dong du lieu
        DBManager dbManager = new DBManager();
        listStaff = dbManager.initDB();
    }

    private void closeStage()
    {
        Stage stage = (Stage)btnSave.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getListStaff();
    }
}
