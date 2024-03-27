Feature: Reset Password User

  Scenario Outline: Reset Password Successfuly
    Given Data Base is already filled
    When User who's we Want to Change His Password is <username>
    And new Password is <newPassword>
    Then Password will Change Successfuly
    Examples:
      | username     | newPassword   |
      | "Naser"      |  'Nass123@'   |
