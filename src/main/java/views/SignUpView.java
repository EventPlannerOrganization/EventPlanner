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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpView {
    private static final Logger logger=Logger.getLogger(SignUpView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private SignUpView() {

    }
    public static void signUpView()  {
        MenusPrinter.printSignUpAsMenu();
        logger.info("Choose User Type :");
        String signUpAs=scanner.nextLine();
        while (!ChoiceChecker.isValidChoice(signUpAs,2)) {
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
        while (!ChoiceChecker.isValidChoice(choiceBetweenPackageOrNormal,2)) {
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
        logger.info("Enter User Name :");
        String userName=scanner.nextLine();
        logger.info("Enter Country: ");
        String country=scanner.nextLine();
        logger.info("Enter city: ");
        String city=scanner.nextLine();
        logger.info("Enter email: ");
        String email=scanner.nextLine();
        while (!isEmail(email)){
            logger.info("Invalid Email,please Enter valid Email : ");
            email=scanner.nextLine();
        }
        logger.info("Enter password: ");
        String password=scanner.nextLine();
        while (!PasswordChecker.isStrongPassword(password)) {
            logger.info("Weak Password , Please Enter a Strong Password (Contains letters ,Symbols,Numbers) :");
            password=scanner.nextLine();
        }
        logger.info("Enter phone number: ");
        String phone=scanner.nextLine();

        return new Person(new Name(fname,mname,lname)
                ,new Authentication(userName,password)
                ,new Address(country,city)
                ,new ContactInfo(email,phone)
                );
    }

    public static boolean isEmail(String email) {
        String emailPattern =
                new StringBuilder().append("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@").append("(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").toString();
        Pattern pattern = Pattern.compile(emailPattern);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static List<Service> serviceList(String numberOfService)
    {
        List <Service> list = new ArrayList<>();
        for(int i =0;i<Integer.parseInt(numberOfService);i++)
        {

        MenusPrinter.printServicesMenu();
            logger.info("Enter The Number Of The Service You Want To Provide : ");

            String servicenum=scanner.nextLine();
            while (!ChoiceChecker.isValidChoice(servicenum,7)){
                 servicenum=scanner.nextLine();
                logger.info("Enter Valid Number For The Service : ");

            }
        ServiceType serviceType = switch (servicenum) {
            case "1" -> ServiceType.DJ;
            case "2" -> ServiceType.PHOTOGRAPHY;
            case "3" -> ServiceType.SECURITY;
            case "4" -> ServiceType.CLEANING;
            case "5" -> ServiceType.DECOR_AND_DESIGN;
            case "6" -> ServiceType.CATERING;
            case "7" -> ServiceType.VENUE;
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
