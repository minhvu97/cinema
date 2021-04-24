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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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

    @FXML
    void addMember(ActionEvent event) {
        System.out.println(TAG);
        viewFactory.showAddMemberWindow(this);
    }

    @FXML
    void delSelected(ActionEvent event) {

        if ( !data.isEmpty())
        {
            System.out.println("data not empty");
            int id_to_delete = data.get(currentIndex).getID();
            data.remove(tbStaff.getSelectionModel().getSelectedItem());
            staffDAO.deleteStaff(connection,id_to_delete);
        }
    }

    @FXML
    void onFirstNameChanged(TableColumn.CellEditEvent<Staff,String> event) {
        Staff staff = tbStaff.getSelectionModel().getSelectedItem();
        staff.setFirstName(event.getNewValue());
        staffDAO.UpdateFirstNameTableStaff(connection,staff.getID(),event.getNewValue());
        data.set(currentIndex,staff);
    }

    @FXML
    void onLastNameChanged(TableColumn.CellEditEvent<Staff,String> event) {
        Staff staff = tbStaff.getSelectionModel().getSelectedItem();
        staff.setLastName(event.getNewValue());
        staffDAO.UpdateLastNameTableStaff(connection,staff.getID(),event.getNewValue());
        data.set(currentIndex,staff);
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

//        System.out.println("current user first name = " + currentUser.getFirstName());
//        System.out.println("current user last name = " + currentUser.getLastName());
//        if (currentUser.getRole().equals("Staff"))
//        {
//            lblRole.setText("Staff");
//            btnAdd.setDisable(true);
//            btnDel.setDisable(true);
//        }
//        else {
            lblRole.setText("Manager");
            if (currentUser.getID() == tbStaff.getSelectionModel().getSelectedItem().getID()) {
                btnDel.setVisible(false);
            } else {
                btnDel.setVisible(true);
            }
//        }

        // edit update
        tbStaff.setEditable(true);
        ColFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        ColLastName.setCellFactory(TextFieldTableCell.forTableColumn());

        currentIndex = tbStaff.getSelectionModel().getSelectedIndex();
        tbStaff.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> newValue) {
                System.out.println("Selected indices : " + newValue);
                if ( newValue != null )
                {
                    int selectIndex = tbStaff.getSelectionModel().getSelectedIndex();
                    currentIndex = selectIndex;
                    System.out.println("current index =" + currentIndex);
                    if (tbStaff.getSelectionModel().getSelectedItem() != null) {
                        if (currentUser.getID() == tbStaff.getSelectionModel().getSelectedItem().getID()) {
                            btnDel.setVisible(false);
                        } else {
                            btnDel.setVisible(true);
                        }
                    }
                }
            }
        });
        data.addListener(new ListChangeListener<Staff>() {
            @Override
            public void onChanged(Change<? extends Staff> change) {
                if (change != null) {
                    if (!listStaff.isEmpty()) {
                        System.out.println("list staff size = " + listStaff.size());
                        System.out.println("data size = " + listStaff.size());
                        tbStaff.getSelectionModel().select(listStaff.size() - 1);
                        currentIndex = tbStaff.getSelectionModel().getSelectedIndex();
                        System.out.println("current index =" + currentIndex);

//                        if (!currentUser.getRole().equals("Staff")) {
                            if (currentUser.getID() == tbStaff.getSelectionModel().getSelectedItem().getID()) {
                                btnDel.setVisible(false);
                            } else {
                                btnDel.setVisible(true);
                            }
//                        }
                    } else {
                        currentIndex = 0;
                    }
                    System.out.println("select last = " + currentIndex);
                }
            }
        });
    }


    @Override
    public void onMemberAdded(Staff staff) {
        // add to list
        System.out.println("member adddddddddd");
//        listStaff.add(staff);
        data.add(staff);
        // add to db
        staffDAO.insertTableStaff(connection, staff.getID(),staff.getFirstName(), staff.getLastName(), staff.getRole(),staff.getEmail(),staff.getPassword());
    }
}
