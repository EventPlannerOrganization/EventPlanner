Feature: Show Services For Service Provider And Package Provider
  Background: testing
    Given Data Base already filled
    Scenario Outline: Show ServiceProvider Services Succesffuly
      When Service provider who is want to show his service is <username>
      Then The Service Will Display Correctly

      Examples:
        |username|
        | "hamid02"|

Scenario Outline: show PackageProviderServices Succesfully
  When PackageProvider who is want to show his services is <USERNAME>
  Then The Servicess Will Display Correctly
  Examples:
    |USERNAME|
    | "Ibrahim160"|