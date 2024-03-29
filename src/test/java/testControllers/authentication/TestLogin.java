package testControllers.authentication;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import controllers.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import static org.junit.Assert.*;


public class TestLogin {
    private String username;
    private String password;

    @Given("Data Base is already filled")
    public void dataBaseisAlreadyFilled() throws UserIsAlreadyExist {
        EventPlanner.initializeRepositoryWithData();
    }

    @When("username is {string}")
    public void usernameIs(String string) {
       this.username=string;
    }
    @When("password is {string}")
    public void password_is(String string) {
        this.password=string;
    }
    @Then("login successfully")
    public void loginSuccessfully() throws UserNotFoundException {

        assertTrue(Login.canLogin(username, password));

        assertNotNull(EventPlanner.getCurrentUser());
    }
    @Then("Login fails and User Not Found Exception will be thrown")
    public void loginFailsAndUserNotFoundExceptionWillBeThrown() {
        assertThrows(UserNotFoundException.class,() ->Login.canLogin(username,password));
    }
    @Then("Login fails because of the wrong password")
    public void loginFailsBecauseOfTheWrongPassword() throws UserNotFoundException {
        assertFalse(Login.canLogin(username, password));
    }


}