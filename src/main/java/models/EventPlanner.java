package models;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import enumerations.ServiceType;
import enumerations.UserType;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner {
    private static final List<Person> users = new ArrayList<>();
    public static List<Person> getUsersList(){
        return users;
    }

    private EventPlanner() {
    }

    private static Person currentUser=null;

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
    public static  boolean checkEmailIfExist(String email)  {
        List<Person> result= users.stream().filter(user -> user.getContactInfo().getEmail().equals(email)).toList();
        if (result.isEmpty())
          return false;

        return true;
    }
    public static  Person getUserByEmail(String email) throws UserNotFoundException {
        List<Person> result= users.stream().filter(user -> user.getContactInfo().getEmail().equals(email)).toList();
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

    public static double calculateTotalPriceForMultiProviders(List<ServiceProvider> serviceProviders)
    {
        double totalPrice=0;
        for(ServiceProvider element:serviceProviders)
            {
            totalPrice+=element.calculateServiceProviderPrice();
            }
        return totalPrice;
    }
    public  static  List<User> getUsers(){
        List<Person> result = users.stream().filter(user -> User.class.isAssignableFrom(user.getClass())).toList();
        List <User> users =new ArrayList<>();
        User uElement;
        for(Person element :result){
            uElement=(User)element;
            if(uElement.getUsertype().equals(UserType.USER))
                users.add((User) element);
        }
        return users;
    }
    public static void initializeRepositoryWithData() throws UserIsAlreadyExist {
       cleanRepositry();

        User user = new User(new Name("Naser", "Mohammad", "Abu-Safieh"),
                new Authentication("Naser", "m123"),
                new Address("Palestine", "Nablus"),
        new ContactInfo("s12199887@stu.najah.edu","0599715584")
              );

      EventPlanner.addUser(user);
      List<Service>services=new ArrayList<>();
      services.add(  new Service(ServiceType.DJ,3200,"tesing"));
      ServiceProvider serviceProvider = new ServiceProvider(new Name("mo","munir","shadid"),
              new Authentication("mohammad03","12345"),new Address("palestine","tulkarm"),
              new ContactInfo("3sfr3sfr@gmail.com","9412412"),
            services);
      EventPlanner.addUser(serviceProvider);


        List<Service>services2=new ArrayList<>();
        services2.add(  new Service(ServiceType.Security,3200,"tesing"));
        ServiceProvider serviceProvider2 = new ServiceProvider(new Name("baha","khalid","alawneh"),
                new Authentication("baha02","0000"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services2);
        EventPlanner.addUser(serviceProvider2);

        List<Service>services3=new ArrayList<>();
        services3.add(  new Service(ServiceType.Photography,3200,"tesing"));
        services3.add(new Service(ServiceType.Security,3200,"tesing"));
        ServiceProvider serviceProvider3 = new ServiceProvider(new Name("jamil","munir","shadid"),
                new Authentication("Ibrahim160","bbaa12"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services3);
        EventPlanner.addUser(serviceProvider3);

        List<Service>services4=new ArrayList<>();
        services3.add(  new Service(ServiceType.Cleaning,3200,"tesing"));
        ServiceProvider serviceProvider4 = new ServiceProvider(new Name("jamil","mohammad","shadid"),
                new Authentication("saleem04","bbaa12"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services3);
        EventPlanner.addUser(serviceProvider4);

        List<Service>services5=new ArrayList<>();
        services5.add(  new Service(ServiceType.Cleaning,3200,"tesing"));
        ServiceProvider serviceProvider5 = new ServiceProvider(new Name("jamil","mohammad","shadid"),
                new Authentication("hamid02","bbaa12"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services5);
        EventPlanner.addUser(serviceProvider5);
        List<Service> service6=new ArrayList<>();
        service6.add(new Service(ServiceType.Cleaning,2000,"helllo"));
        ServiceProvider serviceProvider6 = new ServiceProvider(new Name("jamil","mohammad","shadid"),
                new Authentication("aliii","bbaa12"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                service6);
        EventPlanner.addUser(serviceProvider6);

        User user2 = new User(new Name("khalid", "Mohammad", "Abu-Safieh"),
                new Authentication("khalid", "123"),
                new Address("Palestine", "Nablus"),
                new ContactInfo("s12199887@stu.najah.edu","0599715584")
        );
            user2.setUsertype(UserType.ADMIN);
            EventPlanner.addUser(user2);

        User user3 = new User(new Name("sam", "Mohammad", "Abu-Safieh"),
                new Authentication("Karim", "123"),
                new Address("Palestine", "Nablus"),
                new ContactInfo("s12199887@stu.najah.edu","0599715584")
        );

        EventPlanner.addUser(user3);
        User user4 = new User(new Name("Nassar", "Mohammad", "Abu-Safieh"),
                new Authentication("Nassar", "123"),
                new Address("Palestine", "Nablus"),
                new ContactInfo("s12199887@stu.najah.edu","0599715584")
        );
        EventPlanner.addUser(user4);
        List<ServiceProvider> serviceProviders=new ArrayList<>();
        serviceProviders.add(serviceProvider);
        serviceProviders.add(serviceProvider2);
        LocalDate localDate=LocalDate.of(2024,9,10);
        List<String> emails=new ArrayList<>();
        emails.add("s12113028@stu.najah.edu");
        emails.add("3sfr3sfr@gmail.com");

        user.getRegisteredEvents().add(new RegisteredEvent("Wedding Celebration",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails));
         localDate=LocalDate.of(2024,8,10);
        List<String> emails1=new ArrayList<>();
        emails1.add("bahaalawneh07@gmail.com");
        user.getRegisteredEvents().add(new RegisteredEvent("open day1",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails1));
        localDate=LocalDate.of(2024,4,10);

        user2.getRegisteredEvents().add(new RegisteredEvent("wedding party",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails));

        List<ServiceProvider> serviceProviders22=new ArrayList<>();
        serviceProviders.add(serviceProvider3);
        user3.getRegisteredEvents().add(new RegisteredEvent("Birthday Bash",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders22),
                emails));
        user3.getRegisteredEvents().add(new RegisteredEvent("Food Festival",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders22),
                emails));
        localDate=LocalDate.of(2024,8,10);

        RegisteredEvent registeredEvent=new RegisteredEvent("wedding party",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails);
        for(ServiceProvider element:serviceProviders){
            element.getBookedDates().add(localDate);
        }
        registeredEvent.setLocation("jerusalem");
        user4.getRegisteredEvents().add(registeredEvent);

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
