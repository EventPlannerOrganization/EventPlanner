package launchers;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import models.EventPlanner;
import views.LoginView;
import views.SignUpView;


public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException {
        EventPlanner.initializeRepositoryWithData();
        LoginView.canLoginView();

    }
}