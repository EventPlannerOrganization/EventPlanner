package testControllers.AdminUserManagement;

import Exceptions.EventAlreadyExist;
import Exceptions.UserNotFoundException;
import controllers.AdminControl;
import controllers.EventsControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.User;

import java.util.List;

import static controllers.AdminControl.deleteUser;
import static models.EventPlanner.getUserByUsername;
import static org.junit.Assert.assertThrows;

public class TestDeleteUser {

    private String deletedUsername;

    @When("admin enter username  {string}")
    public void adminEnterUsername(String string) {
        // Write code here that turns the phrase above into concrete actions
    deletedUsername=string;
    }
    @Then("deleteing will not be complete")
    public void deleteingWillNotBeComplete() throws UserNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        //User deletedUser =(User)getUserByUsername(deletedUsername);
        //deleteUser(deletedUser);
        //User deletedUser
        assertThrows(UserNotFoundException.class, () -> getUserByUsername(deletedUsername));

    }
    @Then("user will be deleted successfully")
    public void userWillBeDeletedSuccessfully() throws UserNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        List<User> usersBeforeDeleteing = EventPlanner.getUsers();
        User deletedUser =(User)getUserByUsername(deletedUsername);
        deleteUser(deletedUser);
        List<User> usersAfterDeleteing = EventPlanner.getUsers();
        boolean condition=(usersBeforeDeleteing.contains(deletedUser)
                &&!usersAfterDeleteing.contains(deletedUser)
                &&usersBeforeDeleteing.size()==usersAfterDeleteing.size()+1
                );
        assert (condition);
    }}
