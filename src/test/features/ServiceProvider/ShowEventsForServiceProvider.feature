Feature: Show Events For Service Provider And Package Provider
  Background: testing
    Given Data Base already filled2
    Scenario Outline: Show Events That Service Provider Has
      When Service Provider how's want to Show Events is <username>
      Then The Events Will Display Correctly
      Examples:
      |username|
      |"moshadid" |
  Scenario Outline: Show Upcoming Events That Service Provider Has
    When Service Provider how's want to Show upcoming Events is <username>
    Then The Upcoming Events Will Display Correctly
    Examples:
    |username|
    |"moshadid"|