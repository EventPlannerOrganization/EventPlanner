package views;

import email.EmailService;
import exceptions.*;
import helpers.ChoiceChecker;
import models.EventPlanner;
import models.RegisteredEvent;
import models.User;
import printers.MenusPrinter;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
            while (!ChoiceChecker.isValidChoice(choice,5)) {
                logger.info("Enter Valid Choice !");
                choice = scanner.nextLine();
            }

            switch (choice) {
                case "1" -> EventsView.registerEventView();
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
        names.add("none, (Don't send Tickets to any Event Guests)");
        MenusPrinter.printnewMenu("Send Tickets",names,"\u001B[34m");


    }

    public static void guestsEmail() throws MessagingException, IOException {
        showEventsName();
        User user = (User) EventPlanner.getCurrentUser();
        logger.info("Choose Event : ");
        int choice=readInt();
        scanner.nextLine();
        if((choice>=1&&choice<=user.getRegisteredEvents().size())){
        try {

            if (user.getRegisteredEvents().get(choice - 1).getGuestsEmails().isEmpty()) {
                throw new EmptyList();
            }
        } catch (EmptyList emptyList) {
            logger.warning("you didnt add any guest to this event yet");
            return;
        }
        EmailService emailService = new EmailService();
        emailService.sendEventInvitations(user.getRegisteredEvents().get(choice - 1));
    }


}
    public static int readInt(){
        int integer;
        while(true){
            try{
                integer=scanner.nextInt();
                break;
            }
            catch (InputMismatchException e){
                logger.info("Invalid input, please enter again: \n");
                scanner.nextLine();
            }
        }
        return integer;

    }


}
