package controllers;

import models.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventsControl {
    private EventsControl() {
    }
    public static void addEvent(Date date, String name){
        RegisteredEvent registeredEvent =new RegisteredEvent();
        registeredEvent.setEventName(name);
        registeredEvent.setDate(date);
        User currentUser=(User) (EventPlanner.getCurrentUser());
        currentUser.getRegisteredEvent().add(registeredEvent);

    }


}
