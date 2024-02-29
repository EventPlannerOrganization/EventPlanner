package launchers;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import Exceptions.WeakPasswordException;
import models.EventPlanner;
import views.LoginView;
import views.SignUpView;

public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException, UserNotFoundException {
        EventPlanner.initializeRepositoryWithData();
        LoginView.canLoginView();

    }
}