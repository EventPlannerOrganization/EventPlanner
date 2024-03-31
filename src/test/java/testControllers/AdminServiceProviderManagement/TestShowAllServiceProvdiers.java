package testControllers.AdminServiceProviderManagement;

import Exceptions.UserIsAlreadyExist;
import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;

import java.util.ArrayList;
import java.util.List;
import static helpers.StringOperations.listsContainSameElements;

public class TestShowAllServiceProvdiers {
    @When("Data Base already filled")
    public void dataBaseAlreadyFilled() throws UserIsAlreadyExist {
        EventPlanner.initializeRepositoryWithData();

    }

    @Then("All The Service Provider Will Appear Successfuly")
    public void allTheServiceProviderWillAppearSuccessfuly() {
        List<String> expectedOuput = new ArrayList<>(List.of(new String[]{"mohammad03", "baha02", "Ibrahim160", "saleem04","hamid02","aliii"}));
        List<String> actualOutput =AdminControl.getAllServiceProviders();
        assert(listsContainSameElements(expectedOuput,actualOutput));
    }

    @Then("There is no users to Display")
    public void thereIsNoUsersToDisplay() {
        EventPlanner.cleanRepositry();
        List<String> actualOutput = AdminControl.getAllServiceProviders();
        assert (actualOutput.isEmpty());
    }
}