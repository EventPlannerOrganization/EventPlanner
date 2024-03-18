package helpers;

import Exceptions.EventAlreadyExist;
import Exceptions.GoToMainMenuException;
import controllers.ServiceProviderControl;
import models.EventPlanner;
import models.Service;
import models.ServiceProvider;
import models.User;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ChoiceChecker {
    private static final Logger logger=Logger.getLogger(ChoiceChecker.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private ChoiceChecker() {

    }
    public static boolean isOneOrTwo(String value){
       return value.equals("1")||value.equals("2");
    }



    public static boolean isValidNumberOfServices(String value) {
        return value.equals("2")||value.equals("3")||value.equals("4");
    }
    public static boolean isVaildServiceIndex(String value) {
        return value.equals("1")||value.equals("2")||value.equals("3")||value.equals("4")||value.equals("5")||value.equals("6")||value.equals("7");
    }

    public static boolean userMenuChecker(String value) {
        return value.equals("1")||value.equals("2")||value.equals("3")||value.equals("4");
    }
    public static boolean serviceProviderMenuChecker(String value) {
        return value.equals("1")||value.equals("2")||value.equals("3")||value.equals("4")||value.equals("5");

    }
    public static boolean againChecker(){
        boolean again=true;
        String choice = scanner.nextLine();
        boolean wrongChoice = true;
        while (wrongChoice) {
            if (choice.equals("y")) {
                wrongChoice = false;
            } else if (choice.equals(("n"))) {
                again = false;
                wrongChoice = false;
            } else{
                logger.info("Invalid choice. Please enter either 'y' or 'n'.\n");
                choice = scanner.nextLine();
            }
        }
        return again;
    }


    public static boolean editServiceMenuCheck(String value) {
        return value.equals("1")||value.equals("2")||value.equals("3")||value.equals("4")||value.equals("5")||value.equals("6")||value.equals("7")||value.equals("8")||value.equalsIgnoreCase("b");

    }
    public static boolean checkIfB(String value) {
        return value.equals("b") ||value.equals("B");
    }



    public static String readingEventName(){
        String eventName = scanner.nextLine();
        while (true){
            try {
                ((User) EventPlanner.getCurrentUser()).checkEventExisting(eventName);
                break;
            } catch (EventAlreadyExist e) {
                logger.warning("This event name is already exist\nplease Enter another name: ");
                eventName = scanner.nextLine();
            }
        }
        return eventName;
    }

    public static String checkPackageProviderAddingProccess(List<Service> serviceList, String choice) {
        while (!ChoiceChecker.isVaildServiceIndex(choice) || (ServiceProviderControl.checkIfTheServiceAlreadyAdded(serviceList,choice))) {
            if (!ChoiceChecker.isVaildServiceIndex(choice)) {
                logger.info("Invalid Input , Choose Correct Service Number :\n");
                choice = scanner.nextLine();
            }

            else if (ServiceProviderControl.checkIfTheServiceAlreadyAdded(serviceList, choice)) {
                if (serviceList.size() > 2) {
                    logger.info("You Already Provide This Service ! , choose Another one or Enter B to Stop Adding :\n");
                    choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("b")) {
                        return "b";
                    }
                } else {
                    logger.info("You Already Provide This Service !,Choose Another One Because The Package Cannot Contain One Service  :\n");
                    choice = scanner.nextLine();
                }

            }
        }
        return choice;
    }

    public static String checkPackageProviderServiceChooice(ServiceProvider serviceProvider, String choice) throws GoToMainMenuException {
        boolean again = true;
        int ch;

        while (again) {
            try {
                ch = Integer.parseInt(choice);
                if (ch <= serviceProvider.getServices().size()) {
                    again=false;
                } else {
                    logger.info("Invalid Choice,Choose Again Or Enter B to Go Back");
                    choice = scanner.nextLine();
                }
            } catch (Exception e) {
                logger.info("Invalid Choice,Choose Again Or Enter B to Go Back");
                choice = scanner.nextLine();
                if(choice.equalsIgnoreCase("b")){
                    throw new GoToMainMenuException();
                }

            }
        }
return choice;

    }
}

