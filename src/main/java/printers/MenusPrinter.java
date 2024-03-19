package printers;

import enumerations.Colors;
import enumerations.ServiceType;
import models.RegisteredEvent;
import models.ServiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MenusPrinter {
    private static final Logger logger = Logger.getLogger(MenusPrinter.class.getName());
    public static final String FORMAT = "%n|%-5s| %-30s|";
    private MenusPrinter() {

    }
    private static void printMenu(List<String> actions) {
        StringBuilder outputString = new StringBuilder();
        outputString.append(Colors.YELLOW.getUniCodeValue());
        CollectionsPrinter.appendHorizontalLine(outputString, 39);
        for(int i = 0; i < actions.size(); i++) {
            outputString.append(String.format(FORMAT, i + 1, actions.get(i)));
        }
        CollectionsPrinter.appendHorizontalLine(outputString, 39);
        outputString.append(Colors.RESET.getUniCodeValue());
        outputString.append("\n\n");
        String result = String.valueOf(outputString);
        logger.info(result);
    }



    public static void printSignUpAsMenu() {
        List<String> mainMenu = new ArrayList<>();
        mainMenu.add("User");
        mainMenu.add("Service provider");
        printMenu(mainMenu);
    }

    public static void printServicesMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("DJ");
        menu.add("Photography");
        menu.add("Security");
        menu.add("Cleaning");
        menu.add("Decor And Design");
        menu.add("Catering");
        menu.add("Venue");
        printMenu(menu);
    }

    public static void printServicesMenuWithPcks() {
        List<String> menu = new ArrayList<>();
        menu.add("DJ");
        menu.add("Photography");
        menu.add("Security");
        menu.add("Cleaning");
        menu.add("Decor And Design");
        menu.add("Catering");
        menu.add("Venue");
        menu.add("packages Offers");
        printMenu(menu);
    }


        public static void printServicesList(List<ServiceProvider> services) {
        List<String> menu = new ArrayList<>();

        for(ServiceProvider element:services){
            menu.add(element.toString());
        }
        menu.add("None (I don't need any of these services)");

        printMenu(menu);
    }

    public static void printEventsList(List<RegisteredEvent> events) {
        List<String> menu = new ArrayList<>();

        for(RegisteredEvent element:events){
            menu.add(element.toString());
        }
        menu.add("Back to menue");
        printMenu(menu);
    }

    public static void printUserMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Add Event");
        menu.add("Show My Events");
        menu.add("Edit My Upcoming Events");
        menu.add("Sign out !");

        printMenu(menu);
    }
    public static void printAdminMenu()
    {
        List<String> menu = new ArrayList<>();
        menu.add("User Management");
        menu.add("Service Provider Management");
        menu.add("Event Management");
        menu.add("");
        menu.add("Sign out !");

        printMenu(menu);
    }

    public static void printUserManageMenu()
    {
        List<String> menu = new ArrayList<>();
        menu.add("Show all users");
        menu.add("Search users by username");
        menu.add("Create new user");
        menu.add("Delete user");
        menu.add("Reset password for user");
        menu.add("view registered events for user");

        printMenu(menu);
    }
    public static void printManageServiceProviderMenu()
    {
        List<String> menu = new ArrayList<>();
        menu.add("Create new service provider");
        menu.add("Delete service provider");
        menu.add("Reset passwords for sevice provider");
        menu.add("Show his service/s");

        printMenu(menu);
    }

    public static void manageEventsMenu()
    {
        List<String> menu = new ArrayList<>();
        menu.add("View a list of all events");
        menu.add("Modifying events for a specific user");
        menu.add("");
        menu.add("");

        printMenu(menu);
    }

    public static void printServiceProviderMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Show your Service/s");
        menu.add("Edit your Service/s");
        menu.add("Show your Event/s");
        menu.add("Edit your Event/s");
        menu.add("Sign out !");
        printMenu(menu);
    }


    public static void printEditingChoices() {
        List<String> menu = new ArrayList<>();
        menu.add("Edit Event Name");
        menu.add("Add Services");
        menu.add("Delete Service");
        menu.add("Add new Guests");
        menu.add("Delete Guest");
        menu.add("Cancel");
        printMenu(menu);
    }
    public static void printGuestsList(List<String> menu) {
        menu.add("Back to menue");
        printMenu(menu);
    }
    public static void printListofStringWithNumbers(List<String> list,String type){
        StringBuilder string = new StringBuilder();
        string.append(type);
        for (int i = 1; i<=list.size();i++){
            String temp = "\n "+i + " - ";
            string.append(temp);
            string.append(list.get(i-1));
        }
        String s = string.toString();
        logger.info(s);

    }

    public static void printStartingMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Sign in");
        menu.add("Sign Up");
        menu.add("Reset Your Password");
        menu.add("Exit");

        printMenu(menu);
    }

    public static void printServiceProviderEditMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Edit Service type");
        menu.add("Edit Service description");
        menu.add("Edit Service Price");
        printMenu(menu);
    }

    public static void printListOfUsers(List<String> list){
        printMenu(list);
    }
}
