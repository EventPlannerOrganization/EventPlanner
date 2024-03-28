Feature: delete Event by Admin
  Background: testing now

  Scenario Outline: admin try to delete event
    Given Data Base is already filled
    When admin enter Event Name  <eventName>
    Then deleting will complete successfully
    Examples:
      | eventName  |
      |"open day1"|