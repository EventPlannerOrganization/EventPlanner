package views;

import controllers.AdminControl;
import helpers.ChoiceChecker;
import helpers.PasswordChecker;
import models.EventPlanner;
import models.User;
import printers.MenusPrinter;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AdminView {
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private static final User notUser=new User();
    static String message="Sorry, no users were found matching your search criteria.\n" +
            "do need to retry process? Enter 'Y' if yes, enter any button to return to menu";

    private AdminView() {
    }

    public static void adminMenu()
    {
        boolean flage=true;
        while(flage)
        {
            MenusPrinter.printAdminMenu();
            logger.info("What do you want to do ?");
            String choice = scanner.nextLine();
            while (!ChoiceChecker.adminMenuChecker(choice))
                {
                choice = scanner.nextLine();
                logger.info("Enter Valid Choice !");
                }
            switch (choice)
            {
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
                    flage=false;
                    AdminControl.signout();
                    break;
                default:
                    // code block
            }
        }
    }

    private static void userManagement() {
        boolean flage=true;
        while(flage)
        {
            MenusPrinter.printUserManageMenu();
            logger.info("What do you want to do ? ");
            String choice = scanner.nextLine();
            while (!ChoiceChecker.userManageMenuChecker(choice))
            {
                choice = scanner.nextLine();
                logger.info("Enter Valid Choice !");
            }
            switch (choice)
            {
                case "1":
                    AdminView.showUsersView();
                    break;
                case "2":
                    AdminView.serchUserView();
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
                    flage=false;
                    break;
                default:
                    // code block
            }
        }
    }

    private static void resetPassword() {
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
                //you can here send email to notify him that the admin delete him
                logger.info("Enter New Password: ");
                String newPassword = scanner.nextLine();
                while( !PasswordChecker.isStrongPassword(newPassword)){
                    logger.info("please re enter password because its weak  ");
                     newPassword = scanner.nextLine();
                }
                AdminControl.resetPassword(user,newPassword);
                }

            }

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

    private static void serchUserView() {
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

    private static User findModifiedUser() {
    User modifiedUser;
    logger.info("Please select a method to generate the process:");
    MenusPrinter.printfindUserMethodsMenu();
        String choice=scanner.nextLine();
        while (!ChoiceChecker.isOneOrTwo(choice)) {
            logger.info("Enter Valid Choice: ");
            choice=scanner.nextLine();
        }
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
        usersNames.add("None (Do not delete any user)");
        MenusPrinter.printListOfStrings(usersNames);
        int selectedUser = Integer.parseInt(scanner.nextLine());
        while(selectedUser > users.size()+1||selectedUser<=0){
            logger.info("Enter Valid number: ");
            selectedUser = Integer.parseInt(scanner.nextLine());
            }
            if(selectedUser==usersNames.size())modifiedUser=notUser;
            else modifiedUser= users.get(selectedUser-1);

        return modifiedUser;

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
                logger.info("Enter Valid Choice !");
            }
            switch (choice)
            {
                case "1":
                    AdminView.showServiceProvidersView();
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":

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

    private static void showServiceProvidersView() {
        List<String> listOfUsers=AdminControl.getAllServiceProviders();
        MenusPrinter.printListOfStrings(listOfUsers);
        backTouserManagementMenu();
    }


}

