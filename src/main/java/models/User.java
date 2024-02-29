package models;

import enumerations.UserType;

public class User extends Person{
private UserType usertype;

    public User(Name name, Authentication authentication, Address address, ContactInfo contactInfo) {
        super(name, authentication, address, contactInfo);
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }
}
