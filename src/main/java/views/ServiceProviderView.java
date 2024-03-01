package views;

import controllers.ServiceProviderControl;
import helpers.ChoiceChecker;
import printers.MenusPrinter;
import java.util.Scanner;
import java.util.logging.Logger;

public class ServiceProviderView {
    private static final Logger logger=Logger.getLogger(ServiceProviderView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private ServiceProviderView() {


    }


    public static void providerMenu() {
        MenusPrinter.printServiceProviderMenu();
        logger.info("What do you want to do ?");
        String choice = scanner.nextLine();
        while (!ChoiceChecker.UserMenuChecker(choice)) {
            choice = scanner.nextLine();
            logger.info("Enter Valid Choice !");
        }
        switch (choice) {
            case "1":

                break;
            case "2":

                break;
            case "3":
                break;
            case "4":
                ServiceProviderControl.signout();
                LoginView.canLoginView();
                break;
            default:
                // code block
        }
    }
}
