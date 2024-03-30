package views;

import Exceptions.UserNotFoundException;
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
    public static void staringView() throws  MessagingException, IOException, UserNotFoundException {
        boolean flag = true;
        while (flag) {
            MenusPrinter.printStartingMenu();
            String choice = scanner.nextLine();
            while (!ChoiceChecker.isValidChoice(choice,5)) {
                logger.info("Enter Valid Choice !");
                choice = scanner.nextLine();
            }

            switch (choice) {
                case "1" -> LoginView.canLoginView();
                case "2" -> SignUpView.signUpView();
                case "3" -> ResetPasswordView.resetPasswordView();
                case "4" -> {
                    logger.info("Bye Bye !");
                    flag = false;
                }
                default ->logger.warning("Unexpected value: ");
            }
        }
    }
    }

