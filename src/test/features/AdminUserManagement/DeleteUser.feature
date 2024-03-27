Feature: delete user by admin
  Background: testing now

    Scenario Outline: admin try to delete user does not exist
      Given Data Base is already filled
      When admin enter username  <username>
      Then deleteing will not be complete
      Examples:
        | username  |
        | 'samir'   |
        | 'sohib'   |
        | 'efvevev' |

  Scenario Outline: successfully deleteing exist user
    Given Data Base is already filled
    When admin enter username  <username>
    Then user will be deleted successfully
    Examples:
      | username |
      | 'khalid' |
      | 'Karim'  |
      | 'Naser'  |