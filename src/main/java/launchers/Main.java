package launchers;

import Email.EmailService;
import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import models.EventPlanner;
import models.User;
import views.StartingView;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;


<<<<<<< HEAD
public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException, IOException, MessagingException {
=======
public class Main
{
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException
        {


>>>>>>> 8ea5a63f21d538a7e87500d2c243cbc258e9f480
        EventPlanner.initializeRepositoryWithData();
        StartingView.staringView();


        }
}