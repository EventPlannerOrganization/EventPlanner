package helpers;

public class ChoiceChecker {
    private ChoiceChecker(){

    }
    public static boolean isOneOrTwo(String value){
       return value.equals("1")||value.equals("2");
    }



    public static boolean isValidNumberOfServices(String value) {
        return value.equals("2")||value.equals("3")||value.equals("4");
    }

    public static boolean userMenuChecker(String value) {
        return value.equals("1")||value.equals("2")||value.equals("3")||value.equals("4");

    }
}
