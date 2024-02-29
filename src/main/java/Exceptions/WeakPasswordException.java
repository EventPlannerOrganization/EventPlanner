package Exceptions;

public class WeakPasswordException extends Exception {
    public WeakPasswordException() {
        super("Enter a Stronger password");
    }
}
