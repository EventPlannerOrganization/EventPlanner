package printers;

import enumerations.Colors;
import models.RegisteredEvent;
import models.ServiceProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static printers.CollectionsPrinter.getMonthName;

public class MenusPrinter {
    private static final Logger logger = Logger.getLogger(MenusPrinter.class.getName());
    public static final String FORMAT = "%n|%-5s| %-30s|";
    public static final String SIGN_OUT = "Sign out !";

    private MenusPrinter() {

    }
    public static void printMenu(List<String> actions) {
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
        menu.add(SIGN_OUT);

        printMenu(menu);
    }
    public static void printAdminMenu()
    {
        List<String> menu = new ArrayList<>();
        menu.add("User Management");
        menu.add("Service Provider Management");
        menu.add("Event Management");
        menu.add("");
        menu.add(SIGN_OUT);

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

        menu.add("Cancel");
        printMenu(menu);
    }
    public static void printServiceProviderManageMenu()
    {
        List<String> menu = new ArrayList<>();
        menu.add("Show all Service Providers");
        menu.add("Search service Providers by username");
        menu.add("Create new Service Provider");
        menu.add("Delete Service Provider");
        menu.add("Reset password for Service Provider");
        menu.add("Show service/s for Service Provider");
        menu.add("Show Booked Dates for Service Provider");

        menu.add("Cancel ");
        printMenu(menu);
    }

    public static void printServiceProviderMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Show your Service/s");
        menu.add("Edit your Service/s");
        menu.add("Show your Event/s");
        menu.add("Edit your Event/s");
        menu.add("Show requests");
        menu.add(SIGN_OUT);
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
    public static void printfindUserMethodsMenu(){
        List<String> menu = new ArrayList<>();
        menu.add("Search for a specific user");
        menu.add("Show all users and select from the list");
        printMenu(menu);
    }

    public static void printListOfStrings(List<String> list){
        printMenu(list);
    }

    public static void printList(List<RegisteredEvent>filterdEvents){
        List<String>  serviceProvdierEvents= makeStringListOfEvents(filterdEvents);
        MenusPrinter.printListofStringWithNumbers(serviceProvdierEvents, "Here is Your Event/s:");
    }
    public  static List<String> makeStringListOfEvents(List <RegisteredEvent>filterdEvents ){
        List<String> serviceProvdierEvents = new ArrayList<>();
        for(int i = 0;i<filterdEvents.size();i++) {
            String st1 = "Service info : \n";
            String events = st1 + filterdEvents.get(i).toString2() + "\n -------------------------------------------";
            serviceProvdierEvents.add(events);
        }
        return serviceProvdierEvents;
    }

    public static void printEventManageMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("View a list of all events");
        menu.add("Show events schedule for a specific year");
        menu.add("Create Event for a specific user");
        menu.add("Search Events");
        menu.add("Delete Event");
        menu.add("Modifying Event");

        menu.add("Cancel ");
        printMenu(menu);
    }


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void printSchedule(int year, List<RegisteredEvent> events) {
        StringBuilder output=new StringBuilder();
        for (int month = 1; month <= 12; month++) {
            output.append(ANSI_YELLOW + "\nMonth: ").append(getMonthName(month)).append(" ").append(year).append(ANSI_RESET);
            output.append("\n------------------------------\n");

            for (RegisteredEvent event : events) {
                LocalDate eventDate = event.getDate();
                if (eventDate.getYear() == year && eventDate.getMonthValue() == month) {
                    output.append(ANSI_CYAN).append(eventDate.getDayOfMonth()).append(": ").append(event.getEventName()).append(ANSI_RESET).append("\n");
                }
            }

        }
        logger.info( "\n"+output);
    }


}
