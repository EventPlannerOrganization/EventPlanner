package views;

import helpers.ChoiceChecker;
import printers.MenusPrinter;

import java.util.Scanner;
import java.util.logging.Logger;

public class AdminView {
    private static final Logger logger = Logger.getLogger(UserView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    public static void userMenu()
    {
        MenusPrinter.printAdminMenu();
        logger.info("What do you want to do ?");
        String choice = scanner.nextLine();
        while (!ChoiceChecker.userMenuChecker(choice)) {
            choice = scanner.nextLine();
            logger.info("Enter Valid Choice !");
        }





    }

