package com.green.cinemamanagement.managers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.MovieTheaterDAO;
import com.green.cinemamanagement.dbhelper.StaffDAO;
import com.green.cinemamanagement.models.MovieTheater;
import com.green.cinemamanagement.models.Staff;

import java.sql.Connection;
import java.util.ArrayList;

public class DBManager {
    public ArrayList<Staff> initStaffDB()
    {
        Connection connection = new DBConnector().getDBStaffConnection();
        StaffDAO staffDAO = new StaffDAO();
        ArrayList<Staff> listStaff = staffDAO.getAllStaffs(connection);
        return listStaff;
    }

    public ArrayList<MovieTheater> initMovieTheaterDB()
    {
        Connection connection = new DBConnector().getDBStaffConnection();
        MovieTheaterDAO movieTheaterDAO = new MovieTheaterDAO();
        ArrayList<MovieTheater> listMovieTheaters = movieTheaterDAO.getAllTheater(connection);
        return listMovieTheaters;
    }
}
