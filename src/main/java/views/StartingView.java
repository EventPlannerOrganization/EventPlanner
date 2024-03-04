package views;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import helpers.ChoiceChecker;
import printers.MenusPrinter;

import java.util.Scanner;
import java.util.logging.Logger;

public class StartingView {
    private static final Logger logger=Logger.getLogger(StartingView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private StartingView(){}
    public static void staringView() throws UserIsAlreadyExist, WeakPasswordException {
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
                //reset password
                break;
            case "4":
            logger.info("Bye Bye !");
               return;
            default:
                // code block
        }
    }
    }

