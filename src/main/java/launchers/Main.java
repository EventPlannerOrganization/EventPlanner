package launchers;

<<<<<<< HEAD
import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import Exceptions.WeakPasswordException;
=======
import exceptions.UserIsAlreadyExist;
import exceptions.UserNotFoundException;
import exceptions.WeakPasswordException;
>>>>>>> db351b393b6f9b887d29aba980598760b0183ecd
import models.EventPlanner;
import views.StartingView;
import javax.mail.MessagingException;
import java.io.IOException;
<<<<<<< HEAD



=======
>>>>>>> db351b393b6f9b887d29aba980598760b0183ecd
public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException, IOException, MessagingException, UserNotFoundException {
        EventPlanner.initializeRepositoryWithData();
        StartingView.staringView();
        }
}