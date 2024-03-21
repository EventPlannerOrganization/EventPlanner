Feature: Update event information

  Background: testing
    Given Data Base already filled

  Scenario Outline: Update Service Description Successfully
    When Service Provider Who's Want to Change His Service Description is <userName>
    And Want To Change The Description To <newDescription>
    Then The Description Will Be Updated Successfully

    Examples:
      | userName  | newDescription |
      | "hamid02" | "Best Service" |
      | "aliii" | "Good Service" |