Feature: Login

  Background: testing now
    Given Data Base is already filled


  Scenario Outline: Login successfully
    When username is <username>
    And password is <password>
    Then login successfully
    Examples:
      | username       | password |
      | 'Naser'        | 'm123'   |
      | 'baha'         | '0000'   |
      | 'baha alawneh' | 'bbaa12' |

  Scenario Outline: Login fails because the user is not exist
    When username is <username>
    And password is <password>
    Then Login fails and User Not Found Exception will be thrown
    Examples:
      | username         | password  |
      | 'omar ala'       | 'omar12'  |
      | 'mohammed saaaa' | 'bhasd21' |

  Scenario Outline: Login fails because the password is wrong
    When username is <username>
    And password is <password>
    Then Login fails because of the wrong password
    Examples:
      | username       | password   |
      | 'baha alawneh' | 'bgg10012' |




