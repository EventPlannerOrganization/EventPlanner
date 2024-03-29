package views;


import Exceptions.*;
import controllers.EventsControl;
import controllers.UserControl;
import enumerations.ServiceType;
import helpers.ChoiceChecker;
import models.*;
import printers.MenusPrinter;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;




public class EventsView {
    private static final Logger logger = Logger.getLogger(EventsView.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    private static double cost;

    private EventsView() {

    }

    public static void registerEventView() throws EventAlreadyExist, EventNotFound, MessagingException, IOException {
        RegisteredEvent newEvent= readEventInfo();
        EventsControl.addEvent(newEvent.getDate(), newEvent.getEventName(),newEvent.getCost(), newEvent.getGuestsEmails());
        User user=(User)EventPlanner.getCurrentUser();
        for (ServiceProvider element:newEvent.getServiceProviders()){
           UserControl.sendRequestToServiceProvider(element,newEvent.getDate(),user.getEventByName(newEvent.getEventName()));
           logger.info("Request sent to Service Provider");
       }
    }

    public static RegisteredEvent readEventInfo(){
        RegisteredEvent newEvent =new RegisteredEvent();
        cost = 0;
        logger.info("To get started, please provide the following information: \n* Enter Event Name");
        String name=ChoiceChecker.readingEventName();
        logger.info("* Enter Date of your event \n - Day (1-31): ");
        int day = scanner.nextInt();
        logger.info(" - Month (1-12): ");
        int month = scanner.nextInt();
        logger.info("- Year : ");
        int year = scanner.nextInt();
        LocalDate date = LocalDate.of(year, month, day);
        logger.info("* Add Services:\n");
        scanner.nextLine();// this to fixing some input problem
        List<ServiceProvider> list=addingProcess(date);
        logger.info("* Add guests :\n");
        List<String> guestsEmails = readeGuestsEmails();

        newEvent.setEventName(name);
        newEvent.setDate(date);
        newEvent.setGuestsEmails(guestsEmails);
        newEvent.setServiceProviders(list);
        return newEvent;
    }




    public static List<ServiceProvider> addingProcess(LocalDate date) {
        boolean again = true;
        List<ServiceProvider> addedProviders = new ArrayList<>();

        while (again) {
            logger.info("Select one:\n");
            MenusPrinter.printServicesMenuWithPcks();
            String serviceNum = scanner.nextLine();
            List<ServiceProvider> filteredProvidersList;
            if (serviceNum.equals("5")) {
                filteredProvidersList = EventPlanner.getPakageProviders();
            } else {
                ServiceType serviceType = switch (serviceNum) {
                    case "1" -> ServiceType.DJ;
                    case "2" -> ServiceType.Photography;
                    case "3" -> ServiceType.Security;
                    case "4" -> ServiceType.Cleaning;
                    default -> null;
                };
                filteredProvidersList = EventPlanner.getServiceProviderByServiceType(serviceType, date);
            }

            if (filteredProvidersList.isEmpty())
                logger.info("No Services Available:\nUnfortunately, there are no services available for the specified service type and time.\n");
            else {
                ServiceProvider newServiceProvider = selectedServiceFromServicesList(filteredProvidersList);

               if (newServiceProvider != null) {
////                    UserControl.sendRequestToServiceProvider(newServiceProvider,date);
////                    //here add the accept & reject
                newServiceProvider.getBookedDates().add(date);
                addedProviders.add(newServiceProvider);
                }

            }
            //this called Text block which begin with """, sonarLint need to useing it insted string
            logger.info("""
                    Do you want to add another service ?
                    - Enter 'y' to add another service.
                    - Enter 'n' to finish and proceed.
                    """);
            again = ChoiceChecker.againChecker();
        }
        return addedProviders;
    }


    public static ServiceProvider selectedServiceFromServicesList(List<ServiceProvider> filteredProvidersList) {
        MenusPrinter.printServicesList(filteredProvidersList);
        int addedNumber = Integer.parseInt(scanner.nextLine());
        if (addedNumber <= filteredProvidersList.size()) {
            cost += filteredProvidersList.get(addedNumber - 1).getServices().get(0).getPrice();
            return filteredProvidersList.get(addedNumber - 1);
        }
        return null;
    }


    public static List<String> readeGuestsEmails() {
        List<String> guestsEmails = new ArrayList<>();
        logger.info("Enter the number of guests. You can adjust this number and modify the list as needed:\n");
        int serviceNum = scanner.nextInt();
        logger.info("For each guest, please enter their email address:");
        for (int i = 0; i < serviceNum; i++) {
            String s = "\n" + (i + 1) + "- ";
            logger.info(s);
            String email = scanner.nextLine();
            guestsEmails.add(email);
        }
        return guestsEmails;

    }

    public static void editUpCommingEvents(){
        logger.info("Select Event to Editing it: ");
        User currentUser = (User) EventPlanner.getCurrentUser();
        List<RegisteredEvent> myUpComingEvents = currentUser.getRegisteredEvents().stream().filter(event -> !event.getDate().isBefore(LocalDate.now())).toList();
        MenusPrinter.printEventsList(myUpComingEvents);
        int addedNumber = Integer.parseInt(scanner.nextLine());
        if (addedNumber <= myUpComingEvents.size()) {
            editingEventView(myUpComingEvents.get(addedNumber - 1));
        }


    }


    static void editingEventView(RegisteredEvent event)  {
        boolean flage=true;
        while(flage){
            MenusPrinter.printEditingChoices();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                   editEventName(event);
                    break;
                case "2":
                    addServices(event);
                    break;
                case "3":
                    deleteService(event);
                    break;
                case "4":
                    List <String> newGuests= EventsView.readeGuestsEmails();
                    EventsControl.addNewGuests(event,newGuests);
                    break;
                case "5":
                    deleteGuest(event);
                    break;
                case "6":
                    flage=false;
                    break;
                default:
                    // code block
            }
        }
    }


    private static void editEventName(RegisteredEvent event)  {
        logger.info("Please, Enter new name for the event: ");
        String newName = scanner.nextLine();
        try {
            EventsControl.editEventName(event, newName);
        }
        catch (EventAlreadyExist e){
            logger.info("Sorry, This name invalid because there is another event with same name ");
        }
        catch (EventNotFoundException e){
            logger.info("Sorry, This event does not included to any user! ");

        }
    }

    private static void deleteService(RegisteredEvent event) {
        logger.info("Select service to delete it: ");
        ServiceProvider deletedService = EventsView.selectedServiceFromServicesList(event.getServiceProviders());
        try{EventsControl.deleteService(event, deletedService);}
        catch (ServiceNotFoundException ignored){}
    }

    private static void deleteGuest(RegisteredEvent event) {
        logger.info("");
        MenusPrinter.printGuestsList(event.getGuestsEmails());
        int addedNumber = Integer.parseInt(scanner.nextLine());
        if (addedNumber <= event.getGuestsEmails().size()) {
            EventsControl.deleteGuest(event.getGuestsEmails().get(addedNumber - 1), event);
        }

    }

    public static void showMyevents() {
        User currentUser = (User) EventPlanner.getCurrentUser();
        List<RegisteredEvent> myEvents = currentUser.getRegisteredEvents();
        MenusPrinter.printEventsList(myEvents);
    }

    private static void addServices(RegisteredEvent event) {
        List<ServiceProvider> addedServices =addingProcess(event.getDate());
        try{EventsControl.addServices(event,addedServices);}
        catch (AlreadyBookedDateException e){
            logger.info("Can not be added, \n there is a service already Scheduled in this event date");
        }

    }
}


