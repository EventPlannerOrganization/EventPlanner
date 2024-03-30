package models;

import exceptions.EventAlreadyExist;
import exceptions.EventNotFound;
import enumerations.UserType;
import java.util.ArrayList;
import java.util.List;


public class User extends Person {
    private UserType usertype;
    private List<RegisteredEvent> registeredEvents;
    private double totalCost;

    public User(Name name, Authentication authentication, Address address, ContactInfo contactInfo) {
        super(name, authentication, address, contactInfo);
        usertype = UserType.USER;
        registeredEvents = new ArrayList<>();
        setTotalCost(0);
    }

    public User() {
        super();
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }


    public List<RegisteredEvent> getRegisteredEvents() {
        return registeredEvents;
    }


    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void addToTotalCost(double newCost) {
        this.totalCost += newCost;
    }
    public double getTotalCost( ) {
    return totalCost;
    }




    public void checkEventExisting(String eventName) throws EventAlreadyExist {
        List<RegisteredEvent> similars = this.registeredEvents.stream().filter(e -> e.getEventName().equals(eventName)).toList();
        if (!similars.isEmpty() ) {
            throw new EventAlreadyExist();
        }

    }
    public  boolean isThisEventExist(String eventName) {
        try {
        checkEventExisting(eventName);
        return false;
    }
    catch (EventAlreadyExist e){
        return true;
    }
}
public RegisteredEvent getEventByName(String eventName) throws EventNotFound {
    List<RegisteredEvent> similars = this.registeredEvents.stream().filter(e -> e.getEventName().equals(eventName)).toList();
    if(similars.isEmpty()) {
        throw new EventNotFound();
    }
    return similars.get(0);
}

}