package helpers;

import Exceptions.EventAlreadyExist;
import Exceptions.GoToMainMenuException;
import enumerations.ServiceType;
import models.EventPlanner;
import models.Service;
import models.ServiceProvider;
import models.User;
import views.ServiceProviderView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
public class ChoiceChecker {
    private static final Logger logger=Logger.getLogger(ChoiceChecker.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private ChoiceChecker() {

    }
    public static boolean isValidChoice(String choice,int range) {
        int readedNum=Integer.parseInt(choice);
        for(int i=1;i<=range;i++) {
            if(i==readedNum) {
                return true;
            }
        }
        return false;
    }


    public static boolean isValidNumberOfServices(String value) {
        return value.equals("2")||value.equals("3")||value.equals("4");
    }







    public static boolean againChecker(){
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

    public static String readingEventName(String eventName){
        while (true){
            try {
                ((User) EventPlanner.getCurrentUser()).checkEventExisting(eventName);
                break;
            } catch (EventAlreadyExist e) {
                logger.warning("This event name is already exist\nplease Enter another name: ");
                eventName = scanner.nextLine();
            }
        }
        return eventName;
    }
    public static boolean checkIfTheServiceAlreadyAdded(List<Service> serviceList,String choice) {
        Map <String,ServiceType> map =ServiceProviderView.hashmap();
        serviceList = serviceList.stream().filter(service -> service.getServiceType().equals(map.get(choice))).toList();
        return !serviceList.isEmpty();
    }

    public static String checkPackageProviderAddingProcess(List<Service> serviceList, String choice) {
        while (!ChoiceChecker.isValidChoice(choice,7) || (checkIfTheServiceAlreadyAdded(serviceList,choice))) {
            if (!ChoiceChecker.isValidChoice(choice,7)) {
                logger.info("Invalid Input , Choose Correct Service Number :\n");
                choice = scanner.nextLine();
            }

            else if (checkIfTheServiceAlreadyAdded(serviceList, choice)) {
                if (serviceList.size() > 2) {
                    logger.info("You Already Provide This Service ! , choose Another one or Enter B to Stop Adding :\n");
                    choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("b")) {
                        return "b";
                    }
                } else {
                    logger.info("You Already Provide This Service !,Choose Another One Because The Package Cannot Contain One Service  :\n");
                    choice = scanner.nextLine();
                }

            }
        }
        return choice;
    }

    public static String checkPackageProviderServiceChoice(ServiceProvider serviceProvider, String choice) throws GoToMainMenuException {
        boolean again = true;
        int ch;

        while (again) {
            try {
                ch = Integer.parseInt(choice);
                if (ch <= serviceProvider.getServices().size()) {
                    again=false;
                } else {
                    logger.info("Invalid Choice,Choose Again Or Enter B to Go Back");
                    choice = scanner.nextLine();
                }
            } catch (Exception e) {
                logger.info("Invalid Choice,Choose Again Or Enter B to Go Back");
                choice = scanner.nextLine();
                if(choice.equalsIgnoreCase("b")){
                    throw new GoToMainMenuException();
                }

            }
        }
return choice;

    }




    public static boolean checkIfItsCurrentService(Service service, String choice) {
        Map<String, ServiceType> map= ServiceProviderView.hashmap();
        try {
            return map.get(choice).equals(service.getServiceType());
        }
        catch (Exception e){
            return false;
        }
    }



    public static int readInt(){
        int integer;
        while(true){
            try{
            integer=scanner.nextInt();
            break;
            }
            catch (InputMismatchException e){
                logger.info("Invalid input, please enter again: \n");
                scanner.nextLine();
            }
        }
        return integer;

    }

}

