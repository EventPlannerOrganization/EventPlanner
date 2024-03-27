Feature: Search Service Provider by UserName
  Background: testing now
  Scenario Outline: Display Services For Service Provider Successfully
    Given Data Base is already filled
    When Admin Want To Display Services For ServiceProvider <username>
    And Service Provider has <serviceType> <ServicePrice> <ServiceDescription>
    Then Services Will Display Successfully
    Examples:
      |  username  | serviceType |ServicePrice|ServiceDescription|
      |"mohammad03"|      "DJ"   |    "3200"   |   "tesing"       |
      |  "hamid02" |  "Cleaning" |    "3200"    |  "tesing"  |