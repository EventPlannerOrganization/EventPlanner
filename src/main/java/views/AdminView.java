package views;

import controllers.AdminControl;

import helpers.ChoiceChecker;
import helpers.PasswordChecker;
import models.EventPlanner;
import models.ServiceProvider;
import models.User;
import printers.MenusPrinter;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AdminView {
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    private static final User notUser = new User();
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
        User user = findModifiedUser();
        while (reTry) {
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
        User deletedUser=findModifiedUser();
        while (reTry){
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
        User user=findModifiedUser();
        while (reTry){
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
                if(AdminControl.getEventsForUser(user).isEmpty()) logger.info("This user does not have registered events yet!" );
                else {
                    MenusPrinter.printListOfStrings(AdminControl.getEventsForUser(user));
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
       List<String> searchResult =AdminControl.getUserNameOfUsers(AdminControl.searchUsers(username));
        if(searchResult.isEmpty()){
            logger.info("Sorry, no users were found matching your search criteria.");
            return;
        }
       MenusPrinter.printListOfStrings(searchResult);
        backTouserManagementMenu();
    }


    private static void showUsersView()
    {
        List<String> listOfUsers=AdminControl.getAllUsers();
        MenusPrinter.printListOfStrings(listOfUsers);
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

                    break;
                case "7":

                    break;
                case "8":
                    flage=false;
                    break;
                default:
                    // code block
            }
        }
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
                AdminControl.deleteUser( deletedUser);
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


}

