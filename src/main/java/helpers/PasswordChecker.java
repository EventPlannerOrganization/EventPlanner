package helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PasswordChecker {
    private PasswordChecker() {

    }

    public static boolean isStrongPassword(String password) {
        return isAcceptableLength(password)
                && containsSmallLetters(password)
                && containsCapitalLetters(password)
                && containsDigits(password)
                && containsSpecialCharacters(password);
    }

    private static boolean isAcceptableLength(String password) {
        return password.length() >= 8;
    }

    private static boolean containsSmallLetters(String password) {
                for(char ch : password.toCharArray()) {
                    if(ch >= 'a' &&  ch <= 'z')
                        return true;
        }
        return false;
    }

    private static boolean containsCapitalLetters(String password) {
        for(char ch : password.toCharArray()) {
            if(ch >= 'A' &&  ch <= 'Z')
                return true;
        }
        return false;
    }

    private static boolean containsDigits(String password) {
        for(char ch : password.toCharArray()) {
            if(ch >= '0' &&  ch <= '9')
                return true;
        }
        return false;
    }

    private static boolean containsSpecialCharacters(String password) {
        String specialChars = "+-/*=-_!@#$%^&~(){}.\\';";
        for(char chIteratorForSpecialChars : specialChars.toCharArray()) {
            for(char chIteratorForPassword : password.toCharArray()) {
                if(chIteratorForPassword == chIteratorForSpecialChars)
                    return true;
            }
        }
        return false;
    }





    public static String mergeThreeStrings(String str1,String str2,String str3){
        StringBuilder string1=new StringBuilder(str1);

        // Check if x is shorter than 20 characters
        if (string1.length() < 25) {
            // Calculate the number of spaces needed to reach 20 characters
            int spacesNeeded = 25 - string1.length();
            // Append spaces to x to make it 20 characters long
            string1.append(" ".repeat(Math.max(0, spacesNeeded)));
        }
        string1= new StringBuilder((string1.substring(0, 24) + str2));
        if (string1.length() < 50) {
            // Calculate the number of spaces needed to reach 20 characters
            int spacesNeeded = 50 - string1.length();
            // Append spaces to x to make it 20 characters long
            string1.append(" ".repeat(Math.max(0, spacesNeeded)));
        }
        // Append "hi hi hi hi " to x
        return string1.substring(0, 49) + str3;

    }

    public static boolean listsContainSameElements(List<String> list1, List<String> list2) {
        // Convert lists to sets
        Set<String> set1 = new HashSet<>(list1);
        Set<String> set2 = new HashSet<>(list2);

        // Check if sets are equal
        return set1.equals(set2);
    }
}
