package controllers;

import models.*;
import views.EventsView;

import java.time.LocalDate;
import java.util.List;

public class EventsControl {
    private EventsControl() {
    }
    public static void addEvent(LocalDate date, String name, List<ServiceProvider> serviceProviders, double cost, List<String> guestsEmails){
        RegisteredEvent registeredEvent =new RegisteredEvent(name,serviceProviders,date,cost,guestsEmails);

        User currentUser=(User) (EventPlanner.getCurrentUser());
        currentUser.getRegisteredEvents().add(registeredEvent);
        currentUser.addToTotalCost(cost);

    }
    public static void editEventName(RegisteredEvent event,String newName){
        event.setEventName(newName);
    }
    public static void deleteService(ServiceProvider service,RegisteredEvent event){
    event.getServiceProviders().remove(service);
    event.subFromCost(service.getServices().get(0).getPrice());// note this does not include package provider

    }

}
