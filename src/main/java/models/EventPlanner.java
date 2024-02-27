package models;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EventPlanner {
    private static final List<User> users = new ArrayList<>();

    private EventPlanner() {
    }

    private User currentUser;

    public static void addUser(User user) throws UserIsAlreadyExist {
        if (users.contains(user))
            throw new UserIsAlreadyExist();
        users.add(user);
    }

    public static void removeUser(User user) throws UserNotFoundException {
        if (!users.contains(user)) {
            throw new UserNotFoundException();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


}
