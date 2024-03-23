Feature: AddEvent

  Background: testing now
    Given Data Base is already filled

  Scenario Outline: Add Success
    When current user who wants to add an event is <username>
    And added event information are <date> <eventName> <serviceProviders> <guestsEmails>
    And service provider accept the request
    Then event will be added successfully and requset will be send the service provider

    Examples:
      | username | date        | eventName          | serviceProviders      | guestsEmails                                   |
      | 'Naser'  | '17/8/2024' | 'Openday'          | 'mohammad03','baha02' | '3sfr3sfr@gmail.com','s12113028@stu.najah.edu' |
      | 'Naser'  | '12/5/2024' | 'Graduation Party' | 'mohammad03','baha02' | '3sfr3sfr@gmail.com','s12113028@stu.najah.edu' |
      | 'Karim'  | '12/8/2024' | 'Openday'          | 'mohammad03','baha02' | '3sfr3sfr@gmail.com','s12113028@stu.najah.edu' |

  Scenario Outline: Add Failed Due to Already Found Event with same Name for same user
    When current user who wants to add an event is <username>
    And added event information are <date> <eventName> <serviceProviders> <guestsEmails>
    Then event will not be added successfully

    Examples:
      | username | date        | eventName             | serviceProviders      | guestsEmails                                   |
      | 'Naser'  | '10/9/2024' | 'Wedding Celebration' | 'mohammad03','baha02' | '3sfr3sfr@gmail.com','s12113028@stu.najah.edu' |