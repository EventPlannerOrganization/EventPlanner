package launchers;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import models.EventPlanner;
import views.StartingView;
import javax.mail.MessagingException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException, IOException, MessagingException {
        EventPlanner.initializeRepositoryWithData();
        StartingView.staringView();

        }
}