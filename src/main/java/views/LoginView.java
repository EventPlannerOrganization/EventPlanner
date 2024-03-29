package views;


import Exceptions.*;
import controllers.Login;
import enumerations.UserType;
import models.EventPlanner;
import models.Person;
import models.ServiceProvider;
import models.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class LoginView {
    private static final Logger logger = Logger.getLogger(LoginView.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    private LoginView() {

    }

    public static void canLoginView() {
        logger.info("Enter Your Username: ");
        String username = scanner.nextLine();
        logger.info("Enter Your password: ");
        String password = scanner.nextLine();

        try {
            if (Login.canLogin(username, password)) {
                logger.info("Login Successfully..");
                whosLogin();
            } else {
                logger.info("invalid password..!");
            }

        } catch (UserNotFoundException exception) {
            logger.info("invalid username..!");
        } catch (EventNotFound | EventAlreadyExist | MessagingException | IOException | EmptyList | UserIsAlreadyExist |
                 WeakPasswordException e) {
            logger.warning(e.getMessage());
        }
    }

    public static void whosLogin() throws UserIsAlreadyExist, WeakPasswordException, UserNotFoundException, EventNotFound, MessagingException, IOException, EventAlreadyExist, EmptyList {
        Person current = EventPlanner.getCurrentUser();
        if (current instanceof ServiceProvider) ServiceProviderView.providerMenu();
        else if (((User) current).getUsertype() == UserType.USER) UserView.userMenu();
        else AdminView.adminMenu();

    }

}
