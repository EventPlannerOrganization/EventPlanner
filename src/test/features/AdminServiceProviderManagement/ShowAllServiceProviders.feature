Feature:Show All Service Provider in The System
  Background: testing now

  Scenario: System Does Not Contains Service Providers
    Then There is no users to Display

  Scenario: Show All The Service Provider Successfully
    When Data Base already filled
    Then All The Service Provider Will Appear Successfuly



