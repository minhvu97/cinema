package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.views.ViewFactory;

public class BaseController {

    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(ViewFactory viewFactory, String fxmlName)
    {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public BaseController() {

    }

    public String getFxmlName() {
        return fxmlName;
    }

}
