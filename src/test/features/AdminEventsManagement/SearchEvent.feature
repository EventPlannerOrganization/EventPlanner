Feature: Search event by searchTerm
  Background: testing now
    Given Data Base is already filled
    Scenario Outline: there is search result for the entered searchTerm
      When the entered searchTerm is <searchTerm>
      And there is related events with searchTerm with name <searchEventResult> and date <searchEventDate> and location <searchEventLocation>
      Then search success and pring result
      Examples:

        | searchTerm | searchEventResult             | searchEventDate         | searchEventLocation     |
        | 'Op'       | 'open day1'                   | '2024-08-10'            | 'Tulkarm Terrace'       |
        | 'we'       | 'wedding party,wedding party' | '2024-04-10,2024-08-10' | 'Birzeit Ballroom/null' |


  Scenario Outline: there is no matches result
    When the entered searchTerm is <searchTerm>
    Then there is no results match the searchTerm
    Examples:

      | searchTerm |
      | 'dsfdsf'   |
      | 'gefgwwf'  |