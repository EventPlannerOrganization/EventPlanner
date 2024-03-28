package views;

import Exceptions.EmptyList;
import Exceptions.EventAlreadyExist;
import Exceptions.ServiceNotFoundException;
import controllers.AdminControl;

import helpers.ChoiceChecker;
import helpers.PasswordChecker;
import models.EventPlanner;
import models.RegisteredEvent;
import models.ServiceProvider;
import models.User;
import printers.MenusPrinter;

import java.util.ArrayList;
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
            while (!ChoiceChecker.adminMenuChecker(choice)) {
                choice = scanner.nextLine();
                logger.info(messageEnterValid);
            }
            switch (choice) {
                case "1":
                    AdminView.userManagement();
                    break;
                case "2":
                    AdminView.serviceProviderManagement();
                    break;
                case "3":
                    AdminView.eventManagement();
                    break;
                case "4":
                    break;
                case "5":
                    flage = false;
                    AdminControl.signout();
                    break;
                default:
                    // code block
            }
        }
    }



    private static void userManagement() {
        boolean flage = true;
        while (flage) {
            MenusPrinter.printUserManageMenu();
            logger.info("What do you want to do ? ");
            String choice = scanner.nextLine();
            while (!ChoiceChecker.userManageMenuChecker(choice)) {
                choice = scanner.nextLine();
                logger.info(messageEnterValid);
            }
            switch (choice) {
                case "1":
                    AdminView.showUsersView();
                    break;
                case "2":
                    AdminView.searchUserView();
                    break;
                case "3":
                    AdminView.createNewUser();
                    break;
                case "4":
                    AdminView.deleteUser();
                    break;
                case "5":
                    AdminView.resetPassword();
                    break;
                case "6":
                    AdminView.viewEvents();
                    break;
                case "7":
                    flage = false;
                    break;
                default:
                    // code block
            }
        }
    }

    private static void resetPassword() {
        boolean reTry = true;
        while (reTry) {
            User user = findModifiedUser();
            if (user == null) {
                logger.info(message);
                String choice = scanner.nextLine();
                if (!(choice.equals("y") || choice.equals("Y"))) reTry = false;
            } else if (user == notUser) {
                reTry = false;
            } else {
                reTry = false;
                //you can here send email to notify him that the admin delete him
                AdminControl.resetPassword(user, readNewPassword());
            }
        }
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
            AdminControl.deleteUser( deletedUser);
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
        while (!ChoiceChecker.isOneOrTwo(choice)) {
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
        MenusPrinter.printListOfStrings(usersNames);
        int selectedUser = Integer.parseInt(scanner.nextLine());
        while(selectedUser > usersNames.size()||selectedUser<=0){
            logger.info("Enter Valid number: ");
            selectedUser = Integer.parseInt(scanner.nextLine());
        }
        return selectedUser;
    }

    private static void backTouserManagementMenu()  {
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
            logger.info("What do you want to do ? ");
            String choice = scanner.nextLine();
            while (!ChoiceChecker.serviceProviderManageMenuChecker(choice))
            {
                choice = scanner.nextLine();
                logger.info(messageEnterValid);
            }
            switch (choice)
            {
                case "1":
                    AdminView.showServiceProvidersView();
                    break;
                case "2":
                    AdminView.searchServiceProviderView();
                    break;
                case "3":
                    AdminView.createNewServiceProvider();
                    break;
                case "4":
                    AdminView.deleteServiceProvider();
                    break;
                case "5":
                    AdminView.resetServiceProviderPassword();
                    break;
                case "6":
                    AdminView.viewServices();
                    break;
                case "7":
                    AdminView.viewBookedDates();
                    break;
                case "8":
                    flage=false;
                    break;
                default:
                    // code block
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
        ServiceProvider serviceProvider = findModifiedServiceProvider();
        while (reTry) {
            if (serviceProvider == null) {
                logger.info(message);
                String choice = scanner.nextLine();
                if (!(choice.equals("y") || choice.equals("Y"))) reTry = false;
            } else if (serviceProvider == notServiceProvider) {
                reTry = false;
            } else {
                reTry = false;
                //you can here send email to notify him that the admin delete him
                AdminControl.resetPassword(serviceProvider, readNewPassword());
            }
        }
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
                catch (EmptyList | ServiceNotFoundException e){e.printStackTrace();}
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
            logger.info("What do you want to do ? ");
            String choice = scanner.nextLine();
            while (!ChoiceChecker.userManageMenuChecker(choice))
            {
                choice = scanner.nextLine();
                logger.info(messageEnterValid);
            }
            switch (choice)
            {
                case "1":
                    AdminView.viewAllEvents();
                    break;
                case "2":
                    AdminView.showSchedule();
                    break;
                case "3":
                    AdminView.createEvent();
                    break;
                case "4":
                    AdminView.searchEvent();
                    break;
                case "5":
                    AdminView.deleteEvent();
                    break;
                case "6":
                    AdminView.editEvent();
                    break;
                case "7":
                    flage=false;
                    break;
                case "8":
                    flage=false;
                    break;
                default:
                    // code block
            }
        }
    }

    private static void editEvent() {
        RegisteredEvent event=findModifiedEvent();
        editingEventView(event);
    }

    private static void deleteEvent() {
        RegisteredEvent event=findModifiedEvent();
        try {
            AdminControl.deleteEvent(event);
        }
        catch (Exception e){

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
        if(choice.equals("1")) {
            logger.info("Please enter event name to search ");
            String eventName = scanner.nextLine();
            events = AdminControl.searchEvents(eventName);
        }
        else if (choice.equals("2")){
            events= AdminView.eventsForUser();
        }

        else if (choice.equals("3"))
            events= getAllEvents();
        else
            events=null;
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
        return null;
    }

    private static String selectEventMethod() {
        logger.info("Please select a method to generate the process:");
        MenusPrinter.printfindEventMethodsMenu();
        String choice=scanner.nextLine();
        while (!ChoiceChecker.isOneOrTwo(choice)) {
            logger.info("Enter Valid Choice: ");
            choice=scanner.nextLine();
        }
        return choice;
    }
}


