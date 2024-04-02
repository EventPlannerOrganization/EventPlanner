Feature: Reset Password

  Background: testing now
    Given Data Base is already filled


  Scenario Outline: Reset Successfully
    When username is  <username>
    And newPassword is <newPassword>
    Then Reset Password succssefull
    Examples:
      | username   | newPassword   |
      | 'Naser'    | 'Baaha12345#' |
      | 'khalid'   | 'omarA13&2'   |
      | 'Mariam03' | 'BBaaua1321$' |