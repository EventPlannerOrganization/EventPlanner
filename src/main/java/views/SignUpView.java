package views;

import controllers.SignUp;
import enumerations.ServiceType;
import helpers.ChoiceChecker;
import helpers.PasswordChecker;
import models.*;
import printers.MenusPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class SignUpView {
    private static final Logger logger=Logger.getLogger(SignUpView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private SignUpView() {

    }
    public static void signUpView()  {
        MenusPrinter.printSignUpAsMenu();
        String signUpAs=scanner.nextLine();
        while (!ChoiceChecker.isOneOrTwo(signUpAs)) {
            logger.info("Enter Valid Choice: ");
            signUpAs=scanner.nextLine();
        }
        if(signUpAs.equals("1"))signUpAsUserView();
        else if (signUpAs.equals("2")) signUpAsServiceProviderView();
    }

    public static void signUpAsServiceProviderView() {
        Person primaryInfo=readPrimaryInfo();

        List<Service> list = new ArrayList<>();
        logger.info("if you want to become Package Provider Enter 1 ,Else Enter 2 ");
        String choiceBetweenPackageOrNormal = scanner.nextLine();
        while (!ChoiceChecker.isOneOrTwo(choiceBetweenPackageOrNormal)) {
            logger.info("Enter Valid Choice: ");
            choiceBetweenPackageOrNormal =scanner.nextLine();
        }
        if(choiceBetweenPackageOrNormal.equals("2")) {
            list = serviceList("1");
        }
        else if(choiceBetweenPackageOrNormal.equals("1")){
            MenusPrinter.printServicesMenu();
            logger.info("Enter Number of Services (Greater Than One): ");
            String numberOfServices= scanner.nextLine();
            while(!ChoiceChecker.isValidNumberOfServices(numberOfServices)){
                logger.info("Enter A Valid Number of Services Greater Than One: ");
                numberOfServices= scanner.nextLine();
            }
            list = serviceList(numberOfServices);
        }
        try {
            SignUp.signUpServiceProvider(primaryInfo.getName()
                    , primaryInfo.getAddress()
                    , primaryInfo.getAuthentication()
                    , primaryInfo.getContactInfo()
                    , list);
        }
        catch (Exception e){
logger.warning(e.getMessage());
        }
    }


    public static void signUpAsUserView(){
        Person primaryInfo=readPrimaryInfo();
        try
        {
            SignUp.signUpUser(primaryInfo.getName()
                    ,primaryInfo.getAddress()
                    ,primaryInfo.getAuthentication()
                    ,primaryInfo.getContactInfo()
            );
        }
        catch (Exception e){
            logger.warning(e.getMessage());
        }
    }

    private static Person readPrimaryInfo(){
        logger.info("Enter first name: ");
        String fname=scanner.nextLine();
        logger.info("Enter middle name: ");
        String mname=scanner.nextLine();
        logger.info("Enter last name: ");
        String lname=scanner.nextLine();
        logger.info("Enter Country: ");
        String country=scanner.nextLine();
        logger.info("Enter city: ");
        String city=scanner.nextLine();
        logger.info("Enter email: ");
        String email=scanner.nextLine();
        logger.info("Enter password: ");
        String password=scanner.nextLine();
        while (!PasswordChecker.isStrongPassword(password)) {
            logger.info("re enter password: ");
            password=scanner.nextLine();
        }
        logger.info("Enter phone number: ");
        String phone=scanner.nextLine();

        return new Person(new Name(fname,mname,lname)
                ,new Authentication(email,password)
                ,new Address(country,city)
                ,new ContactInfo(email,phone)
                );
    }
    public static List<Service> serviceList(String numberOfService)
    {
        List <Service> list = new ArrayList<>();
        for(int i =0;i<Integer.parseInt(numberOfService);i++)
        {

        MenusPrinter.printServicesMenu();
            logger.info("Enter The Number Of The Service You Want To Provide : ");

            String servicenum=scanner.nextLine();
            while (!ChoiceChecker.isValidServiceIndex(servicenum)){
                 servicenum=scanner.nextLine();
                logger.info("Enter Valid Number For The Service : ");

            }
        ServiceType serviceType = switch (servicenum) {
            case "1" -> ServiceType.DJ;
            case "2" -> ServiceType.Photography;
            case "3" -> ServiceType.Security;
            case "4" -> ServiceType.Cleaning;
            case "5" -> ServiceType.Decor_and_Design;
            case "6" -> ServiceType.Catering;
            case "7" -> ServiceType.Venue;
            default -> null;
        };
        logger.info("Enter price for this service: ");
        double price=Double.parseDouble(scanner.nextLine());
        logger.info("description about the service: ");
        String discription=scanner.nextLine();
        list.add(new Service(serviceType,price,discription));
    }
        return list;
    }
}
