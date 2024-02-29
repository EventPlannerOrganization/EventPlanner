package launchers;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import Exceptions.WeakPasswordException;
import models.EventPlanner;
import views.SignUpView;

public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException, UserNotFoundException {
       // SignUpView.signUpView();
        EventPlanner.getUserByUsername("Naser");
    }
}