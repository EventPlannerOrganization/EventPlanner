Feature:Show BookedDate For ServiceProvider
  Background: testing now
    Scenario Outline: Display BookedDate Successfuly
      Given Data Base already filled
      When The Admin Want to Display Booked Date For service Provider <username>
      And Service Provider has bookedDate <date>
      Then The bookedDate Will Appear Correctly
      Examples:
      |username|date|
      |"mohammad03"|"2024-08-10"|
