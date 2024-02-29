package controllers;

import models.EventPlanner;

public class UserControl {
    private UserControl(){

    }
    public static void  signout(){
        EventPlanner.setCurrentUser(null);
    }
}
