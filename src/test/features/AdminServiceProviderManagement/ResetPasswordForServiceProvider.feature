Feature: Reset Password For Service Provider

  Scenario Outline: Reset Password Successfuly
    When ServiceProvider who's Want to Change His Password is <username>
    And new Password is <newPassword>
    Then Password will Change Successfuly
    Examples:
      | username     | newPassword   |
      | "mohammad03" |  'Mooo123@'   |
