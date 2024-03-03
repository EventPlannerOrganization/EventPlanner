package views;

import controllers.ServiceProviderControl;
import helpers.ChoiceChecker;
import models.EventPlanner;
import models.User;
import printers.MenusPrinter;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
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
                LoginView.canLoginView();
                break;
            default:
                // code block
        }
    }

    private static void showEvents() {

        ServiceProviderControl.showServiceProviderEvents();
        BacktoServiceProviderMenu();

    }

    private static void showServices() {

        ServiceProviderControl.showServiceProviderServices();
       BacktoServiceProviderMenu();

    }
    private static void BacktoServiceProviderMenu(){
        logger.info("To Return Back Enter B");
        String choice = scanner.nextLine();
        while (!(choice.equals("B") || choice.equals("b"))) {
            logger.info("To Return Back Enter B");
            choice = scanner.nextLine();
        }
        ServiceProviderView.providerMenu();
    }

}
