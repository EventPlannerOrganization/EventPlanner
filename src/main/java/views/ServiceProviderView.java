package views;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import controllers.ServiceProviderControl;
import enumerations.ServiceType;
import helpers.ChoiceChecker;
import models.EventPlanner;
import models.ServiceProvider;
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
        while (!ChoiceChecker.ServiceProviderMenuChecker(choice)) {
            choice = scanner.nextLine();
            logger.info("Enter Valid Choice !");
        }
        switch (choice) {
            case "1":
           ServiceProviderView.showServices();
                break;
            case "2":
                ServiceProviderView.editServices((ServiceProvider)EventPlanner.getCurrentUser() );
                break;
            case "3":
                ServiceProviderView.showEvents();
                break;
            case "4":
                ServiceProviderView.showUpComingEvents();

                break;
            case"5":
                ServiceProviderControl.signout();
                StartingView.staringView();
                break;
            default:
                // code block
        }
    }

    private static void editServices(ServiceProvider serviceProvider) throws UserIsAlreadyExist, WeakPasswordException {
        if(serviceProvider.isPackageProvider()){
         //   MenusPrinter.printPackageProviderEditMenu();



        }
        else {
            MenusPrinter.printServiceProviderEditMenu();
            logger.info(" Please Choose Number from Menu or Press B To Back To Main Menu");
            String choice = scanner.nextLine();
            if(ChoiceChecker.checkIfB(choice)){
                ServiceProviderView.providerMenu();
            }
            while(!(ChoiceChecker.editServiceMenuCheck(choice))){
                logger.info("Invalid Input , Please Choose Number from Menu or Press B To Back To Main Menu");
              choice= scanner.nextLine();
                if (ChoiceChecker.checkIfB(choice)) ServiceProviderView.providerMenu();

            }
            switch (choice) {
                case "1":
             ServiceProviderView.editServicesType(serviceProvider);

                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
            }
            }


        }

    private static void editServicesType(ServiceProvider serviceProvider) throws UserIsAlreadyExist, WeakPasswordException {
        MenusPrinter.printServicesMenuWithPcks();
        StringBuilder stringBuilder = new StringBuilder("What is The Service You Want To Provid ? \n");
        stringBuilder.append("If You Want To Go Back Press B");
        logger.info(stringBuilder.toString());
        String choice = scanner.nextLine();
        if (ChoiceChecker.checkIfB(choice)) {
            backtoServiceProviderMenu();
        }
        while (ChoiceChecker.editServiceMenuCheck(choice)) {
            logger.info("Invalid Input , Please Choose Number from Menu or Press B To Back To Main Menu");
            choice = scanner.nextLine();
            if (ChoiceChecker.checkIfB(choice)) ServiceProviderView.providerMenu();

        }
        switch (choice) {
            case "1":
                if (serviceProvider.getServices().get(0).equals(ServiceType.DJ)){
                    logger.info("This is your current service !");
                    editServicesType(serviceProvider);
                }
                //add service type and description and price
                // and ithink its a bad practice but i dont have another solution


        }
    }


    private static void showEvents() throws UserIsAlreadyExist, WeakPasswordException {

        ServiceProviderControl.showServiceProviderEvents();
        backtoServiceProviderMenu();

    }

    private static void showServices() throws UserIsAlreadyExist, WeakPasswordException {

        ServiceProviderControl.showServiceProviderServices((ServiceProvider) EventPlanner.getCurrentUser());
       backtoServiceProviderMenu();

    }
    private static void showUpComingEvents() throws UserIsAlreadyExist, WeakPasswordException {
        ServiceProviderControl.showServiceProviderUpcomingEvents();
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
