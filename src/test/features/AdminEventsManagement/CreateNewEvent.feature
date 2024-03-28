Feature: create new event for spicific user
  Background: testing now
    Given Data Base is already filled

    Scenario Outline: creating done successfully
      When event name is  <eventName>
      And user who will added event to him <username>
      And event date is <date>
      And the list of guests <guestsList>
      And the list service Providers names <serviceProvidersNames>
      Then added event successfully
      Examples:
        | eventName   | username | date        | guestsList                                   | serviceProvidersNames |
        | 'Open Day5' | 'Naser'  | '17/2/2026' | '3sfr3sfr@gmail.com,s12113028@stu.najah.edu' | 'mohammad03,baha02'   |

  Scenario Outline: creating field because there is another event with same name
    When event name is  <eventName>
    And user who will added event to him <username>
    And event date is <date>
    And the list of guests <guestsList>
    And the list service Providers names <serviceProvidersNames>
    Then event will not be added
    Examples:
      | eventName   | username | date        | guestsList                                   | serviceProvidersNames |
      | 'Wedding Celebration' | 'Naser'  | '17/2/2026' | '3sfr3sfr@gmail.com,s12113028@stu.najah.edu' | 'mohammad03,baha02'   |

