package views;
import controllers.UserControl;
import helpers.ChoiceChecker;
import printers.MenusPrinter;

import java.util.Scanner;
import java.util.logging.Logger;

public class UserView {
    private static final Logger logger = Logger.getLogger(UserView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);


    private UserView() {


    }

    public static void userMenu() {
        MenusPrinter.printUserMenu();
        logger.info("What do you want to do ?");
        String choice = scanner.nextLine();
        while (!ChoiceChecker.userMenuChecker(choice)) {
            choice = scanner.nextLine();
            logger.info("Enter Valid Choice !");
        }

        switch (choice) {
            case "1":
                EventsView.registerEventView();
                UserView.userMenu();
                break;
            case "2":
                EventsView.showMyevents();
                UserView.userMenu();
                break;
            case "3":
                EventsView.editUpCommingEvents();
                break;
            case "4":
                UserControl.signout();
                LoginView.canLoginView();
                break;
            default:
                // code block
        }
    }
}
