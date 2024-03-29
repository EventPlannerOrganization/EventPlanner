package controllers;


import Exceptions.UserNotFoundException;
import models.EventPlanner;
import models.Person;


public class Login {
    private Login() {

    }

    public static boolean canLogin(String username, String password) throws UserNotFoundException {
        Person person = EventPlanner.checkUser(username);
        if (person.getAuthentication().getPassword().equals(password)) {
            EventPlanner.setCurrentUser(person);
            return true;
        }
        return false;
    }


}
