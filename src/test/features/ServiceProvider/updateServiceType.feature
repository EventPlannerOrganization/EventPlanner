Feature: update event information

  Background: testing
    Given Data Base already filled


  Scenario Outline:update service type info success
    When serivce provider  who wants to update its service type is <username>
    And the value of updating the type is <newType>
    Then the service type will be updated successfully
    Examples:
      | username     | newType    |
      | 'mohammad03' | 'Cleaning' |
      | 'baha02'     | 'DJ'       |


  Scenario Outline:update service type info fail
    When serivce provider  who wants to update its service type is <username>
    And the value of updating the type is <newType>
    Then the service type update will be fail
    Examples:
      | username     | newType    |
      | 'mohammad03' | 'Cleaning' |
      | 'baha02'     | 'DJ'       |
