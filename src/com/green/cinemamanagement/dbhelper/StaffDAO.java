package com.green.cinemamanagement.dbhelper;

import com.green.cinemamanagement.models.Login;
import com.green.cinemamanagement.models.Staff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StaffDAO {
    private static final String QUERY_STAFF = "SELECT * FROM STAFF";
    private static final String QUERY_LOGIN = "SELECT * FROM LOGIN";
    private static final String DROP_TBL_STAFF = "DROP TABLE IF EXISTS STAFF";
    private static final String DROP_TBL_LOGIN = "DROP TABLE IF EXISTS LOGIN";
    private static final String CREATE_TBL_STAFF =
            "CREATE TABLE STAFF ("
                    +"ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    +"FIRSTNAME VARCHAR(120),"
                    +"LASTNAME VARCHAR(120)"
                    +")"
            ;
    private static final String CREATE_TBL_LOGIN =
            "CREATE TABLE LOGIN ("
                    +"ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    +"USER VARCHAR(120),"
                    +"PASS VARCHAR(120)"
                    +")"
            ;
    private static final String INSERT_TBL_STAFF =
            "INSERT INTO STAFF (FIRSTNAME,LASTNAME) VALUES('#V1','#V2')";
    private static final String INSERT_TBL_LOGIN =
            "INSERT INTO LOGIN (USER,PASS) VALUES('#V1','#V2')";

    private static final String INSERT_TBL_STAFF_FULL =
            "INSERT INTO STAFF (ID,FIRSTNAME,LASTNAME) VALUES(#V1,'#V2','#V3')";

    private static final String DELETE_TBL_STAFF =
            "DELETE FROM STAFF WHERE ID = #V1";

    private static final String UPDATE_TBL_STAFF =
            "UPDATE STAFF SET ID = #V1, FIRSTNAME = '#V2', LASTNAME = '#V3'";

    private static final String UPDATE_TBL_STAFF_NONE_ID =
            "UPDATE STAFF SET FIRSTNAME = '#V2', LASTNAME = '#V3' WHERE ID = #V1";

    public StaffDAO() {
    }

    public Login getLoginInfo(Connection connection)
    {
        Login login = new Login();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_LOGIN);
            while ( resultSet.next())
            {
                login.setId(resultSet.getInt(1));
                login.setEmail(resultSet.getString(2));
                login.setPassword(resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            System.out.println("Get staffs exception : " + throwables.getMessage());
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
        return login;
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

    public int createTableStaff (Connection connection)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(DROP_TBL_STAFF);
            statement.executeUpdate(CREATE_TBL_STAFF);
            System.out.println("Table Staff Created");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Create table exception : " + exception.getMessage());
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

    public int insertTableStaff(Connection connection, String firstName, String lastName)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_TBL_STAFF.replace("#V1",firstName).replace("#V2",lastName));

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

    public int createTableLogin (Connection connection)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(DROP_TBL_LOGIN);
            statement.executeUpdate(CREATE_TBL_LOGIN);
            System.out.println("Table Staff Created");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Create table exception : " + exception.getMessage());
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

    public int insertTableLogin(Connection connection, String user, String pass)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_TBL_LOGIN.replace("#V1",user).replace("#V2",pass));

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
