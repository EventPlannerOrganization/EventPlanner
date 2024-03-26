Feature:ShowAllUsers
  Background: testing now

  Scenario: system does not contain users
    Then there is no users to print

    Scenario: system contains users
      When Data Base is already filled
      Then printAllUsers

