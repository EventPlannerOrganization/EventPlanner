package views;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import controllers.ServiceProviderControl;
import enumerations.ServiceType;
import helpers.ChoiceChecker;
import models.EventPlanner;
import models.Service;
import models.ServiceProvider;
import printers.MenusPrinter;

import java.util.Scanner;
import java.util.logging.Logger;

public class ServiceProviderView {
    private static final Logger logger=Logger.getLogger(ServiceProviderView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private ServiceProviderView() {


    }


    public static void providerMenu() throws UserIsAlreadyExist, WeakPasswordException {
        boolean flag = true;
        while (flag) {
            MenusPrinter.printServiceProviderMenu();
            logger.info("What do you want to do ?");
            String choice = scanner.nextLine();
            while (!ChoiceChecker.ServiceProviderMenuChecker(choice)) {
                choice = scanner.nextLine();
                logger.info("Enter Valid Choice !");
            }
            switch (choice) {
                case "1":
                    ServiceProviderView.showServices((ServiceProvider) EventPlanner.getCurrentUser());
                    break;
                case "2":
                    ServiceProviderView.updateServices((ServiceProvider) EventPlanner.getCurrentUser());
                    break;
                case "3":
                    ServiceProviderView.showEvents();
                    break;
                case "4":
                    ServiceProviderView.showUpComingEvents();
                    break;
                case "5":
                    ServiceProviderControl.signout();
                    flag = false;
                    break;
                default:
                    // code block
            }
        }
    }

    private static void updateServices(ServiceProvider serviceProvider)  {

        if(serviceProvider.isPackageProvider()){
         //   MenusPrinter.printPackageProviderEditMenu();

        }
        else {
            MenusPrinter.printServiceProviderEditMenu();
            logger.info(" Please Choose Number from Menu or Press B To Back To Main Menu");
            String choice = scanner.nextLine();

            while(!(ChoiceChecker.editServiceMenuCheck(choice))){
                logger.info("Invalid Input , Please Choose Number from Menu or Press B To Back To Main Menu");
              choice= scanner.nextLine();


            }
            switch (choice) {
                case "1":
             ServiceProviderView.changeServiceProviderServiceView(serviceProvider);
                    break;
                case "2":
                    ServiceProviderView.changeServiceDescription(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider));
                    break;
                case "3":
                    ServiceProviderView.changeServicePrice(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider));
                    break;


                default:
            }
            }


        }

    private static void changeServicePrice(Service service) {
        String oldPrice = "Your Old Price is " + ServiceProviderControl.getServicePrice(service);
        logger.info(oldPrice);
        String newPrice= scanner.nextLine();
        if(newPrice.equals(ServiceProviderControl.getServicePrice(service))){
            logger.info("Its The Same Price !");
            return;
        }
        service.setPrice(Double.parseDouble(newPrice));
    }

    private static void changeServiceDescription(Service service) {
        String oldDescription = "Your Old Description is " + ServiceProviderControl.getServiceDescription(service);
        logger.info(oldDescription);
        logger.info("\n Enter The New Description : ");
        String newDescription = scanner.nextLine();
        if(newDescription.equals(ServiceProviderControl.getServiceDescription(service))){
            logger.info("Its The Same Descritpion");
            return;
        }

        ServiceProviderControl.editServiceDescription(service,newDescription);
    }

    private static void changeServiceProviderServiceView(ServiceProvider serviceProvider)  {

        MenusPrinter.printServicesMenuWithPcks();
        String string = "What is The Service You Want To Provide ? \n" + "If You Want To Go Back Press B";
        logger.info(string);
        String choice = scanner.nextLine();


        while ((!ChoiceChecker.editServiceMenuCheck(choice))||ServiceProviderControl.checkIfitsCurrentService(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider),choice)) {

            if(!ChoiceChecker.editServiceMenuCheck(choice)) {
                logger.info("Invalid Input , Please Choose Number from Menu or Press B To Back To Main Menu");
                choice = scanner.nextLine();
            }
            else if(ServiceProviderControl.checkIfitsCurrentService(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider),choice)){
                logger.info("this is your current Service ! , Choose Another Service or Back !");
choice=scanner.nextLine();
            }

        }

        switch (choice) {
            case "1":
               changeService(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider),ServiceType.DJ);

              break;
            case "2":
               changeService(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider),ServiceType.Photography);
               break;
            case "3":
                changeService(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider),ServiceType.Security);
                break;
            case "4":
              changeService(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider),ServiceType.Cleaning);
                break;
            case "5":
                serviceProvider.setPackageProvider(true);

                 break;
            default:
                }


                }








    private static void showEvents()  {

        ServiceProviderControl.showServiceProviderEvents();
        backToServiceProviderMenu();
    }

    private static void showServices(ServiceProvider serviceProvider) {

        ServiceProviderControl.showServiceProviderServices(serviceProvider);
        backToServiceProviderMenu();


    }
    private static void showUpComingEvents() {
        ServiceProviderControl.showServiceProviderUpcomingEvents();
        backToServiceProviderMenu();


    }
    private static void backToServiceProviderMenu()  {
        logger.info("To Return Back Enter B");
        String choice = scanner.nextLine();
        while (!(choice.equals("B") || choice.equals("b"))) {
            logger.info("To Return Back Enter B");
            choice = scanner.nextLine();
        }
    }

    public static void changeService(Service service, ServiceType serviceType) {
        ServiceProviderControl.editServiceType(service,serviceType);
        logger.info("Enter Your Service Description :");
        String description = scanner.nextLine();
        ServiceProviderControl.editServiceDescription(service,description);
        logger.info("Enter Your Service Price :");
        String price = scanner.nextLine();
        ServiceProviderControl.editServicePrice(service,price);
    }

}
