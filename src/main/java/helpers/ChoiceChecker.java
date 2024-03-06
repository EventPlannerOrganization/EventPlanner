package helpers;

import Exceptions.EventAlreadyExist;
import models.EventPlanner;
import models.User;
import views.EventsView;

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

    public static boolean userMenuChecker(String value) {
        return value.equals("1")||value.equals("2")||value.equals("3")||value.equals("4");
    }
    public static boolean ServiceProviderMenuChecker(String value) {
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
        return value.equals("1")||value.equals("2")||value.equals("3")||value.equals("4")||value.equals("5")||value.equalsIgnoreCase("b");

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
}

