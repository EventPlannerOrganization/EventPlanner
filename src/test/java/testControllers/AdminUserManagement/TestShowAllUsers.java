package testControllers.AdminUserManagement;

import Exceptions.UserIsAlreadyExist;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import java.util.ArrayList;
import java.util.List;
import static controllers.AdminControl.getAllUsers;
import static helpers.PasswordChecker.listsContainSameElements;
public class TestShowAllUsers {
    @Then("there is no users to print")
    public void thereIsNoUsersToPrint() {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.cleanRepositry();
        List<String> actualOutput = getAllUsers();
        assert (actualOutput.isEmpty());
    }


    @When("Data Base is already filled")
    public void dataBaseIsAlreadyFilled() throws UserIsAlreadyExist {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.initializeRepositoryWithData();
    }
    @Then("printAllUsers")
    public void printAllUsers() {
        // Write code here that turns the phrase above into concrete actions
        List<String> expectedOuput = new ArrayList<>(List.of(new String[]{"Naser", "khalid", "Karim", "Nassar","ramii"}));
        List<String> actualOutput =getAllUsers();
        assert(listsContainSameElements(expectedOuput,actualOutput));
    }



}
