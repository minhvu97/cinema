package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.managers.DBManager;
import com.green.cinemamanagement.models.Staff;
import com.green.cinemamanagement.dbhelper.StaffDAO;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffWindow extends BaseController implements Initializable, AddMemberWindow.AddMemberInterface {

    public StaffWindow(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    public StaffWindow(){
        super();
    }

    static ObservableList<Staff> data = FXCollections.observableArrayList();
    private ArrayList<Staff> listStaff;
    private String TAG = "StaffWindow";

    @FXML
    private TableView<Staff> tbStaff;

    @FXML
    private TableColumn<Staff, Integer> ColStaff;

    @FXML
    private TableColumn<Staff, String> ColFirstName;

    @FXML
    private TableColumn<Staff, String> ColLastName;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    void addMember(ActionEvent event) {
        System.out.println(TAG);
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showAddMemberWindow();

        data.addListener(new ListChangeListener<Staff>() {
            @Override
            public void onChanged(Change<? extends Staff> change) {
                tbStaff.setItems(data);
            }
        });
    }

    @FXML
    void delSelected(ActionEvent event) {

    }

    private void initColumnName()
    {
        ColStaff.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ColFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ColLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    }

    private void initListStaff()
    {
        // hien thi cac dong du lieu
        DBManager dbManager = new DBManager();
        listStaff = dbManager.initDB();
    }

    public void uploadStaffOnTableView()
    {
        data.setAll(listStaff);
        tbStaff.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnName();
        initListStaff();
        uploadStaffOnTableView();
    }


    @Override
    public void onMemberAdded(Staff staff) {
        data.add(staff);
    }
}
