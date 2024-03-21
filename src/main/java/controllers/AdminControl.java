package controllers;

import models.*;

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
    public static List<String> getAllServiceProviders(){
        return getUserNameOfServiceProviders(EventPlanner.getServiceProviders());
    }

    public static List<String> getEventsForUser(User user){
        List<String> events=new ArrayList<>();
        for (RegisteredEvent event:user.getRegisteredEvents()){
            events.add(event.getEventName());
        }
        return events;
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
    public static List<String> getUserNameOfServiceProviders(List<ServiceProvider> serviceProviders){
        List<String>userNames=new ArrayList<>();
        for (ServiceProvider element:serviceProviders){
            userNames.add(element.getAuthentication().getUsername());
        }
        return userNames;
    }

    public static void deleteUser(Person deletedUser) {
        EventPlanner.getUsersList().remove(deletedUser);

    }

    public static void resetPassword(Person user, String newPassword) {
        user.getAuthentication().setPassword(newPassword);
    }

    public static List<ServiceProvider> searchServiceProviders(String searchTerm) {
        List<ServiceProvider> searchResults = new ArrayList<>();
        String searchTermLowerCase = searchTerm.toLowerCase(); // Convert search term to lowercase

        for (ServiceProvider user : EventPlanner.getServiceProviders()) {
            String userNameLowerCase = user.getAuthentication().getUsername().toLowerCase(); // Convert username to lowercase
            if (userNameLowerCase.contains(searchTermLowerCase)) { // Partial match check
                searchResults.add(user);
            }
        }
        if(searchResults.isEmpty())searchResults=null;
        return searchResults;
    }
}
