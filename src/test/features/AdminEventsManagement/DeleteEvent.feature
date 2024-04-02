Feature: delete Event by Admin
  Background: testing now


  Scenario Outline: admin try to delete event
    Given Data Base is already filled
    When admin enter Event Name  <eventName>
    Then deleting will complete successfully
    Examples:
      | eventName  |
      |"open day1"|


  Scenario Outline: admin try to delete event does not exist in any user
    Given Data Base is already filled
    When admin enter Event Name  <eventName>
    Then deleting will not complete
    Examples:
      | eventName |
      | "vefvef"  |

