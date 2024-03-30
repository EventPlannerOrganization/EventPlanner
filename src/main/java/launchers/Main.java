package launchers;

import exceptions.UserIsAlreadyExist;
import exceptions.UserNotFoundException;
import exceptions.WeakPasswordException;
import models.EventPlanner;
import views.StartingView;
import javax.mail.MessagingException;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException, IOException, MessagingException, UserNotFoundException {
        EventPlanner.initializeRepositoryWithData();
        StartingView.staringView();
        }
}