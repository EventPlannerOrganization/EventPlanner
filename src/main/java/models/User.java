package models;

import Exceptions.EventAlreadyExist;
import Exceptions.EventNotFound;
import enumerations.UserType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class User extends Person {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(totalCost, user.totalCost) == 0 && usertype == user.usertype && Objects.equals(registeredEvents, user.registeredEvents)&&getAuthentication().getUsername().equals(user.getAuthentication().getUsername()) &&
                getContactInfo().getEmail().equals( user.getContactInfo().getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usertype, registeredEvents, totalCost);
    }

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