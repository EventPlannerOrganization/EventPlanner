package helpers;

public class ChoiceChecker {
    private ChoiceChecker(){

    }
    public static boolean isOneOrTwo(String value){
       return value.equals("1")||value.equals("2");
    }

//    public static boolean isMenuChoice() {
//
//    }

    public static boolean isValidNumberOfServices(String value) {
        return value.equals("2")||value.equals("3")||value.equals("4");
    }

    public static boolean UserMenuChecker(String value) {
        return value.equals("1")||value.equals("2")||value.equals("3")||value.equals("4");

    }
}
