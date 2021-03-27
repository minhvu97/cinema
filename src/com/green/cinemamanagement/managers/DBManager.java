package com.green.cinemamanagement.managers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.StaffDAO;
import com.green.cinemamanagement.models.Staff;

import java.sql.Connection;
import java.util.ArrayList;

public class DBManager {
    public ArrayList<Staff> initDB()
    {
        Connection connection = new DBConnector().getDBConnection();
        StaffDAO staffDAO = new StaffDAO();
        ArrayList<Staff> listStaff = staffDAO.getAllStaffs(connection);
        return listStaff;
    }
}
