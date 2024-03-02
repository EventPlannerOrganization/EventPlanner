package launchers;

import Exceptions.UserIsAlreadyExist;
import models.EventPlanner;
import views.LoginView;
import views.SignUpView;


public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist {
        EventPlanner.initializeRepositoryWithData();
        SignUpView.signUpView();

    }
}