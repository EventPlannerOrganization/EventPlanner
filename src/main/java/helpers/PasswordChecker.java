package helpers;


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

}
