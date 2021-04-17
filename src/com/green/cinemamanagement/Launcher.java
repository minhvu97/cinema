package com.green.cinemamanagement;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.StaffDAO;
import com.green.cinemamanagement.managers.DBManager;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(new DBManager());

        Connection connection = new DBConnector().getDBStaffConnection();
        StaffDAO staffDAO = new StaffDAO();
//        staffDAO.createTableStaff(connection);
//        staffDAO.createTableLogin(connection);
//        staffDAO.insertTableStaff(connection, 1,"Minh","Vu", "Manager");
//        staffDAO.insertTableStaff(connection, 2,"Quynh","Vu","Staff");
//        staffDAO.insertTableLogin(connection, "1","1");
//        staffDAO.insertTableLogin(connection, "2","2");
//        staffDAO.insertTableLogin(connection, "3","3");

        viewFactory.showLoginWindow();
//        viewFactory.showMainWindow();

    }
}
