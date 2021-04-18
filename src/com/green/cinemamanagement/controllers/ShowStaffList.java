package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.StaffDAO;
import com.green.cinemamanagement.models.Staff;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowStaffList extends BaseController implements Initializable {

    public ShowStaffList(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    public ShowStaffList(){
        super();
    }
    private ObservableList<Staff> data = FXCollections.observableArrayList();
    private ArrayList<Staff> listStaff;
    private String TAG = "ShowStaffList";
    private Connection connection;
    private StaffDAO staffDAO;

    @FXML
    private Label lblRole;

    @FXML
    private TableView<Staff> tbStaff;

    @FXML
    private TableColumn<Staff, Integer> ColStaff;

    @FXML
    private TableColumn<Staff, String> ColFirstName;

    @FXML
    private TableColumn<Staff, String> ColLastName;

    @FXML
    private TableColumn<Staff, String> ColRole;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    private void initColumnName()
    {
        ColStaff.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ColFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ColLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ColRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void initListStaff()
    {
        // hien thi cac dong du lieu
        connection = new DBConnector().getDBStaffConnection();
        staffDAO = new StaffDAO();
        listStaff = staffDAO.getAllStaffs(connection);
    }

    public void uploadStaffOnTableView()
    {
        data.setAll(listStaff);
        tbStaff.setItems(data);
        tbStaff.getSelectionModel().selectLast();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnName();
        initListStaff();
        uploadStaffOnTableView();
        lblRole.setText("Staff");

    }


}
