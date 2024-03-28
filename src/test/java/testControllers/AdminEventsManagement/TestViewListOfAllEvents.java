package testControllers.AdminEventsManagement;

import Exceptions.UserIsAlreadyExist;
import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;

import java.util.ArrayList;
import java.util.List;

import static controllers.AdminControl.getAllEventsNames;
import static helpers.PasswordChecker.mergeTwoStrings;

public class TestViewListOfAllEvents {




    @Then("there is no events to print")
    public void thereIsNoEventsToPrint() {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.cleanRepositry();
        assert (getAllEventsNames().isEmpty());
    }


    @When("Data Base is already filled")
    public void dataBaseIsAlreadyFilled() throws UserIsAlreadyExist {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.initializeRepositoryWithData();
        }
    @Then("list of all events and their dates will print")
    public void listOfAllEventsAndTheirDatesWillPrint() {
        // Write code here that turns the phrase above into concrete actions
        String []names={"wedding party","Birthday Bash","Food Festival","open day1","wedding party","Wedding Celebration"};
        String [] dates={"2024-04-10","2024-04-10","2024-04-10","2024-08-10","2024-08-10","2024-09-10",};
        List<String> expectedOutput=new ArrayList<>();
        for(int i=0;i<names.length;i++){
            expectedOutput.add(mergeTwoStrings(names[i],dates[i]));
        }
        List<String> actualOutput=getAllEventsNames();

        assert(expectedOutput.containsAll(actualOutput)&&actualOutput.containsAll(expectedOutput));
    }
}
