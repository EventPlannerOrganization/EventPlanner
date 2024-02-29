package launchers;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import views.SignUpView;

public class Main {
    public static void main(String[] args) throws UserIsAlreadyExist, WeakPasswordException {
        SignUpView.signUpView();
    }
}