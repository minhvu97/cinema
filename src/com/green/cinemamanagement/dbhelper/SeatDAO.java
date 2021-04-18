package com.green.cinemamanagement.dbhelper;

import com.green.cinemamanagement.models.Seat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SeatDAO {
    private static final String QUERY_SEAT = "SELECT * FROM movietheater.Seat WHERE THEATERID = '#V1'";
    private static final String DELETE_TBL_SEAT =
            "DELETE FROM Seat.Seat WHERE ID = #V1";
    private static final String INSERT_TBL_SEAT =
            "INSERT INTO movietheater.Seat (ID,TAKEN,theaterid) VALUES('#V1','#V2','#V3')";
    private static final String UPDATE_TBL_SEAT =
            "UPDATE movietheater.Seat SET TAKEN = '#V2' WHERE (id,theaterid) = ('#V1','#V3')";
    private static final String CHECK_SEAT = "SELECT * FROM movietheater.Seat WHERE TAKEN = 1 AND ID = '#V1' AND THEATERID = '#V3'";

    public ArrayList<Seat> getAllSeatWithTheaterID(Connection connection, int theater_id)
    {
        ArrayList<Seat> seats = new ArrayList<Seat>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SEAT.replace("#V1",String.valueOf(theater_id)));

            while (resultSet.next())
            {
                Seat seat = new Seat(resultSet.getInt(1),resultSet.getBoolean(2),resultSet.getInt(3));
                seats.add(seat);
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
        return seats;
    }

    public int deleteSeat(Connection connection, int id)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(DELETE_TBL_SEAT.replace("#V1",String.valueOf(id)));

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

    public int insertTableSeat(Connection connection, int id, boolean taken , int theaterid)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_TBL_SEAT.replace("#V1",String.valueOf(id)).
                    replace("#V2",String.valueOf(taken)).
                    replace("#V3", String.valueOf(theaterid)));

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

    public int updateTableSeat(Connection connection, int id, boolean taken , int theaterid)
    {
        int result = 0;
        Statement statement = null;
        int myInt = taken ? 1 : 0;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_TBL_SEAT.replace("#V1",String.valueOf(id)).
                    replace("#V2",String.valueOf(myInt)).
                    replace("#V3", String.valueOf(theaterid)));

            System.out.println("Inserted");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Update table exception : " + exception.getMessage());
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

    public boolean isSeatTaken(Connection connection, int id, int theaterid)
    {
        boolean result = false;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            String checkSeat = CHECK_SEAT.replace("#V1",String.valueOf(id).replace("#V3", String.valueOf(theaterid)));
            ResultSet resultSet = statement.executeQuery(checkSeat);
            while (resultSet.next())
            {
                result = true;
            }
        }
        catch ( SQLException exception)
        {
            result = false;
            System.out.println("isSeatTaken table exception : " + exception.getMessage());
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
