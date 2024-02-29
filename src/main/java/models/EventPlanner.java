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

    public  static  List<ServiceProvider> getServiceProviders(){
        List<Person> result = users.stream().filter(user -> ServiceProvider.class.isAssignableFrom(user.getClass())).toList();
        List <ServiceProvider> services =new ArrayList<>();
        for(Person element :result){
            services.add((ServiceProvider) element);
        }
        return services;
    }

    public  static  List<User> getUsers(){
        List<Person> result = users.stream().filter(user -> User.class.isAssignableFrom(user.getClass())).toList();
        List <User> users =new ArrayList<>();
        for(Person element :result){
            users.add((User) element);
        }
        return users;
    }


}
