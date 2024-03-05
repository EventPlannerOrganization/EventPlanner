Feature: AddEvent
  Background: testing now
    Given Data Base is already filled

  Scenario Outline: Add Success
    When current user who wants to add an event is <username>
    And added event information are <date> <eventName> <serviceProviders> <guestsEmails>
    Then event will be added successfully

    Examples:
      | username | date        | eventName | serviceProviders      | guestsEmails                                   |
      | 'Naser'  | '17/8/2024' | 'Openday' | 'mohammad03','baha02' | '3sfr3sfr@gmail.com','s12113028@stu.najah.edu' |
