Feature: Update Service Price

  Background: testing
    Given Data Base already filled

  Scenario Outline: Update Service Price Successfully
    When Service Provider Who's Want to Change His Service Price is <userName>
    And Want To Change The Price To <newPrice>
    Then The Price Will Be Updated Successfully

    Examples:
      | userName  | newPrice |
      | "hamid02" | "1000" |
      | "aliii" | "2000" |