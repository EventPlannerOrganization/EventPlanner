Feature: Search user by user name
  Background:  testing now


    Scenario Outline: system does not contain users with related username to input
      Given Data Base is already filled
      When admin enter username <searchInput>
      Then there is no users related with input string
      Examples:
        | searchInput |
        | 'jumana'    |
        | 'yaseen'    |
        | 'fatima'    |
        | 'ayh'       |

      Scenario Outline: system contains users with related username to input
        Given Data Base is already filled
        When admin enter username <searchInput>
        Then list of related usernames will appear <resultNames>

        Examples:
          | searchInput | resultNames    |
          | 'Na'        | 'Naser,Nassar' |
          | 'Naser'     | 'Naser'        |
          | 'nAsEr'     | 'Naser'        |
          | 'Karim'     | 'Karim'        |
          | 'khalid'    | 'khalid'       |
