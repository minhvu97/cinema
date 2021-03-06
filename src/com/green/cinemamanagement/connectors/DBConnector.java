package com.green.cinemamanagement.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_STAFF_CONNECTION = "jdbc:mysql://localhost/staff?"
            +"user=root&password=123456";
    private static final String DB_MOVIETHEATER_CONNECTION = "jdbc:mysql://localhost/movietheater?"
            +"user=root&password=123456";
    private Connection connection = null;

    public DBConnector()
    {
    }

    public Connection getDBStaffConnection()
    {

        if (connection != null)
        {
            return connection;
        }

        try
        {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_STAFF_CONNECTION);
            System.out.println("get db staff success");
        }
        catch ( ClassNotFoundException exception)
        {
            System.out.println();
            System.out.println("Exception: " + exception.getMessage());
        }
        catch  (SQLException exception)
        {
            System.out.println("Make connection ex: " + exception.getMessage());
        }


        return connection;
    }

    public Connection getDBMovieTheaterConnection()
    {

        if (connection != null)
        {
            return connection;
        }

        try
        {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_MOVIETHEATER_CONNECTION);
            System.out.println("get db movie theater success");
        }
        catch ( ClassNotFoundException exception)
        {
            System.out.println();
            System.out.println("Exception: " + exception.getMessage());
        }
        catch  (SQLException exception)
        {
            System.out.println("Make connection ex: " + exception.getMessage());
        }


        return connection;
    }

}
