package models;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import enumerations.ServiceType;


import java.time.LocalDate;
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
    public static Person checkUser(String username) throws UserNotFoundException {
        List<Person> result= users.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();
        if (result.isEmpty())
            throw new UserNotFoundException();

        return result.get(0);
    }
    public static Person getUserByUsername(String username) throws UserNotFoundException {
        List<Person> result = users.stream().filter(user -> User.class.isAssignableFrom(user.getClass())).toList();
        result = result.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();

        if (result.isEmpty())
            throw new UserNotFoundException();



        return result.get(0);
    }

    public static Person getServiceProviderByUsername(String username) throws UserNotFoundException {
        List<Person> result = users.stream().filter(user -> ServiceProvider.class.isAssignableFrom(user.getClass())).toList();
        result = result.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();

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

    public  static  List<ServiceProvider> getServiceProviders()  {
        List<Person> result = users.stream().filter(user -> ServiceProvider.class.isAssignableFrom(user.getClass())).toList();
        List <ServiceProvider> services =new ArrayList<>();
        for(Person element :result){
            services.add((ServiceProvider) element);
        }
        return services;
    }
    public static List<ServiceProvider>getPakageProviders(){
        return getServiceProviders().stream().filter(ServiceProvider::isPackageProvider).toList();
    }

    public  static  List<User> getUsers(){
        List<Person> result = users.stream().filter(user -> User.class.isAssignableFrom(user.getClass())).toList();
        List <User> users =new ArrayList<>();
        for(Person element :result){
            users.add((User) element);
        }
        return users;
    }
    public static void initializeRepositoryWithData() throws UserIsAlreadyExist {
        users.clear();
        System.out.println("bahaaaaa");
        User user = new User(new Name("Naser", "Mohammad", "Abu-Safieh"),
                new Authentication("Naser", "m123"),
                new Address("Palestine", "Nablus"),
        new ContactInfo("s12199887@stu.najah.edu","0599715584")
              );

      EventPlanner.addUser(user);
      List<Service>services=new ArrayList<>();
      services.add(  new Service(ServiceType.DJ,3200,"tesing"));
      ServiceProvider serviceProvider = new ServiceProvider(new Name("mo","munir","shadid"),
              new Authentication("mo","12345"),new Address("palestine","tulkarm"),
              new ContactInfo("mo@gmail.com","9412412"),
            services);
      EventPlanner.addUser(serviceProvider);


        List<Service>services2=new ArrayList<>();
        services2.add(  new Service(ServiceType.Security,3200,"tesing"));
        ServiceProvider serviceProvider2 = new ServiceProvider(new Name("baha","khalid","alawneh"),
                new Authentication("baha","0000"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services2);
        EventPlanner.addUser(serviceProvider2);

        List<Service>services3=new ArrayList<>();
        services3.add(  new Service(ServiceType.Photography,3200,"tesing"));
        services3.add(new Service(ServiceType.Security,3200,"tesing"));
        ServiceProvider serviceProvider3 = new ServiceProvider(new Name("jamil","munir","shadid"),
                new Authentication("baha alawneh","bbaa12"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services3);
        EventPlanner.addUser(serviceProvider3);

        User user2 = new User(new Name("khalid", "Mohammad", "Abu-Safieh"),
                new Authentication("khalid", "123"),
                new Address("Palestine", "Nablus"),
                new ContactInfo("s12199887@stu.najah.edu","0599715584")
        );

        EventPlanner.addUser(user2);

        User user3 = new User(new Name("sam", "Mohammad", "Abu-Safieh"),
                new Authentication("Nasernnnn", "123"),
                new Address("Palestine", "Nablus"),
                new ContactInfo("s12199887@stu.najah.edu","0599715584")
        );

        EventPlanner.addUser(user3);
    }

     public static List<ServiceProvider> getServiceProvidersNotBookedinThisDate(LocalDate date) {
         return EventPlanner.getServiceProviders().stream().filter(provider -> ! provider.getBookedDates().contains(date)).toList();
     }



    public static List<ServiceProvider> getServiceProviderByServiceType(ServiceType serviceType, LocalDate date) {
        return getServiceProvidersNotBookedinThisDate(date).stream().filter(provider -> provider.getServices().get(0).getServiceType().equals(serviceType)&&!provider.isPackageProvider()).toList();
    }
    public static void cleanRepositry() {
        users.clear();
    }
}
