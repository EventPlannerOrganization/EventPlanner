Feature: Search Service Provider by UserName
  Background: testing now


  Scenario Outline: system does not contain ServiceProvider with related username to input
    Given Data Base is already filled
    When admin enter  Username <searchInput>
    Then there is no users related with input string
    Examples:
      | searchInput |
      | 'samer' |
      | 'mahmoud'|
      | 'aliaa'|
      | 'nermeen'|

  Scenario Outline: system contains users with related username to input
    Given Data Base is already filled
    When admin enter  Username <searchInput>
    Then list of related usernames will appear <resultNames>

    Examples:
      | searchInput |  resultNames    |
      | 'mo'        |  'mohammad03'   |
      | 'sal'       |  'saleem04'     |
      | 'ibra'      |  'Ibrahim160'   |
      | 'ali'       |  'aliii'        |
      | 'baha'      |  'baha02'       |
