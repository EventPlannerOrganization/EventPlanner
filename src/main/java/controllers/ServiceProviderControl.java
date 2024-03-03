package controllers;

import models.EventPlanner;
import models.RegisteredEvent;
import models.ServiceProvider;
import models.User;
import views.ServiceProviderView;


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

        for (int i = 0; i < currentprovider.getServices().size(); i++) {
            logger.info("Service info :");
            String st = currentprovider.getServices().get(i).toString();
            logger.info(st+"\n ----------------------------");

        }

    }

    public static void signout() {
        EventPlanner.setCurrentUser(null);
    }


    public static void showServiceProviderEvents() {
        ServiceProvider serviceProvider = (ServiceProvider) EventPlanner.getCurrentUser();
        List<User> filteredUsers = getUsersRelatedWithServiceProvider(serviceProvider);
        if(filteredUsers.isEmpty()){
            logger.info("You Don't  Have Any Events ");
            return;
        }
        for (int i = 0; i < filteredUsers.size(); i++) {
            User user = filteredUsers.get(i);
            List<RegisteredEvent> filterd = getFilterdEvents(user.getRegisteredEvents());
            for (int j = 0; j < filterd.size(); j++) {
                String events = filterd.get(j).toString2();
                logger.info(events);
                logger.info("-----------------------------------/n");
            }
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

}
