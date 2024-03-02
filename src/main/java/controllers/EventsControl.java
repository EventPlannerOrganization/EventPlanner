package controllers;

import models.*;
import java.util.Date;
import java.util.List;

public class EventsControl {
    private EventsControl() {
    }
    public static void addEvent(Date date, String name,List<ServiceProvider> serviceProviders,double cost,List<String> guestsEmails){
        RegisteredEvent registeredEvent =new RegisteredEvent(name,serviceProviders,date,cost,guestsEmails);

        User currentUser=(User) (EventPlanner.getCurrentUser());
        currentUser.getRegisteredEvents().add(registeredEvent);
        currentUser.addToTotalCost(cost);

    }


}
