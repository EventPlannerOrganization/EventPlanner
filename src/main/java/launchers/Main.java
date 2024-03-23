package launchers;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import Exceptions.WeakPasswordException;
import controllers.AdminControl;
import enumerations.ServiceType;
import models.EventPlanner;
import models.RegisteredEvent;
import printers.MenusPrinter;
import views.StartingView;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException, IOException, MessagingException, UserNotFoundException {
        EventPlanner.initializeRepositoryWithData();
        StartingView.staringView();

        }
}