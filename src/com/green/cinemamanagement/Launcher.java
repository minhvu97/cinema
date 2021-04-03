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

        Connection connection = new DBConnector().getDBConnection();
        StaffDAO staffDAO = new StaffDAO();
        staffDAO.createTableStaff(connection);
        staffDAO.createTableLogin(connection);
        staffDAO.insertTableStaff(connection, 1,"Minh","Vu");
        staffDAO.insertTableStaff(connection, 2,"Quynh","Vu");
        staffDAO.insertTableLogin(connection, "1","1");

        viewFactory.showLoginWindow();
//        viewFactory.showMainWindow();

    }
}
