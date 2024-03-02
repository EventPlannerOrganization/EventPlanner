package models;

import enumerations.UserType;

import java.util.ArrayList;
import java.util.List;

public class User extends Person{
    private UserType usertype;
    private List  <RegisteredEvent> registeredEvents;

    public User(Name name, Authentication authentication, Address address, ContactInfo contactInfo ) {
        super(name, authentication, address, contactInfo);
        usertype = UserType.USER;
        registeredEvents=new ArrayList<>();
    }



    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public List<RegisteredEvent> getRegisteredEvent() {
        return registeredEvents;
    }

    public void setRegisteredEvent(List <RegisteredEvent> registeredEvent) {
        this.registeredEvents = registeredEvent;
    }
}
