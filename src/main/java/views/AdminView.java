package views;

import controllers.AdminControl;
import controllers.UserControl;
import helpers.ChoiceChecker;
import models.User;
import printers.MenusPrinter;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AdminView {
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);

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
                    break;
                case "5":
                    flage=false;
                    break;
                default:
                    // code block
            }
        }
    }

    private static void createNewUser() {
    SignUpView.signUpAsUserView();
    }

    private static void serchUserView() {
        logger.info("Please enter username to search");
        String username = scanner.nextLine();
       List<String> searchResult =AdminControl.getUserNameOfUsers(AdminControl.searchUsers(username));
       MenusPrinter.printListOfUsers(searchResult);
        backTouserManagementMenu();
    }

    private static void showUsersView()
    {
        List<String> listOfUsers=AdminControl.getAllUsers();
        MenusPrinter.printListOfUsers(listOfUsers);
        backTouserManagementMenu();
    }




    private static void backTouserManagementMenu()  {
        logger.info("To Return Back Enter B");
        String choice = scanner.nextLine();
        while (!(choice.equals("B") || choice.equals("b"))) {
            logger.info("To Return Back Enter B");
            choice = scanner.nextLine();
        }
    }

}

