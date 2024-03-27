Feature: Show Registerd Events For User

  Scenario Outline: Display Event Names And Dates Successfuly
    Given Data Base is already filled
    When Admin Want To Display Events for User <username>
    And User Has <eventName><EventDate>
    Then The User Events Will Display Correctly
    Examples:
    |username|eventName|EventDate|
    |"Naser"   |"open day1,Wedding Celebration"|"2024-08-10,2024-09-10"|
    |"Nassar"  |"wedding party"                |  "2024-08-10"|