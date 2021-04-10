package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.managers.DBManager;
import com.green.cinemamanagement.models.Staff;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddMemberWindow extends BaseController implements Initializable {
    public interface AddMemberInterface {
        void onMemberAdded(Staff staff);
    }

    public AddMemberWindow(ViewFactory viewFactory, String fxmlName, AddMemberInterface listener) {
        super(viewFactory, fxmlName);
        this.listener = listener;
    }

    private ArrayList<Staff> listStaff;

    private AddMemberInterface listener;

    @FXML
    private Button btnSave;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfFirstName;

    @FXML
    private Label lblError;

    @FXML
    private RadioButton radioManager;

    @FXML
    private ToggleGroup role;

    @FXML
    private RadioButton radioStaff;

    public int getInputID() {
        String text = tfID.getText();
        return Integer.parseInt(text);
    }

    public String getInputLastName() {
        return tfLastName.getText();
    }

    public String getInputFirstName() {
        return tfFirstName.getText();
    }

    public String getRole()
    {
        if (radioStaff.isSelected())
        {
            return "Staff";
        }
        else
        {
            return "Manager";
        }
    }

    @FXML
    void onButtonSaveClicked(ActionEvent event) {

        for (Staff staffTemp : listStaff)
        {
            if (staffTemp.getID() == getInputID())
            {
                lblError.setText("this ID has been used");
                return;
            }
        }

        Staff staff = new Staff(getInputID(), getInputFirstName(), getInputLastName(), getRole());

        if (listener != null) {
            System.out.println("on member add.");
            listener.onMemberAdded(staff);
        }

        closeStage();
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
        btnSave.setDefaultButton(true);
        radioStaff.setSelected(true);
//        repeatFocus(tfID);
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
