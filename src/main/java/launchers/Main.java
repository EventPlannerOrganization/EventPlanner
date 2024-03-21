package launchers;

import Email.EmailService;
import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import models.EventPlanner;
import views.StartingView;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException, IOException, MessagingException {
        EmailService emailForDeletedUserFromAdmin=new EmailService();
        emailForDeletedUserFromAdmin.sendAdminChangePasswordEmail("3sfr3sfr@gmail.com","mohammad shadid","5464$$sfgwfdfs","admin-change-password");

        //EventPlanner.initializeRepositoryWithData();
        //StartingView.staringView();


        }
}