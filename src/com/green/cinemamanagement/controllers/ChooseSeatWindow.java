package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseSeatWindow extends BaseController implements Initializable {

    private int theater_id;

    public ChooseSeatWindow(ViewFactory viewFactory, String fxmlName, int theater_id) {
        super(viewFactory, fxmlName);
        this.theater_id = theater_id;
    }

    @FXML
    private Spinner<Integer> spinerGheThuong;

    @FXML
    private Spinner<Integer> spnGheThanhVien;

    @FXML
    private Button btnChonGhe;

    @FXML
    void onChonGheClicked(ActionEvent event) {
        if ( spinerGheThuong.getValue() == 0 && spnGheThanhVien.getValue() == 0 )
        {
            btnChonGhe.setText("ban phai chon ghe");
        }
        else
        {
            btnChonGhe.setText("Chọn ghế");
            viewFactory.showSeatWindow(theater_id, spinerGheThuong.getValue() + spnGheThanhVien.getValue());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spinerGheThuong.setEditable(true);
        spnGheThanhVien.setEditable(true);
        SpinnerValueFactory<Integer> svf1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5, 0);
        SpinnerValueFactory<Integer> svf2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5, 0);
        spinerGheThuong.setValueFactory(svf1);
        spnGheThanhVien.setValueFactory(svf2);
    }
}
