package controller;

import view.BaseView;
import model.Model;

public class Controller {

    public void Controller() {

        BaseView view = new BaseView();
        Model model = new Model();

        view.setController(this);
    }


    public void InitializeController() {

    }
}