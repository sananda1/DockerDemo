@HomePageTest
Feature: Message of the Day Homepage as a Scenario Outline

  Scenario Outline: Homepage allows display of message for previous days
    Given the user is on the message of the day homepage
    When the user enters a previous date that is <date>
    And the user clicks the get message button
    Then the user sees the message of the day for a <dayOfWeek>
  Examples:
    | date | dayOfWeek |
    | 02/03/2002 | Sunday |
    | 02/04/2002 | Monday |
    | 02/05/2002 | Tuesday |
    | 02/06/2002 | Wednesday |
