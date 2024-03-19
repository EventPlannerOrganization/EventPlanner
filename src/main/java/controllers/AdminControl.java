package controllers;

import models.EventPlanner;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class AdminControl {
    private AdminControl() {
    }

    public static void  signout(){
        EventPlanner.setCurrentUser(null);
    }

    public static List<String> getAllUsers(){
        List<String>userNames=new ArrayList<>();
    for (User element:EventPlanner.getUsers()){
        userNames.add(element.getAuthentication().getUsername());
    }
    return userNames;
    }

}
