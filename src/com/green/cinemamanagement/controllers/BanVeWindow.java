package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.SeatDAO;
import com.green.cinemamanagement.models.Seat;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class BanVeWindow extends BaseController implements Initializable {

    public BanVeWindow(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    private String TAG = "SeatWindow";
    private Connection connection;
    private SeatDAO seatDAO;
    private ArrayList<Seat> listSeat;

    @FXML
    private Rectangle seat1_1;

    @FXML
    private Rectangle seat1_2;

    @FXML
    private Rectangle seat1_3;

    @FXML
    private Rectangle seat1_4;

    @FXML
    private Rectangle seat1_5;

    @FXML
    private Rectangle seat1_6;

    @FXML
    private Rectangle seat1_7;

    @FXML
    private Rectangle seat1_8;

    @FXML
    private Rectangle seat2_1;

    @FXML
    private Rectangle seat2_2;

    @FXML
    private Rectangle seat2_3;

    @FXML
    private Rectangle seat2_4;

    @FXML
    private Rectangle seat2_5;

    @FXML
    private Rectangle seat2_6;

    @FXML
    private Rectangle seat2_7;

    @FXML
    private Rectangle seat2_8;

    @FXML
    private Rectangle seat3_1;

    @FXML
    private Rectangle seat3_2;

    @FXML
    private Rectangle seat3_3;

    @FXML
    private Rectangle seat3_4;

    @FXML
    private Rectangle seat3_5;

    @FXML
    private Rectangle seat3_6;

    @FXML
    private Rectangle seat3_7;

    @FXML
    private Rectangle seat3_8;

    @FXML
    private Rectangle seat4_1;

    @FXML
    private Rectangle seat4_2;

    @FXML
    private Rectangle seat4_3;

    @FXML
    private Rectangle seat4_4;

    @FXML
    private Rectangle seat4_5;

    @FXML
    private Rectangle seat4_6;

    @FXML
    private Rectangle seat4_7;

    @FXML
    private Rectangle seat4_8;

    void setSeatID(Rectangle rec)
    {
        if (rec.equals(seat1_1))
        {
            seat1_1.setId("11");
        }
        else if (rec.equals(seat1_2))
        {
            seat1_2.setId("12");
        }
        else if (rec.equals(seat1_3))
        {
            seat1_3.setId("13");
        }
        else if (rec.equals(seat1_4))
        {
            seat1_4.setId("14");
        }
        else if (rec.equals(seat1_5))
        {
            seat1_5.setId("15");
        }
        else if (rec.equals(seat1_6))
        {
            seat1_6.setId("16");
        }
        else if (rec.equals(seat1_7))
        {
            seat1_7.setId("17");
        }
        else if (rec.equals(seat1_8))
        {
            seat1_8.setId("18");
        }
        else if (rec.equals(seat2_1))
        {
            seat2_1.setId("21");
        }
        else if (rec.equals(seat2_2))
        {
            seat2_2.setId("22");
        }
        else if (rec.equals(seat2_3))
        {
            seat2_3.setId("23");
        }
        else if (rec.equals(seat2_4))
        {
            seat2_4.setId("24");
        }
        else if (rec.equals(seat2_5))
        {
            seat2_5.setId("25");
        }
        else if (rec.equals(seat2_6))
        {
            seat2_6.setId("26");
        }
        else if (rec.equals(seat2_7))
        {
            seat2_7.setId("27");
        }
        else if (rec.equals(seat2_8))
        {
            seat2_8.setId("28");
        }
        else if (rec.equals(seat3_1))
        {
            seat3_1.setId("31");
        }
        else if (rec.equals(seat3_2))
        {
            seat3_2.setId("32");
        }
        else if (rec.equals(seat3_3))
        {
            seat3_3.setId("33");
        }
        else if (rec.equals(seat3_4))
        {
            seat3_4.setId("34");
        }
        else if (rec.equals(seat3_5))
        {
            seat3_5.setId("35");
        }
        else if (rec.equals(seat3_6))
        {
            seat3_6.setId("36");
        }
        else if (rec.equals(seat3_7))
        {
            seat3_7.setId("37");
        }
        else if (rec.equals(seat3_8))
        {
            seat3_8.setId("38");
        }
        else if (rec.equals(seat4_1))
        {
            seat4_1.setId("41");
        }
        else if (rec.equals(seat4_2))
        {
            seat4_2.setId("42");
        }
        else if (rec.equals(seat4_3))
        {
            seat4_3.setId("43");
        }
        else if (rec.equals(seat4_4))
        {
            seat4_4.setId("44");
        }
        else if (rec.equals(seat4_5))
        {
            seat4_5.setId("45");
        }
        else if (rec.equals(seat4_6))
        {
            seat4_6.setId("46");
        }
        else if (rec.equals(seat4_7))
        {
            seat4_7.setId("47");
        }
        else if (rec.equals(seat4_8))
        {
            seat4_8.setId("48");
        }
    }

//    List<Rectangle> listRec = Arrays.asList(seat1_1,seat1_2,seat1_3,seat1_4,seat1_5,seat1_6,seat1_7,seat1_8,
//                                            seat2_1,seat2_2,seat2_3,seat2_4,seat2_5,seat2_6,seat2_7,seat2_8,
//                                            seat3_1,seat3_2,seat3_3,seat3_4,seat3_5,seat3_6,seat3_7,seat3_8,
//                                            seat4_1,seat4_2,seat4_3,seat4_4,seat4_5,seat4_6,seat4_7,seat4_8);
    private Rectangle[] listRec = new Rectangle[Seat.NUMBEROFSEAT] ;

    void addRectangle()
    {
        listRec[0] = seat1_1;
        listRec[1] = seat1_2;
        listRec[2] = seat1_3;
        listRec[3] = seat1_4;
        listRec[4] = seat1_5;
        listRec[5] = seat1_6;
        listRec[6] = seat1_7;
        listRec[7] = seat1_8;
        listRec[8] = seat2_1;
        listRec[9] = seat2_2;
        listRec[10] = seat2_3;
        listRec[11] = seat2_4;
        listRec[12] = seat2_5;
        listRec[13] = seat2_6;
        listRec[14] = seat2_7;
        listRec[15] = seat2_8;
        listRec[16] = seat3_1;
        listRec[17] = seat3_2;
        listRec[18] = seat3_3;
        listRec[19] = seat3_4;
        listRec[20] = seat3_5;
        listRec[21] = seat3_6;
        listRec[22] = seat3_7;
        listRec[23] = seat3_8;
        listRec[24] = seat4_1;
        listRec[25] = seat4_2;
        listRec[26] = seat4_3;
        listRec[27] = seat4_4;
        listRec[28] = seat4_5;
        listRec[29] = seat4_6;
        listRec[30] = seat4_7;
        listRec[31] = seat4_8;
    }


    @FXML
    void onMouseClicked(MouseEvent event) {

        if (event.getButton() == MouseButton.PRIMARY) // get left button
        {
            if(((Rectangle) event.getSource()).getFill() != Color.RED)
            {
                ((Rectangle) event.getSource()).setFill(Color.RED);
                addToDataBase((Rectangle) event.getSource());
            }
        }
    }

    void addToDataBase(Rectangle rec)
    {
//        setSeatID(rec);
        Seat seat = new Seat(Integer.parseInt(rec.getId()),true,1);
        seatDAO.updateTableSeat(connection,seat.getId(),seat.isTaken(),seat.getTheaterId());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // hien thi cac dong du lieu
        connection = new DBConnector().getDBMovieTheaterConnection();
        seatDAO = new SeatDAO();
        listSeat = seatDAO.getAllSeat(connection);
        addRectangle();
        for (Seat seat : listSeat)
        {
            if (seat.isTaken())
            {
                for (int i = 0 ; i < Seat.NUMBEROFSEAT; i++)
                {
                    setSeatID(listRec[i]);
                    if (Integer.parseInt(listRec[i].getId()) == (seat.getId()))
                    {
                        listRec[i].setFill(Color.RED);
                    }
                }

            }
        }
    }
}
