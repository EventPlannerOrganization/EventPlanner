package views;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import Exceptions.WeakPasswordException;
import helpers.ChoiceChecker;
import printers.MenusPrinter;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class StartingView {
    private static final Logger logger=Logger.getLogger(StartingView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private StartingView(){}
    public static void staringView() throws UserIsAlreadyExist, WeakPasswordException, MessagingException, IOException, UserNotFoundException {
        boolean flag = true;
        while (flag) {
            MenusPrinter.printStartingMenu();
            String choice = scanner.nextLine();
            while (!ChoiceChecker.userMenuChecker(choice)) {
                choice = scanner.nextLine();
                logger.info("Enter Valid Choice !");
            }

            switch (choice) {
                case "1":
                    LoginView.canLoginView();
                    break;
                case "2":
                    SignUpView.signUpView();
                    break;
                case "3":
                    ResetPasswordView.resetPasswordView();
                    break;
                case "4":
                    logger.info("Bye Bye !");
                    flag=false;
                    break;
                default:
                    // code block
            }
        }
    }
    }

