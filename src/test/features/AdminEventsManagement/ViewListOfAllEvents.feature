Feature: view list of all events in the system
  Background: testing now

    Scenario: system does not have events
      Then there is no events to print

    Scenario: system contains events
      When Data Base is already filled
      Then list of all events and their dates will print
