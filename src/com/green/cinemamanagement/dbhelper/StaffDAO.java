package com.green.cinemamanagement.dbhelper;

import com.green.cinemamanagement.models.Staff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StaffDAO {
    private static final String QUERY_STAFF = "SELECT * FROM STAFF.STAFF";
    private static final String QUERY_LOGIN = "SELECT * FROM STAFF.STAFF WHERE EMAIL = '#V1'";

    private static final String INSERT_TBL_STAFF_FULL =
            "INSERT INTO STAFF.STAFF (ID,FIRSTNAME,LASTNAME,ROLEE,EMAIL,PASS) VALUES(#V1,'#V2','#V3','#V4','#V5','#V6')";

    private static final String DELETE_TBL_STAFF =
            "DELETE FROM STAFF.STAFF WHERE ID = #V1";

    private static final String UPDATE_TBL_STAFF =
            "UPDATE STAFF.STAFF SET #VCol = '#V1' WHERE ID = #V2";

    public StaffDAO() {
    }

    public Staff getLoginInfo(Connection connection, String user)
    {
        Staff staff = new Staff();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String queryLogin = QUERY_LOGIN.replace("#V1", user);
            ResultSet resultSet = statement.executeQuery(queryLogin);
            while ( resultSet.next())
            {
                staff.setID(resultSet.getInt(1));
                staff.setFirstName(resultSet.getString(2));
                staff.setLastName(resultSet.getString(3));
                staff.setRole(resultSet.getString(4));
                staff.setEmail(resultSet.getString(5));
                staff.setPassword(resultSet.getString(6));
            }
        } catch (SQLException throwables) {
            System.out.println("Get login exception : " + throwables.getMessage());
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
        return staff;
    }

    public ArrayList<Staff> getAllStaffs(Connection connection)
    {
        ArrayList<Staff> staffs = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_STAFF);

            while (resultSet.next())
            {
                Staff staff = new Staff();
                staff.setID(resultSet.getInt(1));
                staff.setFirstName(resultSet.getString(2));
                staff.setLastName(resultSet.getString(3));
                staff.setRole(resultSet.getString(4));
                staff.setEmail(resultSet.getString(5));
                staff.setPassword(resultSet.getString(6));
                staffs.add(staff);
            }
        }
        catch ( SQLException exception)
        {
            System.out.println("Get staffs exception : " + exception.getMessage());
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
        return staffs;
    }

//    public int createTableStaff (Connection connection)
//    {
//        int result = 0;
//        Statement statement = null;
//        try
//        {
//            statement = connection.createStatement();
//            statement.executeUpdate(DROP_TBL_STAFF);
//            statement.executeUpdate(CREATE_TBL_STAFF);
//            System.out.println("Table Staff Created");
//        }
//        catch ( SQLException exception)
//        {
//            result = -1;
//            System.out.println("Create table exception : " + exception.getMessage());
//        }
//        finally {
//            if (statement != null)
//            {
//                try
//                {
//                    statement.close();
//                }
//                catch (SQLException exception)
//                {
//                    exception.printStackTrace();
//                }
//            }
//        }
//        return result;
//    }
//
    public int insertTableStaff(Connection connection, int id, String firstName, String lastName, String role, String email, String password)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_TBL_STAFF_FULL.replace("#V1",String.valueOf(id)).
                    replace("#V2",firstName).
                    replace("#V3",lastName).
                    replace("#V4",  role).
                    replace("#V5",email).
                    replace("#V6",password))
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

    public int deleteStaff(Connection connection, int id)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(DELETE_TBL_STAFF.replace("#V1",String.valueOf(id)));

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

    public int UpdateFirstNameTableStaff(Connection connection, int id, String firstName)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            System.out.println(UPDATE_TBL_STAFF.replace("#VCol","firstname").
                    replace("#V1",firstName).
                    replace("#V2",String.valueOf(id)));

            statement.executeUpdate(UPDATE_TBL_STAFF.replace("#VCol","firstname").
                    replace("#V1",firstName).
                    replace("#V2",String.valueOf(id)))
            ;

            System.out.println("Updated");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Updated first name exception : " + exception.getMessage());
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

    public int UpdateLastNameTableStaff(Connection connection, int id, String lastName)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_TBL_STAFF.replace("#VCol","lastname").
                    replace("#V1",lastName).
                    replace("#V2",String.valueOf(id)))
            ;

            System.out.println("Updated");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Updated last name exception : " + exception.getMessage());
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
