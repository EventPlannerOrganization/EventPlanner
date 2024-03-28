Feature: Search event by searchTerm
  Background: testing now

    Scenario Outline: there is search result for the entered searchTerm
      When the entered searchTerm is <searchTerm>
      And there is related events with searchTerm with name <searchEventResult> and date <searchEventDate>
      Then search success and pring result
      Examples:

        | searchTerm | searchEventResult             | searchEventDate         |
        | 'Op'       | 'open day1'                   | '2024-08-10'            |
        | 'we'       | 'wedding party,wedding party' | '2024-04-10,2024-08-10' |


  Scenario Outline: there is search result for the entered searchTerm
    When the entered searchTerm is <searchTerm>
    Then there is no results match the searchTerm
    Examples:

      | searchTerm |
      | 'dsfdsf'   |
      | 'gefgwwf'  |