package helpers;

import views.EventsView;

import java.util.Scanner;
import java.util.logging.Logger;

public class ChoiceChecker {
    private static final Logger logger=Logger.getLogger(EventsView.class.getName());
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
}
