package com.green.cinemamanagement.dbhelper;

import com.green.cinemamanagement.models.MovieTheater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieTheaterDAO {
    private static final String QUERY_THEATER = "SELECT * FROM movietheater.theater";
    private static final String DELETE_TBL_MOVIETHEATER =
            "DELETE FROM movietheater.theater WHERE ID = #V1";
    private static final String INSERT_TBL_MOVIETHEATER =
            "INSERT INTO movietheater.theater (CUMRAP,THANHPHO, RATE) VALUES('#V1','#V2','#V3')";

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
                theaters.add(theater);
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

    public int deleteMovieTheater(Connection connection, int id)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(DELETE_TBL_MOVIETHEATER.replace("#V1",String.valueOf(id)));

            System.out.println("Deleted");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Delete note exception : " + exception.getMessage());
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
        return result;
    }

    public int insertTableStaff(Connection connection, String cumRap, String thanhPho, int rate)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_TBL_MOVIETHEATER.replace("#V1",cumRap).
                    replace("#V2",thanhPho).
                    replace("#V3",String.valueOf(rate)))
            ;

            System.out.println("Inserted");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Insert table exception : " + exception.getMessage());
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
        return result;
    }
}
