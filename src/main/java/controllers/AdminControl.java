package controllers;

import models.EventPlanner;
import models.Person;
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
    return getUserNameOfUsers(EventPlanner.getUsers());

    }


    public static List<User> searchUsers(String searchTerm) {
        List<User> searchResults = new ArrayList<>();
        String searchTermLowerCase = searchTerm.toLowerCase(); // Convert search term to lowercase

        for (User user : EventPlanner.getUsers()) {
            String userNameLowerCase = user.getAuthentication().getUsername().toLowerCase(); // Convert user name to lowercase
            if (userNameLowerCase.contains(searchTermLowerCase)) { // Partial match check
                searchResults.add(user);
            }
        }
        if(searchResults.isEmpty())searchResults=null;
        return searchResults;
    }

    public static List<String> getUserNameOfUsers(List<User> users){
        List<String>userNames=new ArrayList<>();
        for (User element:users){
            userNames.add(element.getAuthentication().getUsername());
        }
        return userNames;
    }

    public static void deleteUser(Person deletedUser) {
        EventPlanner.getUsersList().remove(deletedUser);

    }
}
