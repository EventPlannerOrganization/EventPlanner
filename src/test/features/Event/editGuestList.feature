Feature: editGuestList
  Background: testing now
    Given Data Base is already filled

  Scenario Outline: add guest email
    When current user is <username>
    And event name is <eventName>
    And guset emails that the user wants to add is <guestsEmails>
    Then emails will be added successfylly

    Examples:
      | username | eventName             | guestsEmails                               |
      | 'Naser'  | 'open day1'           | 'baha@gmail.com','s12112925@stu.najah.edu' |
      | 'Naser'  | 'Wedding Celebration' | 'rami@gmail.com','loay@stu.najah.edu'      |

  Scenario Outline: delete guest emails
    When current user is <username>
    And event name is <eventName>
    And guset emails that the user wants delete is <guestsEmails>
    Then emails will be deleted successfylly

    Examples:
      | username | eventName             | guestsEmails                                    |
      | 'Naser'  | 'Wedding Celebration' | '3sfr3sfr@gmail.com','s12113028@stu.najah.edu'  |
      | 'Naser'  | 'open day1'           | 'bahaalawneh07@gmail.com','s12112925@gmail.com' |
