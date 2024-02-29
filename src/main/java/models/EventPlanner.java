package models;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import enumerations.ServiceType;


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
       List<Person> result2 = users.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();

        if (result.isEmpty())
            throw new UserNotFoundException();



        return result.get(0);
    }
    public static Person getCurrentUser() {
        return currentUser;

    }
    public static void setCurrentUser(Person currentUser) {
        EventPlanner.currentUser = currentUser;

    }

    public static void initializeRepositoryWithData() throws UserIsAlreadyExist {
        User user = new User(new Name("Naser", "Mohammad", "Abu-Safieh"),
                new Authentication("Naser", "Moha999www"),
                new Address("Palestine", "Nablus"),
        new ContactInfo("s12199887@stu.najah.edu","0599715584")
              );

      EventPlanner.addUser(user);
      ServiceProvider serviceProvider = new ServiceProvider(new Name("mo","munir","shadid"),
              new Authentication("mo","12345"),new Address("palestine","tulkarm"),
              new ContactInfo("mo@gmail.com","9412412"),
              new Service(ServiceType.DJ,3200,"tesing"));
      EventPlanner.addUser(serviceProvider);
    }

}
