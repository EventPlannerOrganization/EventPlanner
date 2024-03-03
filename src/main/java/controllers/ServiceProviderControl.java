package controllers;

import Exceptions.EmptyList;
import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import io.cucumber.java.eo.Se;
import models.EventPlanner;
import models.RegisteredEvent;
import models.ServiceProvider;
import models.User;
import printers.MenusPrinter;
import views.ServiceProviderView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Scanner;
import java.util.logging.Logger;

public class ServiceProviderControl {
    private static final Logger logger = Logger.getLogger(ServiceProviderControl.class.getName());
    private static final Scanner scanner=new Scanner(System.in);

    private ServiceProviderControl() {

    }

    public static void showServiceProviderServices() {
        ServiceProvider currentprovider = (ServiceProvider) EventPlanner.getCurrentUser();
        List<String> serviceProvdierServices = new ArrayList<>();
        for (int i = 0; i < currentprovider.getServices().size(); i++) {
            String st1 = "Service info : \n";
            String st = st1 + currentprovider.getServices().get(i).toString() + "\n -------------------------------------------";
            serviceProvdierServices.add(st);

        }
        MenusPrinter.printListofStringWithNumbers(serviceProvdierServices, "\"Here is Your Service/s:\"");

    }

    public static void signout() {
        EventPlanner.setCurrentUser(null);
    }


    public static List<RegisteredEvent> getServiceProviderEvents() throws EmptyList {
        ServiceProvider serviceProvider = (ServiceProvider) EventPlanner.getCurrentUser();
        List<User> filteredUsers = getUsersRelatedWithServiceProvider(serviceProvider);

        if (filteredUsers.isEmpty()) {
            throw new EmptyList();
        }
          return getEventsRelatedWithServiceProvider(filteredUsers);
    }
    public static void showServiceProviderEvents(){
        try {
            printList(getServiceProviderEvents());
        }
            catch (EmptyList e){
            logger.info("You Don't  Have Any Events ");
            }
    }
    public static List<RegisteredEvent>  getServiceProviderUpComingEvents() throws EmptyList {

           List<RegisteredEvent> filterdEvents = getServiceProviderEvents();
           return filterdEvents.stream().filter(registeredEvent -> registeredEvent.getDate().isAfter(LocalDate.now())).toList();


    }
    public static void showServiceProviderUpcomingEvents() {
        try {
            while (true){
                printList(getServiceProviderUpComingEvents());
                logger.info("If you Want To Discard Event ,Enter Event Number \n To Go Back Enter B ");
                String choice = scanner.nextLine();
                while (!(choice.equals("B")||choice.equals("b")||Integer.parseInt(choice) <= getServiceProviderUpComingEvents().size())) {
                    logger.info("Invalid Input"+"\n If you Want To Discard Event ,Enter Event Number \n To Go Back Enter B " );
                    choice = scanner.nextLine();

                }
                if (choice.equals("B") || choice.equals("b")) {
                    ServiceProviderView.providerMenu();
                } else  {
                    EventsControl.deleteService(getServiceProviderUpComingEvents().get(Integer.parseInt(choice) - 1), (ServiceProvider) EventPlanner.getCurrentUser());
                    printList(getServiceProviderUpComingEvents());
                }

            }

        } catch(EmptyList | UserIsAlreadyExist | WeakPasswordException emptyList) {
            logger.info("You Don't  Have Any Events ");

        }
    }

    public static List<RegisteredEvent> getFilterdEvents(List<RegisteredEvent> reg) {

        return reg.stream().filter(event -> event.getServiceProviders().contains(EventPlanner.getCurrentUser())).toList();

    }

    public static List<User> getUsersRelatedWithServiceProvider(ServiceProvider serviceProvider) {

        List<User> allUsers = EventPlanner.getUsers();
        return allUsers.stream()
                .filter(user -> user.getRegisteredEvents().stream()
                        .anyMatch(event -> event.getServiceProviders().contains(serviceProvider)))
                .toList();
    }

    public static List<RegisteredEvent> getEventsRelatedWithServiceProvider(List<User> filteredUsers) {
        List<RegisteredEvent> serviceProvdierEvents = new ArrayList<>();
        for (int i = 0; i < filteredUsers.size(); i++) {
            User user = filteredUsers.get(i);
            List<RegisteredEvent> filterd = getFilterdEvents(user.getRegisteredEvents());
            for (int j = 0; j < filterd.size(); j++) {
                serviceProvdierEvents.add(filterd.get(j));
            }
        }
            return serviceProvdierEvents;

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
    public static void printList(List<RegisteredEvent>filterdEvents){
        List<String>  serviceProvdierEvents= makeStringListOfEvents(filterdEvents);
        MenusPrinter.printListofStringWithNumbers(serviceProvdierEvents, "Here is Your Event/s:");
    }

}
