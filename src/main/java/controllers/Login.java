package controllers;

import Exceptions.*;
import Exceptions.EventNotFound;
import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import Exceptions.WeakPasswordException;
import enumerations.UserType;
import models.EventPlanner;
import models.Person;
import models.ServiceProvider;
import models.User;
import views.AdminView;
import views.ServiceProviderView;
import views.UserView;

import javax.mail.MessagingException;
import java.io.IOException;

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

        public static void whosLogin () throws UserIsAlreadyExist, WeakPasswordException, UserNotFoundException, EventNotFound, MessagingException, IOException, EventAlreadyExist {
            Person current = EventPlanner.getCurrentUser();
            if (current instanceof ServiceProvider) ServiceProviderView.providerMenu();
            else if (((User) current).getUsertype() == UserType.USER) UserView.userMenu();
            else AdminView.adminMenu();

        }

}
