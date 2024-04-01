package helpers;

import exceptions.EventAlreadyExist;
import models.EventPlanner;
import models.User;

import java.util.Scanner;
import java.util.logging.Logger;

public class ChoiceChecker {
    private static final Logger logger = Logger.getLogger(ChoiceChecker.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    private ChoiceChecker() {

    }

    public static boolean isValidChoice(String choice, int range) {
        int readedNum = Integer.parseInt(choice);
        for (int i = 1; i <= range; i++) {
            if (i == readedNum) {
                return true;
            }
        }
        return false;
    }


    public static boolean isValidNumberOfServices(String value) {
        return value.equals("2") || value.equals("3") || value.equals("4");
    }


    public static String readingEventName(String eventName) {
        while (true) {
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

