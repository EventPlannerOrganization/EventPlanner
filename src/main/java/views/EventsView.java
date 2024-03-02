package views;


import controllers.EventsControl;
import enumerations.ServiceType;
import models.*;
import printers.MenusPrinter;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;



public class EventsView {
    private static final Logger logger=Logger.getLogger(EventsView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private static double cost;
    private EventsView() {

    }
    public static void registerEventView(){
        cost=0;
        List<ServiceProvider> providers;
        logger.info("To get started, please provide the following information: \n* Enter Event Name");
        String name=scanner.nextLine();
        logger.info("* Enter Date of your event \n - Day (1-31): ");
        int day=scanner.nextInt();
        logger.info(" - Month (1-12): ");
        int month=scanner.nextInt();
        logger.info("- Year : ");
        int year=scanner.nextInt();

        LocalDate date=LocalDate.of(year,month,day);
        logger.info("Add Services:\n");
        List <ServiceProvider> addedProviders= addingProcess(date);
        logger.info("Add guests :\n");
        List<String> guestsEmails =readeGuestsEmails();
        EventsControl.addEvent(date,name,addedProviders,cost,guestsEmails);
    }



    public static List<ServiceProvider> addingProcess(LocalDate date){
        boolean again=true;
        List <ServiceProvider> addedProviders=new ArrayList<>();
        scanner.nextLine();
        while(again) {
            logger.info("Select one:\n");
            MenusPrinter.printServicesMenu();
            String serviceNum = scanner.nextLine();
            ServiceType serviceType = switch (serviceNum) {
                case "1" -> ServiceType.DJ;
                case "2" -> ServiceType.Photography;
                case "3" -> ServiceType.Security;
                case "4" -> ServiceType.Cleaning;
                default -> null;
            };
            List<ServiceProvider> filteredProvidersList = EventPlanner.getServiceProviderByServiceType(serviceType,date);
            if (filteredProvidersList.isEmpty())
                logger.info("No Services Available:\nUnfortunately, there are no services available for the specified service type and time.\n");
            else {
                ServiceProvider newServiceProvider=selectedServiceFromServicesList(filteredProvidersList);
                if(newServiceProvider!=null) {
                    newServiceProvider.getBookedDates().add(date);
                    addedProviders.add(newServiceProvider);}
            }
            //this called Text block which begin with """, sonarLint need to useing it insted string
            logger.info("""
                    Do you want to add another service?
                    - Enter 'y' to add another service.
                    - Enter 'n' to finish and proceed.
                    """);
            again=againChecker();
        }
        return addedProviders;
    }


    public static ServiceProvider selectedServiceFromServicesList(List<ServiceProvider>filteredProvidersList){

        MenusPrinter.printServicesList(filteredProvidersList);
        int addedNumber = Integer.parseInt( scanner.nextLine());
        if(addedNumber<=filteredProvidersList.size()){
            for(Service e:filteredProvidersList.get(addedNumber-1).getServices()){//this loop will calculate the packeges by summing its services prices,and this must replace with another functionality ...
                cost+=e.getPrice();
            }
            return filteredProvidersList.get(addedNumber-1);
        }
        return null;
    }
    private static boolean againChecker(){
        boolean again=true;
        String choice = scanner.nextLine();
        boolean wrongChoice = true;
        while (wrongChoice) {
            if (choice.equals("y")) {
                wrongChoice = false;
            } else if (choice.equals(("n"))) {
                again = false;
                wrongChoice = false;
            } else{
                logger.info("Invalid choice. Please enter either 'y' or 'n'.\n");
                choice = scanner.nextLine();
            }
        }
        return again;
    }


    private static List<String> readeGuestsEmails(){
        List<String> guestsEmails=new ArrayList<>();
        logger.info("Enter the number of guests. You can adjust this number and modify the list as needed:\n");
        int serviceNum = Integer.parseInt(scanner.nextLine());
        logger.info("For each guest, please enter their email address:\n");
        for(int i=0;i<serviceNum;i++){
            String s="\n"+(i+1)+"- ";
            logger.info(s);
            String email = scanner.nextLine();
            guestsEmails.add(email);
        }

        return  guestsEmails;

    }

    public static void editUpCommingEvents(){
        logger.info("Select Event to Editing it: ");
        User currentUser=(User) EventPlanner.getCurrentUser();
        List<RegisteredEvent> myEvents = currentUser.getRegisteredEvents().stream().filter(event ->!event.getDate().isBefore(LocalDate.now()) ).toList();
        MenusPrinter.printEventsList(myEvents);
        int addedNumber = Integer.parseInt( scanner.nextLine());
        if(addedNumber<=myEvents.size()){
            editingEventView(myEvents.get(addedNumber-1));
        }
        //else if(addedNumber==myEvents.size()+1) ;


    }
    public static void showMyevents(){
        User currentUser=(User) EventPlanner.getCurrentUser();
        List <RegisteredEvent> myEvents=currentUser.getRegisteredEvents();
        MenusPrinter.printEventsList(myEvents);
    }


    //this method can be deleted
    private static void printEventsList(List<RegisteredEvent> eventList){
        int counter=1;
        String s;
        for(RegisteredEvent element :eventList){
            s="\n"+counter+"- "+element.toString();
            logger.info(s);
            counter++;
        }
    }

    private static void editingEventView(RegisteredEvent event)
    {
        MenusPrinter.printEditingChoices();
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                editEventName(event);
                break;
            case "2":
                event.addServices();
                break;
            case "3":
                break;
            case "4":

                break;
            case "5":

                break;
            case "6":
                break;
            default:
                // code block
        }
    }


    private static void editEventName(RegisteredEvent event){
        logger.info("Please, Enter new name for the event: ");
        String newName = scanner.nextLine();
        EventsControl.editEventName(event,newName);
    }

}
