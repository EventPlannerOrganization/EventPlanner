package controllers;

import models.*;
import java.util.Date;
import java.util.List;

public class EventsControl {
    private EventsControl() {
    }
    public static void addEvent(Date date, String name,List<ServiceProvider> serviceProviders,double cost){
        RegisteredEvent registeredEvent =new RegisteredEvent();
        registeredEvent.setEventName(name);
        registeredEvent.setDate(date);
        registeredEvent.setServiceProviders(serviceProviders);
        registeredEvent.setCost(cost);
        User currentUser=(User) (EventPlanner.getCurrentUser());
        currentUser.getRegisteredEvents().add(registeredEvent);
        currentUser.addToTotalCost(cost);

    }


}
