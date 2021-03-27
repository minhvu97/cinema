package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.constant.ColorTheme;
import com.green.cinemamanagement.constant.FontSize;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionWindow extends BaseController implements Initializable {

    public final String TAG = "Button: ";
    public OptionWindow(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private Label lblOption;

    @FXML
    private Slider sliderFontSize;

    @FXML
    private ComboBox<ColorTheme> cmbColorTheme;

    @FXML
    private Button btnApply;

    @FXML
    private Button btnCancel;


    @FXML
    void actApply(ActionEvent event) {
        System.out.println(TAG+"applyAction");
        closeStage();
    }

    @FXML
    void actCancel(ActionEvent event) {
        System.out.println(TAG+"cancelAction");
        closeStage();
    }

    private void closeStage()
    {
        Stage stage = (Stage)btnApply.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void actSlider(MouseEvent event) {
        if (sliderFontSize.getValue() == 0)
        {
            lblOption.setFont(new Font("Arial", 10));
        }
        else if (sliderFontSize.getValue() == FontSize.values().length - 2)
        {
            lblOption.setFont(new Font("Arial", 20));
        }
        else
        {
            lblOption.setFont(new Font("Arial", 30));
        }
        lblOption.setText(sliderFontSize.getValue()+ "");
    }

    private void initChoiceBoxTheme()
    {
        cmbColorTheme.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        cmbColorTheme.setValue(viewFactory.getColorTheme());
    }

    private void initSliderFontSize()
    {
        sliderFontSize.setMin(0);
        sliderFontSize.setMax(FontSize.values().length -1 );
        sliderFontSize.setShowTickLabels(true);
        sliderFontSize.setShowTickMarks(true);
        sliderFontSize.setSnapToTicks(true);
        sliderFontSize.showTickLabelsProperty();
        sliderFontSize.setMajorTickUnit(1);
        sliderFontSize.setMinorTickCount(0);
        sliderFontSize.setBlockIncrement(1);
        sliderFontSize.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                int i = object.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });


        sliderFontSize.setValue(viewFactory.getFontSize().ordinal());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initChoiceBoxTheme();
        initSliderFontSize();
    }
}
