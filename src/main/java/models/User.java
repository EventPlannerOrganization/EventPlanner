package models;

import enumerations.UserType;

import java.util.Date;
import java.util.List;

public class User extends Person{
    private UserType usertype;
    private RegisteredEvent registeredEvent;

    public User(Name name, Authentication authentication, Address address, ContactInfo contactInfo ) {
        super(name, authentication, address, contactInfo);
        usertype = UserType.USER;

    }



    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public RegisteredEvent getRegisteredEvent() {
        return registeredEvent;
    }

    public void setRegisteredEvent(RegisteredEvent registeredEvent) {
        this.registeredEvent = registeredEvent;
    }
}
