package testControllers.AdminEventsManagement;

import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.RegisteredEvent;

import java.util.ArrayList;
import java.util.List;

import static helpers.PasswordChecker.mergeTwoStrings;

public class TestSearchEvent {

    String searchTerm ;
    String []dates;
    String []names;

    @When("the entered searchTerm is {string}")
    public void theEnteredSearchTermIs(String string) {
        // Write code here that turns the phrase above into concrete actions
        searchTerm=string;
    }
    @When("there is related events with searchTerm with name {string} and date {string}")
    public void thereIsRelatedEventsWithSearchTermAnd(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    names =string.split(",");
    dates =string2.split(",");
    }
    @Then("search success and pring result")
    public void searchSuccessAndPringResult() {
    // Write code here that turns the phrase above into concrete actions.
    List<RegisteredEvent> events= AdminControl.searchEvents(searchTerm);
    assert(events!=null);
    List<String> expectedSearchResult=new ArrayList<>();
    for(int i=0;i<names.length;i++){
        expectedSearchResult.add(mergeTwoStrings(names[i],dates[i]));
    }
    List<String> actualSearchResult=AdminControl.getEventNameOfUsers(events);
    assert(actualSearchResult.containsAll(expectedSearchResult)&&expectedSearchResult.containsAll(actualSearchResult));

    }

    @Then("there is no results match the searchTerm")
    public void thereIsNoResultsMatchTheSearchTerm() {
        // Write code here that turns the phrase above into concrete actions
        List<RegisteredEvent> events= AdminControl.searchEvents(searchTerm);
        assert(events==null);
    }



}
