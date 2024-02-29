package models;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EventPlanner {
    private static final List<Person> users = new ArrayList<>();

    private EventPlanner() {
    }

    private static Person currentUser;

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
        List<Person> result = users.stream().filter(user ->   user.getClass().getName().equals(ServiceProvider.class.getName())).toList();
       // List<Person> result = users.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();

        if (result.isEmpty())
            throw new UserNotFoundException();
<<<<<<< HEAD

        return result.getFirst();
=======
        return result.get(0);
>>>>>>> 86d3fec84ccfea8c4bf85421774284666f995e4d
    }
    public static Person getCurrentUser() {
        return currentUser;

    }
    public static void setCurrentUser(Person currentUser) {
        EventPlanner.currentUser = currentUser;

    }



}
