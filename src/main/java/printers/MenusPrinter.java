package printers;

import enumerations.Colors;
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
        printMenu(menu);
    }

    public static void printServicesMenuWithPcks() {
        List<String> menu = new ArrayList<>();
        menu.add("DJ");
        menu.add("Photography");
        menu.add("Security");
        menu.add("Cleaning");
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
    public static void printServiceProviderMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Show your Service/s");
        menu.add("Show your Events");
        menu.add("Edit your Events");
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

}
