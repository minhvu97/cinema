package com.green.cinemamanagement.dbhelper;

import com.green.cinemamanagement.models.MovieTheater;

import java.sql.*;
import java.util.ArrayList;

public class MovieTheaterDAO {
    private static final String QUERY_THEATER = "SELECT * FROM movietheater.theater";
    private static final String QUERY_THEATER_BYID = "SELECT * FROM movietheater.theater where ID = ?";

    private static final String DELETE_TBL_THEATER =
            "DELETE FROM movietheater.theater WHERE ID = #V1";
    private static final String DELETE_TBL_SEAT =
            "DELETE FROM movietheater.seat WHERE theaterid = #V1";
    private static final String INSERT_TBL_MOVIETHEATER =
            "INSERT INTO movietheater.theater (CUMRAP,THANHPHO, PHIM,SUATCHIEU) VALUES('#V1','#V2','#V3','#V4')";
    private static final String INSERT_TBL_SEAT =
            "insert into movietheater.seat(id,theaterid)" +
                    "values(11,#V1),(12,#V1),(13,#V1),(14,#V1),(15,#V1),(16,#V1),(17,#V1),(18,#V1)," +
                    "(21,#V1),(22,#V1),(23,#V1),(24,#V1),(25,#V1),(26,#V1),(27,#V1),(28,#V1)," +
                    "(31,#V1),(32,#V1),(33,#V1),(34,#V1),(35,#V1),(36,#V1),(37,#V1),(38,#V1)," +
                    "(41,#V1),(42,#V1),(43,#V1),(44,#V1),(45,#V1),(46,#V1),(47,#V1),(48,#V1)";

    private static final String QUERY_CITY = "SELECT CUMRAP FROM MOVIETHEATER.THEATER WHERE THANHPHO = '#V1'";
    private static final String QUERY_CUMRAP = "SELECT PHIM FROM MOVIETHEATER.THEATER WHERE THANHPHO = '#V1' AND CUMRAP = '#V2'";
    private static final String QUERY_PHIM = "SELECT SUATCHIEU FROM MOVIETHEATER.THEATER WHERE THANHPHO = '#V1' AND CUMRAP = '#V2' AND PHIM = '#V3'";
    private static final String QUERY_THEATER_ID = "SELECT ID FROM MOVIETHEATER.THEATER WHERE THANHPHO = '#V1' AND CUMRAP = '#V2' AND PHIM = '#V3' AND SUATCHIEU = '#V4'";

    private static final String QUERY_TEXTFIELD = "SELECT * FROM MOVIETHEATER.THEATER WHERE LOCATE(?,PHIM) = 1";

    private static final String QUERY_ALL_THANHPHO = "SELECT DISTINCT THANHPHO FROM MOVIETHEATER.THEATER";
    private static final String QUERY_ALL_CUMRAP = "SELECT DISTINCT CUMRAP FROM MOVIETHEATER.THEATER";
    private static final String QUERY_ALL_PHIM = "SELECT DISTINCT PHIM FROM MOVIETHEATER.THEATER";
    private static final String QUERY_ALL_SUATCHIEU = "SELECT DISTINCT SUATCHIEU FROM MOVIETHEATER.THEATER";

    private static final String QUERY_COMBOBOX = "SELECT * FROM MOVIETHEATER.THEATER WHERE THANHPHO #V1 AND CUMRAP #V2 AND PHIM #V3 AND SUATCHIEU #V4";

    private static final String UPDATE_TBL_THEATER =
            "UPDATE movietheater.theater SET #VCol = '#V1' WHERE ID = #V2";

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
                theater.setPhim(resultSet.getString(4));
                theater.setSuatChieu(resultSet.getString(5));
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

    public String getMovieNameByID(Connection connection, int id)
    {
        String name = "";
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement(QUERY_THEATER_BYID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                name = resultSet.getString(4);
            }
        }
        catch ( SQLException exception)
        {
            System.out.println("Get name by id exception : " + exception.getMessage());
        }
        finally {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                }
            }
        }
        return name;
    }

    public ArrayList<MovieTheater> getTextFieldResult(Connection connection, String phim)
    {
        ArrayList<MovieTheater> theaters = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement(QUERY_TEXTFIELD);
            preparedStatement.setString(1,phim);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                MovieTheater theater = new MovieTheater();
                theater.setId(resultSet.getInt(1));
                theater.setCumRap(resultSet.getString(2));
                theater.setThanhPho(resultSet.getString(3));
                theater.setPhim(resultSet.getString(4));
                theater.setSuatChieu(resultSet.getString(5));
                theaters.add(theater);
            }
        }
        catch ( SQLException exception)
        {
            System.out.println("Get text field input exception : " + exception.getMessage());
        }
        finally {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException exception)
                {
                    exception.printStackTrace();
                }
            }
        }
        return theaters;
    }

    public ArrayList<MovieTheater> getComboboxResult(Connection connection, String thanhpho, String cumrap, String phim, String suatchieu)
    {
        ArrayList<MovieTheater> theaters = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();

            String thanhphoString, cumrapString, phimString, suatchieuString;

            if (thanhpho.equals("Tất cả"))
            {
                thanhphoString = "in (SELECT THANHPHO FROM MOVIETHEATER.THEATER)";
            }
            else
            {
                thanhphoString = "= '" + thanhpho + "'";
            }
            if (cumrap.equals("Tất cả"))
            {
                cumrapString = "in (SELECT CUMRAP FROM MOVIETHEATER.THEATER)";
            }
            else
            {
                cumrapString = "= '" + cumrap + "'";
            }
            if (phim.equals("Tất cả"))
            {
                phimString = "in (SELECT PHIM FROM MOVIETHEATER.THEATER)";
            }
            else
            {
                phimString = "= '" + phim + "'";
            }
            if (suatchieu.equals("Tất cả"))
            {
                suatchieuString = "in (SELECT SUATCHIEU FROM MOVIETHEATER.THEATER)";
            }
            else
            {
                suatchieuString = "= '" + suatchieu + "'";
            }
            String queryString =
                    QUERY_COMBOBOX.replace("#V1",thanhphoString)
                            .replace("#V2", cumrapString)
                            .replace("#V3",phimString)
                            .replace("#V4",suatchieuString);

            ResultSet resultSet = statement.executeQuery(queryString);

            while (resultSet.next())
            {
                MovieTheater theater = new MovieTheater();
                theater.setId(resultSet.getInt(1));
                theater.setCumRap(resultSet.getString(2));
                theater.setThanhPho(resultSet.getString(3));
                theater.setPhim(resultSet.getString(4));
                theater.setSuatChieu(resultSet.getString(5));
                theaters.add(theater);
            }
        }
        catch ( SQLException exception)
        {
            System.out.println("Get combobox exception : " + exception.getMessage());
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

    public ArrayList<String> getAllCity(Connection connection)
    {
        ArrayList<String> listThanhpho = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL_THANHPHO);

            while (resultSet.next())
            {
                String thanhpho = resultSet.getString(1);
                listThanhpho.add(thanhpho);
            }
        }
        catch ( SQLException exception)
        {
            System.out.println("Get thanhpho exception : " + exception.getMessage());
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
        return listThanhpho;
    }

    public ArrayList<String> getAllCumRap(Connection connection)
    {
        ArrayList<String> listCumrap = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL_CUMRAP);

            while (resultSet.next())
            {
                String cumrap = resultSet.getString(1);
                listCumrap.add(cumrap);
            }
        }
        catch ( SQLException exception)
        {
            System.out.println("Get cumrap exception : " + exception.getMessage());
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
        return listCumrap;
    }

    public ArrayList<String> getAllPhim(Connection connection)
    {
        ArrayList<String> listPhim = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL_PHIM);

            while (resultSet.next())
            {
                String phim = resultSet.getString(1);
                listPhim.add(phim);
            }
        }
        catch ( SQLException exception)
        {
            System.out.println("Get phim exception : " + exception.getMessage());
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
        return listPhim;
    }

    public ArrayList<String> getAllSuatChieu(Connection connection)
    {
        ArrayList<String> listSuatChieu = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL_SUATCHIEU);

            while (resultSet.next())
            {
                String suatChieu = resultSet.getString(1);
                listSuatChieu.add(suatChieu);
            }
        }
        catch ( SQLException exception)
        {
            System.out.println("Get suatChieu exception : " + exception.getMessage());
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
        return listSuatChieu;
    }

    public ArrayList<String> getAllTheaterAtCity(Connection connection, String thanhpho)
    {
        ArrayList<String> theaters = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_CITY.replace("#V1",thanhpho));

            while (resultSet.next())
            {
                String theater = resultSet.getString(1);
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

    public ArrayList<String> getAllTheaterAtCityAtCumRap(Connection connection, String thanhpho, String cumrap)
    {
        ArrayList<String> movies = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_CUMRAP.replace("#V1",thanhpho).replace("#V2",cumrap));

            while (resultSet.next())
            {
                String movie = resultSet.getString(1);
                movies.add(movie);
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
        return movies;
    }

    public ArrayList<String> getAllTheaterAtCityAtCumRapWithMovie(Connection connection, String thanhpho, String cumrap, String phim)
    {
        ArrayList<String> suatChieus = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_PHIM.replace("#V1",thanhpho).replace("#V2",cumrap).replace("#V3",phim));

            while (resultSet.next())
            {
                String suatChieu = resultSet.getString(1);
                suatChieus.add(suatChieu);
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
        return suatChieus;
    }

    public int getTheaterID(Connection connection, String thanhpho, String cumrap, String phim, String suatChieu)
    {
        int theater_id = -1;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_THEATER_ID.
                    replace("#V1",thanhpho).
                    replace("#V2",cumrap).
                    replace("#V3",phim).
                    replace("#V4",suatChieu));

            while (resultSet.next())
            {
                theater_id = resultSet.getInt(1);
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
        return theater_id;
    }

    public int deleteMovieTheater(Connection connection, int id)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(DELETE_TBL_SEAT.replace("#V1",String.valueOf(id))); // seat must be delete before theater
            statement.executeUpdate(DELETE_TBL_THEATER.replace("#V1",String.valueOf(id)));
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

    public int insertTableMovieTheater(Connection connection, String cumRap, String thanhPho, String phim, String suatChieu)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(INSERT_TBL_MOVIETHEATER.replace("#V1",cumRap).
                    replace("#V2",thanhPho).
                    replace("#V3",phim).
                    replace("#V4",suatChieu))
            ;
            statement.executeUpdate(INSERT_TBL_SEAT.
                    replace("#V1",String.valueOf(getTheaterID(connection,thanhPho,cumRap,phim,suatChieu))));

            System.out.println("Inserted");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Insert table seat exception : " + exception.getMessage());
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

    public int UpdateCityTableTheater(Connection connection, int id, String thanhPho)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_TBL_THEATER.replace("#VCol","thanhpho").
                    replace("#V1",thanhPho).
                    replace("#V2",String.valueOf(id)))
            ;

            System.out.println("Updated");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Updated city exception : " + exception.getMessage());
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

    public int UpdateCumRapTableTheater(Connection connection, int id, String cumrap)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_TBL_THEATER.replace("#VCol","cumrap").
                    replace("#V1",cumrap).
                    replace("#V2",String.valueOf(id)))
            ;

            System.out.println("Updated");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Updated cum rap exception : " + exception.getMessage());
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

    public int UpdatePhimTableTheater(Connection connection, int id, String phim)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_TBL_THEATER.replace("#VCol","phim").
                    replace("#V1",phim).
                    replace("#V2",String.valueOf(id)))
            ;

            System.out.println("Updated");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Updated phim exception : " + exception.getMessage());
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

    public int UpdateSuatChieuTableTheater(Connection connection, int id, String suatchieu)
    {
        int result = 0;
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_TBL_THEATER.replace("#VCol","suatchieu").
                    replace("#V1",suatchieu).
                    replace("#V2",String.valueOf(id)))
            ;

            System.out.println("Updated");
        }
        catch ( SQLException exception)
        {
            result = -1;
            System.out.println("Updated suatchieu exception : " + exception.getMessage());
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
