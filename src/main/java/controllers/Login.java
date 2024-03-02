package controllers;

import Exceptions.UserNotFoundException;
import models.EventPlanner;
import models.Person;
import models.ServiceProvider;
import models.User;
import views.ServiceProviderView;
import views.UserView;

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


    public static void whosLogin() {
       Person current= EventPlanner.getCurrentUser();
       if(current instanceof User){
           UserView.userMenu();
       }
       else if (current instanceof ServiceProvider){
           ServiceProviderView.providerMenu();
       }
    }
}
