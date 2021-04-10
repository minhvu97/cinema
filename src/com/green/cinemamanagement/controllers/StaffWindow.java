package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
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

    private ObservableList<Staff> data = FXCollections.observableArrayList();
    private ArrayList<Staff> listStaff;
    private String TAG = "StaffWindow";
    private int currentIndex = 0;
    private Connection connection;
    private StaffDAO staffDAO;

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

    @FXML
    void addMember(ActionEvent event) {
        System.out.println(TAG);
        viewFactory.showAddMemberWindow(this);
    }

    @FXML
    void delSelected(ActionEvent event) {
        for (Staff staffCheck : listStaff)
        {
            System.out.println("staff " + staffCheck.getID() + ", got first name = " + staffCheck.getFirstName());
        }
        if ( !data.isEmpty())
        {
            System.out.println("data not empty");
            int id_to_delete = data.get(currentIndex).getID();
            listStaff.remove(tbStaff.getSelectionModel().getSelectedItem());
            data.remove(tbStaff.getSelectionModel().getSelectedItem());
            staffDAO.deleteStaff(connection,id_to_delete);

        }
    }

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

        currentIndex = tbStaff.getSelectionModel().getSelectedIndex();
        tbStaff.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> newValue) {
                System.out.println("Selected indices : " + newValue);
                if ( newValue != null )
                {
                    int selectIndex = tbStaff.getSelectionModel().getSelectedIndex();
                    currentIndex = selectIndex;

                }
            }
        });
        data.addListener(new ListChangeListener<Staff>() {
            @Override
            public void onChanged(Change<? extends Staff> change) {
                tbStaff.setItems(data);
                if (!listStaff.isEmpty()){
                    System.out.println("list staff size = " + listStaff.size());
                    System.out.println("data size = " + listStaff.size());
                    tbStaff.getSelectionModel().select(listStaff.size() - 1);
                    currentIndex = tbStaff.getSelectionModel().getSelectedIndex();
                    System.out.println("current index =" + currentIndex);
                }
                else {
                    currentIndex = 0;
                }
                System.out.println("select last = " + currentIndex);
            }
        });
    }


    @Override
    public void onMemberAdded(Staff staff) {
        // add to list
        System.out.println("member adddddddddd");
        listStaff.add(staff);
        data.add(staff);
        // add to db
        staffDAO.insertTableStaff(connection, staff.getID(),staff.getFirstName(), staff.getLastName(), staff.getRole());
    }
}
