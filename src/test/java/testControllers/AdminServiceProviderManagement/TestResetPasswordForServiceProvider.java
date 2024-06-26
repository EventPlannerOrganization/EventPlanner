package testControllers.AdminServiceProviderManagement;

import exceptions.UserNotFoundException;
import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.ServiceProvider;

import javax.mail.MessagingException;
import java.io.IOException;

public class TestResetPasswordForServiceProvider {
    ServiceProvider serviceProvider;
    String newPassword;
    @When("ServiceProvider who's Want to Change His Password is {string}")
    public void serviceProviderWhoSWantToChangeHisPasswordIs(String string) throws UserNotFoundException {
      serviceProvider= (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);
    }
    @When("new Password is {string}")
    public void newPasswordIs(String string) {
    newPassword= string;
    }
    @Then("Password will Change Successfuly")
    public void passwordWillChangeSuccessfuly() throws IOException, MessagingException {
        String oldPassword = serviceProvider.getAuthentication().getPassword();
        AdminControl.resetPassword(serviceProvider,newPassword);
        assert(serviceProvider.getAuthentication().getPassword().equals(newPassword));
        assert (!serviceProvider.getAuthentication().getPassword().equals(oldPassword));


    }

}
