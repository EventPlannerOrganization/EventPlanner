package views;

import Email.EmailService;
import exceptions.UserNotFoundException;
import controllers.ResetPasswordController;
import helpers.PasswordChecker;
import models.EventPlanner;
import models.Person;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ResetPasswordView {
    private static final Logger logger=Logger.getLogger(ResetPasswordView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    public static void resetPasswordView() throws IOException, MessagingException, UserNotFoundException {
        logger.info("Please Enter Your Email : \n");
        String emailAddress= scanner.nextLine();
       while (!EventPlanner.checkEmailIfExist(emailAddress))
        {
            logger.info("Email Doesn't Exist , Enter Valid Email Or Press B to Back: \n");
            emailAddress= scanner.nextLine();
            if(emailAddress.equalsIgnoreCase("b")) return;
        }
        Person person = EventPlanner.getUserByEmail(emailAddress);

        logger.info("Reset Password Code Sent To Your Email Please Check And Enter The Code : \n");
        EmailService emailService = new EmailService();
          String actualCode = emailService.sendResetPasswordCode(emailAddress);
         String enterdCode =  scanner.nextLine();
         while (!enterdCode.equals(actualCode)){
             logger.info("Invalid Code Check Your Email Again Or Press B to go Back");
             enterdCode = scanner.nextLine();
             if(enterdCode.equalsIgnoreCase("b")) return;

         }
         logger.info("Please Enter A New Password : ");
         String newPassword = scanner.nextLine();
         while(!PasswordChecker.isStrongPassword(newPassword)){
           logger.info("Weak Password , Please Enter A Password That Contains Letter ,Symbols And Digits");
             newPassword = scanner.nextLine();
         }
        ResetPasswordController.changePasswordForUser(person,newPassword);


    }
}
