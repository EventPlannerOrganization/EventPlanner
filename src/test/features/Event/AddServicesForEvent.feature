Feature: Add Service To Event
  Background: Testing
    Given Data Base is already filled


  Scenario Outline:Add Services success
    When current user who wants to add services to an event is <username>
    And the eventName   is <eventName>
    And the service Providers who offer these services are <providersNames>
    Then services will be added successfully

    Examples:
      | username | eventName             | providersNames          |
      | 'khalid' | 'wedding party'       | 'Ibrahim160','saleem04' |
      | 'Naser'  | 'Wedding Celebration' | 'Ibrahim160','saleem04' |

  Scenario Outline: Add Failed Due to there is a service already Scheduled in this event date
    When current user who wants to add services to an event is <username>
    And the eventName   is <eventName>
    And the service Providers who offer these services are <providersNames>
    Then services will be added successfully

    Examples:
      | username | eventName             | providersNames          |
      | 'khalid' | 'wedding party'       | 'Ibrahim160','saleem04' |
      | 'Naser'  | 'Wedding Celebration' | 'Ibrahim160','saleem04' |


