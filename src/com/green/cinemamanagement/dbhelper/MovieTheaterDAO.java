package com.green.cinemamanagement.dbhelper;

import com.green.cinemamanagement.models.MovieTheater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieTheaterDAO {
    private static final String QUERY_THEATER = "SELECT * FROM movietheater.theater";
    public ArrayList<MovieTheater> getAllTheater(Connection connection)
    {
        ArrayList<MovieTheater> theaters = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_THEATER);

            while (resultSet.next())
            {
                MovieTheater theater = new MovieTheater();
                theater.setId(resultSet.getInt(1));
                theater.setCumRap(resultSet.getString(2));
                theater.setThanhPho(resultSet.getString(3));
                theater.setRate(resultSet.getInt(4));
            }
        }
        catch ( SQLException exception)
        {
            System.out.println("Get theaters exception : " + exception.getMessage());
        }
        finally {
            if (statement != null)
            {
                try
                {
                    statement.close();
                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                }
            }
        }
        return theaters;
    }

}
