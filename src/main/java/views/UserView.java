package views;

import Email.EmailService;
import Exceptions.*;
import helpers.ChoiceChecker;
import models.EventPlanner;
import models.RegisteredEvent;
import models.User;
import printers.MenusPrinter;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserView {
    private static final Logger logger = Logger.getLogger(UserView.class.getName());
    private static final Scanner scanner = new Scanner(System.in);


    private UserView() {


    }


    public static void userMenu() throws EventNotFound, EventAlreadyExist, MessagingException, IOException {
        boolean flage = true;
        while (flage) {

            MenusPrinter.printUserMenu();
            logger.info("What do you want to do ?");
            String choice = scanner.nextLine();
            while (!ChoiceChecker.userMenuChecker(choice)) {
                choice = scanner.nextLine();
                logger.info("Enter Valid Choice !");
            }

            switch (choice) {
                case "1" -> {
                    EventsView.registerEventView();
                    UserView.userMenu();
                }
                case "2" -> EventsView.showMyevents();
                case "3" -> EventsView.editUpCommingEvents();
                case "4" -> guestsEmail();
                case "5" -> {
                    flage = false;
                    EventPlanner.signout();
                }
                default -> logger.warning("unexpected value");


            }
        }
    }

    public static void showEventsName() {
        User user = (User) EventPlanner.getCurrentUser();
        List<String> names = new ArrayList<>();
        for (RegisteredEvent registeredEvent : user.getRegisteredEvents()) {
            names.add(registeredEvent.getEventName());
        }
        MenusPrinter.printMenu(names);
    }

    public static void guestsEmail() throws MessagingException, IOException {
        showEventsName();
        String choice = scanner.nextLine();
        while (!ChoiceChecker.userMenuChecker(choice)) {
            choice = scanner.nextLine();
            logger.info("Enter Valid Choice !");
        }
        User user = (User) EventPlanner.getCurrentUser();
        try {
            if (user.getRegisteredEvents().get(Integer.parseInt(choice) - 1).getGuestsEmails().isEmpty()) {
                throw new EmptyList();
            }
        } catch (EmptyList emptyList) {
            logger.warning("you didnt add any guest to this event yet");
            return;
        }
        EmailService emailService = new EmailService();
        emailService.sendEventInvitations(user.getRegisteredEvents().get(Integer.parseInt(choice) - 1));
    }
}
