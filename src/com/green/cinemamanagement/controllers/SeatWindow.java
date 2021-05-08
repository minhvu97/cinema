package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.connectors.DBConnector;
import com.green.cinemamanagement.dbhelper.MovieTheaterDAO;
import com.green.cinemamanagement.dbhelper.SeatDAO;
import com.green.cinemamanagement.models.Seat;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeatWindow extends BaseController implements Initializable {

    public SeatWindow(ViewFactory viewFactory, String fxmlName, int theater_id, int soGhe) {
        super(viewFactory, fxmlName);
        this.theater_id = theater_id;
        this.soGhe = soGhe;
    }
    private String TAG = "SeatWindow";
    private Connection connection;
    private SeatDAO seatDAO;
    private MovieTheaterDAO movieTheaterDAO;
    private ArrayList<Seat> listSeat;
    private int theater_id;
    private int soGhe;
    private int countSoGhe;
    private Alert alert;
    private ArrayList<Rectangle> listRecToBeAdded;
    private ArrayList<Rectangle> listRecToBeRemoved;
    @FXML
    private Label lblMovieName;

    @FXML
    private Button btnDatVe;

    @FXML
    void onDatVeClicked(ActionEvent event) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Alert");
        if(countSoGhe < soGhe)
        {
            alert.setContentText("Ban chua chon du so ghe");
            alert.showAndWait();
        }
        else
        {
            for (Seat seat: listSeat)
            {
                if (seat.getHoldNumber() == 2)
                {
                    alert.setContentText("ban khong the de thua 1 ghe giua");
                    alert.showAndWait();
                    return;
                }
            }
            for (Rectangle rec : listRecToBeAdded)
            {
                addToDataBase(rec);
            }
            for (Rectangle rec : listRecToBeRemoved)
            {
                deleteFromDatabase(rec);
            }
            viewFactory.closeStage((Stage)btnDatVe.getScene().getWindow());
        }

    }

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

    void setSeatID()
    {
        seat1_1.setId("11");
        seat1_2.setId("12");
        seat1_3.setId("13");
        seat1_4.setId("14");
        seat1_5.setId("15");
        seat1_6.setId("16");
        seat1_7.setId("17");
        seat1_8.setId("18");
        seat2_1.setId("21");
        seat2_2.setId("22");
        seat2_3.setId("23");
        seat2_4.setId("24");
        seat2_5.setId("25");
        seat2_6.setId("26");
        seat2_7.setId("27");
        seat2_8.setId("28");
        seat3_1.setId("31");
        seat3_2.setId("32");
        seat3_3.setId("33");
        seat3_4.setId("34");
        seat3_5.setId("35");
        seat3_6.setId("36");
        seat3_7.setId("37");
        seat3_8.setId("38");
        seat4_1.setId("41");
        seat4_2.setId("42");
        seat4_3.setId("43");
        seat4_4.setId("44");
        seat4_5.setId("45");
        seat4_6.setId("46");
        seat4_7.setId("47");
        seat4_8.setId("48");
    }

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
            if(((Rectangle) event.getSource()).getFill() == Color.DODGERBLUE)
            {
                if (countSoGhe < soGhe)
                {
                    ((Rectangle) event.getSource()).setFill(Color.RED);
                    listRecToBeAdded.add((Rectangle) event.getSource());
                    updateHoldNumber((Rectangle) event.getSource());
                    countSoGhe ++;
                }
            }
        }
        else if ( event.getButton() == MouseButton.SECONDARY) // get right button
        {
            if(((Rectangle) event.getSource()).getFill() == Color.RED) {
                ((Rectangle) event.getSource()).setFill(Color.DODGERBLUE);
                listRecToBeRemoved.add((Rectangle) event.getSource());
                resetHoldNumber((Rectangle) event.getSource());
                countSoGhe --;
            }

        }
    }

    void addToDataBase(Rectangle rec)
    {
        Seat seat = new Seat(Integer.parseInt(rec.getId()),true,theater_id);
        seatDAO.updateTableSeat(connection,seat.getId(),seat.isTaken(),seat.getTheaterId());
    }

    void deleteFromDatabase(Rectangle rec)
    {
        Seat seat = new Seat(Integer.parseInt(rec.getId()),false,theater_id);
        seatDAO.updateTableSeat(connection,seat.getId(),seat.isTaken(),seat.getTheaterId());
    }

    void updateHoldNumber(Rectangle rec)
    {
        int i = 0;
        Seat seat = new Seat(Integer.parseInt(rec.getId()),true,theater_id);
        for (Seat s:listSeat)
        {
            if (s.getId() == seat.getId())
            {
                i = listSeat.indexOf(s);
                listSeat.set(i,seat);
                break;
            }
        }

        listSeat.get(i).resetHoldNumber();
        if (i != 0) {
            // this part for avoid middle empty seat
            if (listSeat.get(i - 1).getId() != 18 && listSeat.get(i - 1).getId() != 28 && listSeat.get(i - 1).getId() != 38) {
                if (listSeat.get(i - 1).isTaken() == false) {
                    listSeat.get(i - 1).increaseHoldNumber();
                    System.out.println(listSeat.get(i - 1).getId() +" listSeat.get(i - 1).getHoldNumber = " + listSeat.get(i - 1).getHoldNumber());
                }
            }
        }
        if (i != Seat.NUMBEROFSEAT - 1)
        {
            if (listSeat.get(i + 1).getId() != 21 && listSeat.get(i + 1).getId() != 31 && listSeat.get(i + 1).getId() != 41) {
                if (listSeat.get(i + 1).isTaken() == false) {
                    listSeat.get(i + 1).increaseHoldNumber();
                    System.out.println(listSeat.get(i + 1).getId() +" listSeat.get(i + 1).getHoldNumber = " + listSeat.get(i + 1).getHoldNumber());
                }
            }
        }
    }

    void resetHoldNumber(Rectangle rec)
    {
        int i = 0;
        Seat seat = new Seat(Integer.parseInt(rec.getId()),false,theater_id);
        for (Seat s:listSeat)
        {
            if (s.getId() == seat.getId())
            {
                i = listSeat.indexOf(s);
                listSeat.set(i,seat);
                break;
            }
        }

        listSeat.get(i).resetHoldNumber();
        if (i != 0) {
            if (listRec[i - 1].getFill() != Color.DARKRED) {
                if (listSeat.get(i - 1).getId() != 18 || listSeat.get(i - 1).getId() != 28 || listSeat.get(i - 1).getId() != 38) {
                    if (listSeat.get(i - 1).isTaken() == true) {
                        listSeat.get(i).increaseHoldNumber();
                    } else {
                        listSeat.get(i - 1).decreaseHoldNumber();
                    }
                }
            }
        }
        if (i != Seat.NUMBEROFSEAT) {
            if (listRec[i + 1].getFill() != Color.DARKRED) {
                if (listSeat.get(i + 1).getId() != 21 || listSeat.get(i + 1).getId() != 31 || listSeat.get(i + 1).getId() != 41) {
                    if (listSeat.get(i + 1).isTaken() == true) {
                        listSeat.get(i).increaseHoldNumber();
                    } else {
                        listSeat.get(i + 1).decreaseHoldNumber();
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = new DBConnector().getDBMovieTheaterConnection();
        seatDAO = new SeatDAO();
        movieTheaterDAO = new MovieTheaterDAO();
        listSeat = seatDAO.getAllSeatWithTheaterID(connection, theater_id);
        addRectangle();
        setSeatID();
        countSoGhe = 0 ;
        listRecToBeAdded = new ArrayList<>();
        listRecToBeRemoved = new ArrayList<>();
        // set movie name
        lblMovieName.setText(movieTheaterDAO.getMovieNameByID(connection, theater_id));

        for (int i = 0 ; i < Seat.NUMBEROFSEAT; i++)
        {
            listSeat.get(i).resetHoldNumber();
            if (listSeat.get(i).isTaken())
            {
                listRec[i].setFill(Color.DARKRED);
            }
        }
    }
}
