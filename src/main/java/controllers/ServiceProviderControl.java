package controllers;

import models.EventPlanner;

public class ServiceProviderControl {
    private ServiceProviderControl(){

    }
    public static void  signout(){
        EventPlanner.setCurrentUser(null);
    }
}
