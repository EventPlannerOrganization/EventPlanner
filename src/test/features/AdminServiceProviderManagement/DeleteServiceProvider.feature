Feature: delete Service Provider by admin
  Background: testing now

  Scenario Outline: admin try to delete Service Provider does not exist
    Given Data Base is already filled
    When admin enter username  <username>
    Then deleteing will not be complete
    Examples:
      | username  |
      | 'samir'   |
      | 'sohib'   |
      | 'efvevev' |

  Scenario Outline: successfully deleteing exist Service Provider
    Given Data Base is already filled
    When admin enter username  <username>
    Then Service Provider will be deleted successfully
    Examples:
      | username |
      | 'mohammad03' |
      | 'baha02'  |
      | 'Ibrahim160'  |