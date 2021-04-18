package com.green.cinemamanagement.views;

import com.green.cinemamanagement.constant.ColorTheme;
import com.green.cinemamanagement.constant.FontSize;
import com.green.cinemamanagement.controllers.*;
import com.green.cinemamanagement.managers.DBManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private FontSize fontSize = FontSize.SMALL;
    private ColorTheme colorTheme = ColorTheme.DEFAULT;

    private DBManager dbManager;

    public ViewFactory() {

    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public ViewFactory(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void showLoginWindow()
    {
        System.out.println("showing login window");
        BaseController controller = new LoginWindow(this, "LoginWindow.fxml");
        initializeStage(controller);
    }

    public void showMainWindow()
    {
        System.out.println("showing main window");
        BaseController controller = new MainWindow(this, "MainWindow.fxml");
        initializeStage(controller);
    }

    public void showOptionWindow()
    {
        System.out.println("showing option window");
        BaseController controller = new OptionWindow(this, "OptionWindow.fxml");
        initializeStage(controller, true);

    }

    public void showStaffWindow()
    {
        System.out.println("showing staff window");
        BaseController controller = new StaffWindow(this, "StaffWindow.fxml");
        initializeStage(controller, true);

    }

    public void showAddMemberWindow(AddMemberWindow.AddMemberInterface listener)
    {
        System.out.println("showing add member window");
        BaseController controller = new AddMemberWindow(this, "AddMemberWindow.fxml", listener);
        initializeStage(controller, true);
    }

    public void showMovieTheaterWindow()
    {
        System.out.println("showing movie theater window");
        BaseController controller = new MovieTheaterWindow(this, "MovieTheaterWindow.fxml");
        initializeStage(controller, true);
    }

    public void showAddMovieTheaterWindow(AddMovieTheaterWindow.AddMovieTheaterInterface listener)
    {
        System.out.println("showing add movie theater window");
        BaseController controller = new AddMovieTheaterWindow(this, "AddMovieTheaterWindow.fxml", listener);
        initializeStage(controller, true);
    }

    public void showBanVeWindow()
    {
        System.out.println("showing ban ve window");
        BaseController controller = new BanVeWindow(this,"BanVeWindow.fxml");
        initializeStage(controller,true);
    }

    public void showSeatWindow(int theater_id)
    {
        System.out.println("showing seat window");
        BaseController controller = new SeatWindow(this,"SeatWindow.fxml", theater_id);
        initializeStage(controller,true);
    }
    public void showMovieTheaterList()
    {
        System.out.println("showing seat window");
        BaseController controller = new ShowMovieTheaterList(this,"ShowMovieTheaterList.fxml");
        initializeStage(controller,true);
    }

    public void showStaffList()
    {
        System.out.println("showing staff window");
        BaseController controller = new ShowStaffList(this,"ShowStaffList.fxml");
        initializeStage(controller,true);
    }

    public void closeStage(Stage stage)
    {
        stage.close();
    }

    private void initializeStage ( BaseController controller)
    {
        initializeStage(controller, false);
    }

    private void initializeStage ( BaseController controller, boolean isModal)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);

        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("initializeStage: failed to load fxml");
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        if (isModal)
        {
            stage.initModality(Modality.APPLICATION_MODAL);// dong nay de block all checkbox khi message hien len
        }
        stage.setScene(scene);
        stage.setTitle(controller.getFxmlName());
        stage.show();
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }
}
