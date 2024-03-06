Feature:Delete Service From Event
  Background: Testing
    Given Data Base already filled


  Scenario Outline:Delete Service success
    When current user who wants to delete service from an event is <username>
    And the eventName  is <eventName>
    And the service Provider who offer this service is <providerName>
    Then service will be deleted successfully

    Examples:
      | username | eventName             | providerName |
      | 'khalid' | 'wedding party'       | 'mohammad03' |
      | 'Naser'  | 'Wedding Celebration' | 'baha02'     |
