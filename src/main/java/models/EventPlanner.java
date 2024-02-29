package models;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EventPlanner {
    private static final List<Person> users = new ArrayList<>();

    private EventPlanner() {
    }

    private Person currentUser;

    public static void addUser(Person user) throws UserIsAlreadyExist {
        if (users.contains(user))
            throw new UserIsAlreadyExist();
        users.add(user);
    }


    public static void removeUser(Person user) throws UserNotFoundException {
        if (!users.contains(user)) {
            throw new UserNotFoundException();
        }
    }
    public static Person getUserByUsername(String username) throws UserNotFoundException {
        List<Person> result = users.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();

        if (result.isEmpty())
            throw new UserNotFoundException();

        return result.getFirst();
    }
    public Person getCurrentUser() {
        return currentUser;

    }
    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;

    }



}
