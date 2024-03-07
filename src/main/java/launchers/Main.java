package launchers;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import models.EventPlanner;
import views.StartingView;


public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException {

        EventPlanner.initializeRepositoryWithData();
        StartingView.staringView();

    }
}