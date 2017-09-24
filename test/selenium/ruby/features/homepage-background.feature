@HomePageTest
Feature: Message of the Day Homepage

  Background:
    Given the user is on the message of the day homepage

  Scenario: Homepage displays default message of the day
    Then the user sees the message of the day

  Scenario: Homepage allows display of message for previous days
    When the user enters a previous date that is 02/03/2002
    And the user clicks the get message button
    Then the user sees the message of the day for a Sunday

  Scenario: Homepage allows display of message for previous days
    When the user enters a previous date that is 02/04/2002
    And the user clicks the get message button
    Then the user sees the message of the day for a Monday
