package views;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
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


    public static void providerMenu() throws UserIsAlreadyExist, WeakPasswordException {
        MenusPrinter.printServiceProviderMenu();
        logger.info("What do you want to do ?");
        String choice = scanner.nextLine();
        while (!ChoiceChecker.userMenuChecker(choice)) {
            choice = scanner.nextLine();
            logger.info("Enter Valid Choice !");
        }
        switch (choice) {
            case "1":
           ServiceProviderView.showServices();
                break;
            case "2":
            ServiceProviderView.showEvents();
                break;
            case "3":
                break;
            case "4":
                ServiceProviderControl.signout();
                StartingView.staringView();
                break;
            default:
                // code block
        }
    }

    private static void showEvents() throws UserIsAlreadyExist, WeakPasswordException {

        ServiceProviderControl.showServiceProviderEvents();
        backtoServiceProviderMenu();

    }

    private static void showServices() throws UserIsAlreadyExist, WeakPasswordException {

        ServiceProviderControl.showServiceProviderServices();
       backtoServiceProviderMenu();

    }
    private static void backtoServiceProviderMenu() throws UserIsAlreadyExist, WeakPasswordException {
        logger.info("To Return Back Enter B");
        String choice = scanner.nextLine();
        while (!(choice.equals("B") || choice.equals("b"))) {
            logger.info("To Return Back Enter B");
            choice = scanner.nextLine();
        }
        ServiceProviderView.providerMenu();
    }

}
