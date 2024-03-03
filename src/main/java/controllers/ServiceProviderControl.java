package controllers;

import models.EventPlanner;
import models.RegisteredEvent;
import models.ServiceProvider;
import models.User;
import printers.MenusPrinter;
import views.ServiceProviderView;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ServiceProviderControl {
    private static final Logger logger = Logger.getLogger(ServiceProviderControl.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    private ServiceProviderControl() {

    }

    public static void showServiceProviderServices() {
        ServiceProvider currentprovider = (ServiceProvider) EventPlanner.getCurrentUser();
List<String> serviceProvdierServices = new ArrayList<>();
        for (int i = 0; i < currentprovider.getServices().size(); i++) {
            String st1 = "Service info : \n";
            String st = st1 + currentprovider.getServices().get(i).toString() +"\n -------------------------------------------";
            serviceProvdierServices.add(st);

        }
        MenusPrinter.printListofStringWithNumbers(serviceProvdierServices,"\"Here is Your Service/s:\"");

    }

    public static void signout() {
        EventPlanner.setCurrentUser(null);
    }


    public static void showServiceProviderEvents() {
        ServiceProvider serviceProvider = (ServiceProvider) EventPlanner.getCurrentUser();
        List<User> filteredUsers = getUsersRelatedWithServiceProvider(serviceProvider);
        List<String> serviceProvdierEvents = new ArrayList<>();

        if(filteredUsers.isEmpty()){
            logger.info("You Don't  Have Any Events ");
            return;
        }
        for (int i = 0; i < filteredUsers.size(); i++) {
            User user = filteredUsers.get(i);
            List<RegisteredEvent> filterd = getFilterdEvents(user.getRegisteredEvents());
            for (int j = 0; j < filterd.size(); j++) {
                String st1 = "Service info : \n";
                String events = st1 +filterd.get(j).toString2() +"\n -------------------------------------------";
                serviceProvdierEvents.add(events);
            }
        }
        MenusPrinter.printListofStringWithNumbers(serviceProvdierEvents,"Here is Your Event/s:");

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

}
