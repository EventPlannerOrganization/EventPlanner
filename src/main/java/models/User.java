package models;

import enumerations.UserType;

public class User extends Person{
    private UserType usertype;

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }
}
