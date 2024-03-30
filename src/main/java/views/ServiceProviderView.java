package views;


import Email.EmailService;
import exceptions.GoToMainMenuException;
import exceptions.UserIsAlreadyExist;
import exceptions.UserNotFoundException;
import exceptions.WeakPasswordException;

import exceptions.*;
import controllers.EventsControl;
import controllers.ServiceProviderControl;
import enumerations.ServiceType;
import helpers.ChoiceChecker;
import models.*;
import printers.MenusPrinter;
import javax.mail.MessagingException;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;


public class ServiceProviderView {
    private static final Logger logger = Logger.getLogger(ServiceProviderView.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    private ServiceProviderView() {


    }


    public static void showRequest(ServiceProvider serviceProvider) throws IOException, MessagingException {
        List<Request> requests = serviceProvider.getRequests();
        if (requests.isEmpty()) {
            logger.warning("Empty request list");
            return;
        }

        displayRequests(requests);
        int selectedRequest = getSelectedRequest(requests);
        if (selectedRequest == -1) {
            logger.warning("Invalid selection");
            return;
        }

        Request request = requests.get(selectedRequest-1);
        List<String> options = List.of("Accept", "Reject");
        int choice = getMenuChoice(options);

        if (choice == -1) {
            logger.warning("Invalid choice");
            return;
        }

        handleRequestChoice(choice, request, serviceProvider);
        EmailService emailService = new EmailService();
        emailService.sendEmail(request.getUserEmail(), "respond-body");

    }

    private static int getSelectedRequest(List<Request> requests) {
        logger.info("Please select a request:");
        return getIntegerInput(1, requests.size());
    }

    private static int getMenuChoice(List<String> options) {
        MenusPrinter.printMenu(options);
        return getIntegerInput(1, options.size());
    }

    private static int getIntegerInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= 1 && input <= max) {
                    return input;
                }
                logger.info("Please enter a valid integer between "+min+ " and "+ max);
            } catch (NumberFormatException e) {
                logger.warning("Please enter a valid integer");
            }
        }
    }

    public static void showRequset(ServiceProvider serviceProvider)  {
        List<String> requests=new ArrayList<>();
        if(serviceProvider.getRequests().isEmpty())
            logger.info("empty list");
       else {
            for (Request request : serviceProvider.getRequests()) {
                requests.add(request.getMessage());
            }
        }
    }

    private static void handleRequestChoice(int choice, Request request, ServiceProvider serviceProvider) {
        switch (choice) {
            case 1:
                ServiceProviderControl.respondToRequests(true, request, serviceProvider);
                break;
            case 2:
                ServiceProviderControl.respondToRequests(false, request, serviceProvider);
                break;
            default:
                logger.warning("Invalid choice");
                break;
        }

    }

    private static void displayRequests(List<Request> requests) {
        List<String> requestMessages = requests.stream().map(Request::getMessage).toList();
        MenusPrinter.printMenu(requestMessages);
    }


    public static void providerMenu() throws UserNotFoundException, MessagingException, IOException, UserIsAlreadyExist, WeakPasswordException {
        boolean flag = true;
        while (flag) {
            MenusPrinter.printServiceProviderMenu();
            logger.info("What do you want to do ?");
            String choice = scanner.nextLine();
            while (!ChoiceChecker.isValidChoice(choice,6)) {
                choice = scanner.nextLine();
                logger.info("Enter Valid Choice !");
            }
            switch (choice) {
                case "1":
                    ServiceProviderView.showServices((ServiceProvider) EventPlanner.getCurrentUser());
                    backToServiceProviderMenu();
                    break;
                case "2":
                    ServiceProviderView.updateServices((ServiceProvider) EventPlanner.getCurrentUser());
                    break;
                case "3":
                    ServiceProviderView.showEvents((ServiceProvider) EventPlanner.getCurrentUser());
                    break;
                case "4":
                    ServiceProviderView.showUpComingEvents((ServiceProvider) EventPlanner.getCurrentUser());
                    break;
                case "5":
                    ServiceProvider serviceProvider = (ServiceProvider) EventPlanner.getServiceProviderByUsername(EventPlanner.getCurrentUser().getAuthentication().getUsername());
                    showRequest(serviceProvider);
                    break;

                case "6":
                    EventPlanner.signout();
                    flag = false;
                    StartingView.staringView();
                    break;
                default:
                    // code block
            }
        }
    }

    private static void updateServices(ServiceProvider serviceProvider) {


        MenusPrinter.printServiceProviderEditMenu();
        logger.info(" Please Choose Number from Menu or Press B To Back To Main Menu");
        String choice = scanner.nextLine();

        while (!(ChoiceChecker.isValidChoice(choice,8)||choice.equalsIgnoreCase("b"))) {
            logger.info("Invalid Input , Please Choose Number from Menu or Press B To Back To Main Menu");
            choice = scanner.nextLine();


        }
        switch (choice) {
            case "1":
                ServiceProviderView.changeServiceProviderServiceView(serviceProvider);
                break;
            case "2":
                if (serviceProvider.isPackageProvider())
                    ServiceProviderView.changeServiceDescriptionForPackageProvider(serviceProvider);

                else {
                    ServiceProviderView.changeServiceDescription(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider));
                }
                break;
            case "3":
                if (serviceProvider.isPackageProvider()) {
                    ServiceProviderView.changeServicePriceForPackageProvider(serviceProvider);
                } else {
                    ServiceProviderView.changeServicePrice(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider));

                }
                break;


            default:
        }
    }

    private static void changeServicePriceForPackageProvider(ServiceProvider serviceProvider) {

        int ch;
        ServiceProviderView.showServices(serviceProvider);
        String choice = scanner.nextLine();
        try {
            choice = ChoiceChecker.checkPackageProviderServiceChoice(serviceProvider, choice);
            ch = Integer.parseInt(choice);
            changeServicePrice(serviceProvider.getServices().get(ch - 1));
        } catch (GoToMainMenuException e) {
            logger.info("Main Menu");
        }
    }

    private static void changeServiceDescriptionForPackageProvider(ServiceProvider serviceProvider) {

        int ch;
        ServiceProviderView.showServices(serviceProvider);
        String choice = scanner.nextLine();
        try {
            choice = ChoiceChecker.checkPackageProviderServiceChoice(serviceProvider, choice);
            ch = Integer.parseInt(choice);
            changeServiceDescription(serviceProvider.getServices().get(ch - 1));
        } catch (GoToMainMenuException e) {
            logger.info("Main Menu");
        }
    }


    private static void changeServicePrice(Service service) {
        String oldPrice = "Your Old Price is " + ServiceProviderControl.getServicePrice(service);
        logger.info(oldPrice);
        String newPrice = scanner.nextLine();
        if (newPrice.equals(ServiceProviderControl.getServicePrice(service))) {
            logger.info("Its The Same Price !");
            return;
        }
        ServiceProviderControl.editServicePrice(service, newPrice);
    }

    private static void changeServiceDescription(Service service) {
        String oldDescription = "Your Old Description is " + ServiceProviderControl.getServiceDescription(service);
        logger.info(oldDescription);
        logger.info("\n Enter The New Description : ");
        String newDescription = scanner.nextLine();
        if (newDescription.equals(ServiceProviderControl.getServiceDescription(service))) {
            logger.info("Its The Same Description");
            return;
        }

        ServiceProviderControl.editServiceDescription(service, newDescription);
    }


    private static void changeServiceProviderServiceView(ServiceProvider serviceProvider) {
        boolean flag = false;
        ServiceType serviceType = ServiceType.Null;
        MenusPrinter.printServicesMenuWithPcks();
        String string = "What is The Service You Want To Provide ? \n" + "If You Want To Go Back Press B";
        logger.info(string);
        String choice = scanner.nextLine();


        while ((!(ChoiceChecker.isValidChoice(choice,8)||choice.equalsIgnoreCase("b"))) || (ChoiceChecker.checkIfItsCurrentService(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider), choice) && !serviceProvider.isPackageProvider())) {

            if (!(ChoiceChecker.isValidChoice(choice,8)||choice.equalsIgnoreCase("b"))) {
                logger.info("Invalid Input , Please Choose Number from Menu or Press B To Back To Main Menu");
                choice = scanner.nextLine();
            } else if (ChoiceChecker.checkIfItsCurrentService(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider), choice)) {
                logger.info("this is your current Service ! , Choose Another Service or Back !");
                choice = scanner.nextLine();
            }

        }

        switch (choice) {
            case "1" -> {
                serviceType = ServiceType.DJ;
                flag = true;

            }
            case "2" -> {
                serviceType = ServiceType.Photography;
                flag = true;
            }
            case "3" -> {
                serviceType = ServiceType.Security;
                flag = true;
            }
            case "4" -> {
                serviceType = ServiceType.Cleaning;
                flag = true;
            }
            case "5" -> {
                serviceType = ServiceType.Decor_and_Design;
                flag = true;
            }
            case "6" -> {
                serviceType = ServiceType.Catering;
                flag = true;
            }
            case "7" -> {
                serviceType = ServiceType.Venue;
                flag = true;
            }
            case "8" -> {
                serviceProvider.setPackageProvider(true);
                List<Service> services = ServiceProviderView.addingProcessForPackageProvider();
                ServiceProviderControl.changePackageProviderServices(serviceProvider, services);
            }
            default ->
                flag=false;


        }
        if (flag) {
            logger.info("Enter Service Description:\n");
            String description = scanner.nextLine();
            logger.info("Enter Service Price :\n ");
            String price = scanner.nextLine();
            Service service = new Service(serviceType, Double.parseDouble(price), description);
            List<Service> list = new ArrayList<>();
            list.add(service);
            serviceProvider.setServices(list);
            serviceProvider.setPackageProvider(false);
            ServiceProviderControl.changeServiceProviderService(serviceProvider, service);

        }

    }


    private static List<Service> addingProcessForPackageProvider() {
        Map<String, ServiceType> map = hashmap();

        boolean again = true;
        List<Service> serviceList = new ArrayList<>();
        while (again) {
            MenusPrinter.printServicesMenu();
            logger.info("Select Service :\n");
            String choice = scanner.nextLine();
            choice = ChoiceChecker.checkPackageProviderAddingProcess(serviceList, choice);
            if (choice.equalsIgnoreCase("B")) {
                return serviceList;
            }
            ServiceType serviceType = map.get(choice);
            logger.info("Please Enter Service Description");
            String description = scanner.nextLine();
            logger.info("Please Enter Service Price");
            double price = Double.parseDouble(scanner.nextLine());
            serviceList.add(new Service(serviceType, price, description));
            if (serviceList.size() >= 2) {
                logger.info("Do you Want To Add More Services ? Enter Y for Yes , N for No");
                again = ChoiceChecker.againChecker();
            }
            if (serviceList.size() == 4) {
                logger.info("Your Package Contains All The Services you Cant Add Any Thing More");
                again = false;
            }
        }
        return serviceList;


    }

    public static Map<String, ServiceType> hashmap() {
        Map<String, ServiceType> map = new HashMap<>();
        map.put("1", ServiceType.DJ);
        map.put("2", ServiceType.Photography);
        map.put("3", ServiceType.Security);
        map.put("4", ServiceType.Cleaning);
        map.put("5", ServiceType.Decor_and_Design);
        map.put("6", ServiceType.Catering);
        map.put("7", ServiceType.Venue);
        return map;
    }


    private static void showEvents(ServiceProvider serviceProvider) {

        try {
            List<RegisteredEvent> events = ServiceProviderControl.getServiceProviderEvents(serviceProvider);
            MenusPrinter.printList(events);
        } catch (EmptyList e) {
            logger.info("You Don't  Have Any Events ");
        }
        backToServiceProviderMenu();
    }

    private static void showServices(ServiceProvider serviceProvider) {
        List<Service> serviceProvdierServices = ServiceProviderControl.getServiceProviderServices(serviceProvider);
        List<String> serviceProviderServiceString = new ArrayList<>();
        for (int i = 0; i < serviceProvider.getServices().size(); i++) {
            String st1 = "Service info : \n";
            String st = st1 + serviceProvdierServices.get(i).toString() + "\n -------------------------------------------";
            serviceProviderServiceString.add(st);

        }
        MenusPrinter.printListofStringWithNumbers(serviceProviderServiceString, "\"Here is Your Service/s:\"");

    }


    private static void showUpComingEvents(ServiceProvider serviceProvider) {
        showServiceProviderUpcomingEvents(serviceProvider);
    }


    private static void backToServiceProviderMenu() {
        logger.info("To Return Back Enter B");
        String choice = scanner.nextLine();
        while (!(choice.equals("B") || choice.equals("b"))) {
            logger.info("To Return Back Enter B");
            choice = scanner.nextLine();
        }
    }

    public static void showServiceProviderUpcomingEvents(ServiceProvider serviceProvider) {
        try {
            String string = new StringBuilder().append("Invalid Input").append("\n If you Want To Discard Event ,Enter Event Number \n To Go Back Enter B ").toString();

            while (true){
                MenusPrinter.printList(ServiceProviderControl.getServiceProviderUpComingEvents(serviceProvider));
                logger.info("If you Want To Discard Event ,Enter Event Number \n To Go Back Enter B ");
                String choice = scanner.nextLine();
                while (!(choice.equalsIgnoreCase("B") ||
                        Integer.parseInt(choice) <= ServiceProviderControl.getServiceProviderUpComingEvents(serviceProvider).size())) {
                    logger.info(string);
                    choice = scanner.nextLine();

                }
                if (!choice.equalsIgnoreCase("B")) {

                    EventsControl.deleteService(ServiceProviderControl.getServiceProviderUpComingEvents(serviceProvider).get(Integer.parseInt(choice) - 1),
                            serviceProvider);
                    MenusPrinter.printList(ServiceProviderControl.getServiceProviderUpComingEvents(serviceProvider));
                    backToServiceProviderMenu();
                    break;
                } else
                    return;

            }


        } catch (EmptyList | ServiceNotFoundException emptyList) {
            logger.info("You Don't  Have Any Events ");
            backToServiceProviderMenu();


        }
    }


}


