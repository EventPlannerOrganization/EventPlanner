package views;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import Exceptions.WeakPasswordException;
import controllers.Login;

import java.util.Scanner;
import java.util.logging.Logger;

public class LoginView {
 private static final Logger logger=Logger.getLogger(LoginView.class.getName());
 private static final Scanner scanner=new Scanner(System.in);
 private LoginView() {

 }
 public static void canLoginView() {
     logger.info("Enter Your Username: ");
     String username = scanner.nextLine();
     logger.info("Enter Your password: ");
     String password = scanner.nextLine();
     try {
         if (Login.canLogin(username, password)) {
             logger.info("Login Successfully..");
             Login.whosLogin();
         } else {
             logger.info("invalid password..!");
         }

     } catch (UserNotFoundException exception) {
         logger.info("invalid username..!");
     } catch (UserIsAlreadyExist e) {
        logger.info("this account is already signed up");
     } catch (WeakPasswordException e) {
         logger.info("weak password");
     }
 }
}
