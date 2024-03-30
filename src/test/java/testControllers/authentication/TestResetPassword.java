package testControllers.authentication;

import exceptions.UserNotFoundException;
import controllers.ResetPasswordController;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;

public class TestResetPassword {
    private String username;
    private String newPasword;
    @When("username is  {string}")
    public void usernameIs(String string) {
        this.username=string;
    }

    @When("newPassword is {string}")
    public void newPasswordIs(String string) {
        this.newPasword=string;
    }
    @Then("Reset Password succssefull")
    public void resetPasswordSuccssefull() throws UserNotFoundException {
        ResetPasswordController.changePasswordForUser(EventPlanner.getUserByUsername(username),newPasword);
        assert (EventPlanner.getUserByUsername(username).getAuthentication().getPassword().equals(newPasword));
    }
}
