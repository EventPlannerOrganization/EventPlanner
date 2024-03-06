Feature: update event information

  Background: testing
    Given Data Base already filled


  Scenario Outline:update event info success
    When current user who wants to update the event info is <username>
    And the eventName is <eventName>
    And the field of updating the event is <field>
    And the value of updating the event is <value>
    Then the event information will be updated successfully
    Examples:
      | username | eventName       | field      | value           |
      | 'Naser'  | 'open day1'      | 'name'     | 'wedding party' |
      | 'khalid' | 'wedding party' | 'location' | 'jenin'         |


  Scenario Outline: update info fail
    When current user who wants to update the event info is <username>
    And the eventName is <eventName>
    And the field of updating the event is <field>
    And the value of updating the event is <value>
    Then the event infromation upadte will fail and Event Not Exist Exception will be thrown
    Examples:
      | username | eventName        | field      | value           |
      | 'Nassar' | 'open day'       | 'name'     | 'wedding party' |
      | 'Karim'  | 'birthday party' | 'location' | 'nablus'        |
