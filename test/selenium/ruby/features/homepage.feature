@HomePageTest
Feature: Message of the Day Homepage

  Scenario: Homepage displays default message of the day
    Given the user is on the message of the day homepage
    Then the user sees the message of the day

  @Smoke
  @HomePage @Past @Days @Message
  Scenario: Homepage allows display of message for previous days
    Given the user is on the message of the day homepage
    When the user enters a previous date that is 02/03/2002
    And the user clicks the get message button
    Then the user sees the message of the day for a Sunday

  Scenario: Homepage allows display of message for previous days
    Given the user is on the message of the day homepage
    When the user enters a previous date that is 02/04/2002
    And the user clicks the get message button
    Then the user sees the message of the day for a Monday
