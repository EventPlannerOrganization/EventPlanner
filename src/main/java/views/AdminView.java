package views;

import exceptions.EmptyList;
import exceptions.EventAlreadyExist;
import exceptions.EventNotFoundException;
import controllers.AdminControl;

import exceptions.ServiceNotFoundException;
import helpers.ChoiceChecker;
import helpers.PasswordChecker;
import models.*;
import printers.MenusPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static controllers.AdminControl.*;
import static views.EventsView.editingEventView;
import static views.EventsView.readEventInfo;

public class AdminView {
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    private static final User notUser = new User();
    private static final RegisteredEvent notEvent = new RegisteredEvent();
    private static final ServiceProvider notServiceProvider = new ServiceProvider();
    public static final String WHAT_DO_YOU_WANT_TO_DO = "What do you want to do ? ";
    public static final String INVALID_CHOICE = "invalid choice";

    static String message = "Sorry, no users were found matching your search criteria.\n" +
            "do need to retry process? Enter 'Y' if yes, enter any button to return to menu";
    static String messageEnterValid = "Enter Valid Choice !";

    private AdminView() {
    }

    public static void adminMenu() {
        boolean flage = true;
        while (flage) {
            MenusPrinter.printAdminMenu();
            logger.info("What do you want to do ?");
            String choice = scanner.nextLine();
            while (!ChoiceChecker.isValidChoice(choice,5)) {
                choice = scanner.nextLine();
                logger.info(messageEnterValid);
            }
            switch (choice) {
                case "1" -> AdminView.userManagement();
                case "2" -> AdminView.serviceProviderManagement();
                case "3" -> AdminView.eventManagement();
                case "4" -> {
                    flage = false;
                    EventPlanner.signout();
                }
                default -> logger.warning(INVALID_CHOICE);
            }

            }
        }




    private static void userManagement() {
        boolean flage = true;
        while (flage) {
            MenusPrinter.printUserManageMenu();
            logger.info(WHAT_DO_YOU_WANT_TO_DO);
            String choice = scanner.nextLine();
            while (!ChoiceChecker.isValidChoice(choice,7)) {
                choice = scanner.nextLine();
                logger.info(messageEnterValid);
            }
            switch (choice) {
                case "1" -> AdminView.showUsersView();
                case "2" -> AdminView.searchUserView();
                case "3" -> AdminView.createNewUser();
                case "4" -> AdminView.deleteUser();
                case "5" -> AdminView.resetPassword();
                case "6" -> AdminView.viewEvents();
                case "7" -> flage = false;
                default -> logger.warning(INVALID_CHOICE);


            }
        }
    }

    private static void resetPassword() {
        boolean reTry = true;
        while (reTry) {
            User user = findModifiedUser();
            reTry=modifiedPersonChecker(user);

            }
        }

    private static boolean modifiedPersonChecker(Person person){
        boolean reTry = true;

        if (person == null) {
            logger.info(message);
            String choice = scanner.nextLine();
            if (!(choice.equals("y") || choice.equals("Y"))) reTry = false;
        }
        else if(person==notServiceProvider||person==notUser)reTry = false;
        else {
            reTry = false;
            //you can here send email to notify him that the admin delete him
            try {
                AdminControl.resetPassword(person, readNewPassword());
            } catch (Exception e){
                logger.warning(e.getMessage());
            }    }


        return reTry;
    }


    private static String readNewPassword() {
        logger.info("Enter New Password: ");
        String newPassword = scanner.nextLine();
        while (!PasswordChecker.isStrongPassword(newPassword)) {
            logger.info("please re enter password because its weak  ");
            newPassword = scanner.nextLine();
        }
        return newPassword;
    }


        private static void deleteUser() {
        boolean reTry=true;
        while (reTry){
            User deletedUser=findModifiedUser();
            if(deletedUser==null){
            logger.info(message);
            String choice = scanner.nextLine();
            if(!(choice.equals("y")||choice.equals("Y")))reTry=false;
            }
        else if(deletedUser==notUser){
            reTry=false;
        }
        else {
            reTry=false;
            //you can here send email to notify him that the admin delete him
           try {
               AdminControl.deleteUser( deletedUser);
           } catch (Exception e){
               logger.warning(e.getMessage());
           }
        }

        }
    }

    private static void viewEvents() {
        boolean reTry=true;
        while (reTry){
            User user=findModifiedUser();
            if(user==null){
                logger.info(message);
                String choice = scanner.nextLine();
                if(!(choice.equals("y")||choice.equals("Y")))reTry=false;
            }
            else if(user==notUser){
                reTry=false;
            }
            else {
                reTry=false;
                if(getEventsForUser(user).isEmpty()) logger.info("This user does not have registered events yet!" );
                else {
                    MenusPrinter.printListOfStrings(getEventsForUser(user));
                }
            }
        }
        backTouserManagementMenu();
    }



    private static void createNewUser() {
    SignUpView.signUpAsUserView();
    }

    private static void searchUserView() {
        logger.info("Please enter username to search");
        String username = scanner.nextLine();
        List<User> users =AdminControl.searchUsers(username);
        if(users.isEmpty()){
            logger.info("Sorry, no users were found matching your search criteria.");
            return;
        }
       List<String> searchResult =AdminControl.getUserNameOfUsers(users);
       MenusPrinter.printListOfStrings(searchResult);
        backTouserManagementMenu();
    }


    private static void showUsersView()
    {
        List<String> listOfUsers=AdminControl.getAllUsers();
        if(!listOfUsers.isEmpty()){
        MenusPrinter.printListOfStrings(listOfUsers);}
        else logger.info("System does not contain users");
        backTouserManagementMenu();
    }

    private static String selectMethod() {
        logger.info("Please select a method to generate the process:");
        MenusPrinter.printfindUserMethodsMenu();
        String choice=scanner.nextLine();
        while (!ChoiceChecker.isValidChoice(choice,2)) {
            logger.info("Enter Valid Choice: ");
            choice=scanner.nextLine();
        }
        return choice;
    }

    private static User findModifiedUser()
    {
    User modifiedUser;
    String choice=selectMethod();
    List<User> users;
    if(choice.equals("1")) {
        logger.info("Please enter username to search ");
        String username = scanner.nextLine();
        users = AdminControl.searchUsers(username);
    }
    else if (choice.equals("2"))
        users=EventPlanner.getUsers();
    else
        users=null;
    if (users == null){
        return null;
    }
    List<String>usersNames=AdminControl.getUserNameOfUsers(users);
    int selectedUser=selectFromList(usersNames);
    if(selectedUser==usersNames.size())modifiedUser=notUser;
    else modifiedUser= users.get(selectedUser-1);

    return modifiedUser;

    }

    private static int selectFromList(List<String> usersNames) {
        usersNames.add("None (Do not delete any one)");

        MenusPrinter.printnewMenu("Users",usersNames,"\u001B[34m");

        int selectedUser = Integer.parseInt(scanner.nextLine());
        while(selectedUser > usersNames.size()||selectedUser<=0){
            logger.info("Enter Valid number: ");
            selectedUser = Integer.parseInt(scanner.nextLine());
        }
        return selectedUser;
    }

    static void backTouserManagementMenu()  {
        logger.info("To Return Back Enter B");
        String choice = scanner.nextLine();
        while (!(choice.equals("B") || choice.equals("b"))) {
            logger.info("To Return Back Enter B");
            choice = scanner.nextLine();
        }
    }



    private static void serviceProviderManagement() {
        boolean flage=true;
        while(flage)
        {
            MenusPrinter.printServiceProviderManageMenu();
            logger.info(WHAT_DO_YOU_WANT_TO_DO);
            String choice = scanner.nextLine();
            while (!ChoiceChecker.isValidChoice(choice,8))
            {
                choice = scanner.nextLine();
                logger.info(messageEnterValid);
            }
            switch (choice) {
                case "1" -> AdminView.showServiceProvidersView();
                case "2" -> AdminView.searchServiceProviderView();
                case "3" -> AdminView.createNewServiceProvider();
                case "4" -> AdminView.deleteServiceProvider();
                case "5" -> AdminView.resetServiceProviderPassword();
                case "6" -> AdminView.viewServices();
                case "7" -> AdminView.viewBookedDates();
                case "8" -> flage = false;
                default -> logger.warning(INVALID_CHOICE);

            }
        }
    }

    private static void viewBookedDates() {
        boolean reTry=true;
        ServiceProvider serviceProvider=findModifiedServiceProvider();
        while (reTry){
            if(serviceProvider==null){
                logger.info(message);
                String choice = scanner.nextLine();
                if(!(choice.equals("y")||choice.equals("Y")))reTry=false;
            }
            else if(serviceProvider==notServiceProvider){
                reTry=false;
            }
            else {
                reTry=false;
                if(AdminControl.getBookedDatasForServiceProvider(serviceProvider).isEmpty()) logger.info("This service provider does not Booked yet!" );
                else {
                    MenusPrinter.printListOfStrings(AdminControl.getBookedDatasForServiceProvider(serviceProvider));
                }
            }

        }
        backTouserManagementMenu();

    }

    private static void viewServices() {
        boolean reTry=true;
        ServiceProvider serviceProvider=findModifiedServiceProvider();
        while (reTry){
            if(serviceProvider==null){
                logger.info(message);
                String choice = scanner.nextLine();
                if(!(choice.equals("y")||choice.equals("Y")))reTry=false;
            }
            else if(serviceProvider==notServiceProvider){
                reTry=false;
            }
            else {
                reTry=false;
                if(AdminControl.getServicesForServiceProvider(serviceProvider).isEmpty()) logger.info("This service provider does not provider any service yet!" );
                else {
                    MenusPrinter.printListOfStrings(AdminControl.getServicesForServiceProvider(serviceProvider));
                }
            }

        }
        backTouserManagementMenu();

    }

    private static void resetServiceProviderPassword() {
        boolean reTry = true;
        while (reTry) {
            ServiceProvider serviceProvider = findModifiedServiceProvider();
            reTry=modifiedPersonChecker(serviceProvider);
            if (serviceProvider == null) {
            logger.info(message);
            String choice = scanner.nextLine();
            if (!(choice.equals("y") || choice.equals("Y"))) reTry = false;
        } else if (serviceProvider == notServiceProvider) {
            reTry = false;
        } else {
            reTry = false;
            //you can here send email to notify him that the admin delete him
            try {
                AdminControl.resetPassword(serviceProvider, readNewPassword());
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }

        }}
    }

    private static void deleteServiceProvider() {
        boolean reTry=true;
        ServiceProvider deletedUser=findModifiedServiceProvider();
        while (reTry){
            if(deletedUser==null){
                logger.info(message);
                String choice = scanner.nextLine();
                if(!(choice.equals("y")||choice.equals("Y")))reTry=false;
            }
            else if(deletedUser==notServiceProvider){
                reTry=false;
            }
            else {
                reTry=false;
                //you can here send email to notify him that the admin delete him
                try {
                    AdminControl.deleteServiceProvider( deletedUser);
                }
                catch (EmptyList | Exception e){logger.warning(e.getMessage());}
            }

        }
    }

    private static ServiceProvider findModifiedServiceProvider() {
        ServiceProvider modifiedServiceProvider;
        String choice=selectMethod();
        List<ServiceProvider> serviceProviders;
        if(choice.equals("1")) {
            logger.info("Please enter username to search ");
            String username = scanner.nextLine();
            serviceProviders = AdminControl.searchServiceProviders(username);
        }
        else if (choice.equals("2"))
            serviceProviders=EventPlanner.getServiceProviders();
        else
            serviceProviders=null;
        if (serviceProviders == null){
            return null;
        }
        List<String>usersNames=AdminControl.getUserNameOfServiceProviders(serviceProviders);
        int selectedUser=selectFromList(usersNames);
        if(selectedUser==usersNames.size())modifiedServiceProvider=notServiceProvider;
        else modifiedServiceProvider= serviceProviders.get(selectedUser-1);

        return modifiedServiceProvider;
    }

    private static void createNewServiceProvider() {
        SignUpView.signUpAsServiceProviderView();
    }

    private static void searchServiceProviderView() {
        logger.info("Please enter username to search");
        String username = scanner.nextLine();
        List<String> searchResult =AdminControl.getUserNameOfServiceProviders(AdminControl.searchServiceProviders(username));
        if(searchResult.isEmpty()){
            logger.info("Sorry, no users were found matching your search criteria.");
            return;
        }
        MenusPrinter.printListOfStrings(searchResult);
        backTouserManagementMenu();
    }

    private static void showServiceProvidersView() {
        List<String> listOfUsers=AdminControl.getAllServiceProviders();
        MenusPrinter.printListOfStrings(listOfUsers);
        backTouserManagementMenu();
    }


    private static void eventManagement() {
        boolean flage=true;
        while(flage)
        {
            MenusPrinter.printEventManageMenu();
            logger.info(WHAT_DO_YOU_WANT_TO_DO);
            String choice = scanner.nextLine();
            while (!ChoiceChecker.isValidChoice(choice,7))
            {
                choice = scanner.nextLine();
                logger.info(messageEnterValid);
            }
            switch (choice) {
                case "1" -> AdminView.viewAllEvents();
                case "2" -> AdminView.showSchedule();
                case "3" -> AdminView.createEvent();
                case "4" -> AdminView.searchEvent();
                case "5" -> AdminView.deleteEvent();
                case "6" -> AdminView.editEvent();
                case "7" -> flage = false;
                default ->logger.warning(INVALID_CHOICE);
            }
        }
    }

    private static void editEvent() {
        RegisteredEvent event=findModifiedEvent();
        if(event!=null) {
            editingEventView(event);
        }
        else
            logger.info("There isn't Events With this Name");
    }

    private static void deleteEvent() {
        RegisteredEvent event=findModifiedEvent();
        try {
            AdminControl.deleteEvent(event);
        }
        catch (EventNotFoundException | ServiceNotFoundException e){
            logger.info("Sorry, This event does not include to any user ! ");
        }

    }

    private static void searchEvent() {
            logger.info("Please enter event name to search");
            String eventName = scanner.nextLine();
            List<RegisteredEvent> events=AdminControl.searchEvents(eventName);
            List<String> searchResult=new ArrayList<>();
            if(events!=null)
                searchResult=AdminControl.getEventNameOfUsers(events);
            if(searchResult.isEmpty()){
                logger.info("Sorry, no events were found matching your search criteria.\n");
                backTouserManagementMenu();
                return;
            }
            MenusPrinter.printListOfStrings(searchResult);
            backTouserManagementMenu();
        }


    private static void showSchedule() {
        logger.info("Please enter the year for which you want to view the events: ");
        int year = scanner.nextInt();
        List<RegisteredEvent> events= getAllEvents();
        MenusPrinter.printSchedule(year, events);
        backTouserManagementMenu();
    }

    private static void createEvent() {
        boolean reTry=true;
        while (reTry){
            User user=findModifiedUser();
            if(user==null){
                logger.info(message);
                String choice = scanner.nextLine();
                if(!(choice.equals("y")||choice.equals("Y")))reTry=false;
            }
            else if(user==notUser){
                reTry=false;
            }
            else {
                reTry=false;
                try {
                    RegisteredEvent newEvent=readEventInfo();
                    AdminControl.addEventForUser(user,newEvent);
                }
                catch (EventAlreadyExist e){
                    logger.info("sorry, There is an event with the same name ");
                }
            }
        }
        backTouserManagementMenu();

    }

    private static void viewAllEvents() {
        List<String> allEventsNames=getAllEventsNames();
        if(!allEventsNames.isEmpty()){
            MenusPrinter.printListOfStrings(allEventsNames);}
        else
            logger.info("Sorry, system does not have enents.");

        backTouserManagementMenu();

    }



    private static RegisteredEvent findModifiedEvent()
    {
        RegisteredEvent modifiedEvent;
        String choice=selectEventMethod();
        List<RegisteredEvent> events;
        switch (choice) {
            case "1" -> {
                logger.info("Please enter event name to search ");
                String eventName = scanner.nextLine();
                events = AdminControl.searchEvents(eventName);
            }
            case "2" -> events = AdminView.eventsForUser();
            case "3" -> events = getAllEvents();
            default -> events = null;
        }
        if (events == null){
            return null;
        }
        List<String>usersNames=AdminControl.getEventNameOfUsers(events);
        int selectedUser=selectFromList(usersNames);
        if(selectedUser==usersNames.size())modifiedEvent=notEvent;
        else modifiedEvent= events.get(selectedUser-1);

        return modifiedEvent;

    }

    private static List<RegisteredEvent> eventsForUser() {
        boolean reTry=true;
        User user;
        while (reTry){
             user=findModifiedUser();
            if(user==null){
                logger.info(message);
                String choice = scanner.nextLine();
                if(!(choice.equals("y")||choice.equals("Y")))reTry=false;
            }
            else if(user==notUser){
                reTry=false;
            }
            else {
                return user.getRegisteredEvents();

            }
        }
        return Collections.emptyList();
    }

    private static String selectEventMethod() {
        logger.info("Please select a method to generate the process:");
        MenusPrinter.printfindEventMethodsMenu();
        String choice=scanner.nextLine();
        while (!ChoiceChecker.isValidChoice(choice,2)) {
            logger.info("Enter Valid Choice: ");
            choice=scanner.nextLine();
        }
        return choice;
    }
}


