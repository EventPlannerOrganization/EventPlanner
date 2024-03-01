package launchers;

import Exceptions.UserIsAlreadyExist;
import models.EventPlanner;
import views.LoginView;


public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist {
        EventPlanner.initializeRepositoryWithData();
        LoginView.canLoginView();

    }
}