package testControllers.AdminUserManagement;

import Exceptions.UserNotFoundException;
import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.User;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestResetPasswordForUser {
    User user;
    String newPassword;
    @When("User who's we Want to Change His Password is {string}")
    public void userWhoSWeWantToChangeHisPasswordIs(String string) throws UserNotFoundException {
        user= (User) EventPlanner.getUserByUsername(string);

    }
    @When("new Password is {string}")
    public void newPasswordIs(String string) {
       newPassword = string;
    }
    @Then("Password will Change Successfuly")
    public void passwordWillChangeSuccessfuly() throws IOException, MessagingException {
        String oldPassword = user.getAuthentication().getPassword();
        AdminControl.resetPassword(user,newPassword);
        assert(user.getAuthentication().getPassword().equals(newPassword));
        assert (!user.getAuthentication().getPassword().equals(oldPassword));

    }

}
