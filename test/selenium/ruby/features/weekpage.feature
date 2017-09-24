@WeekTest
Feature: Message of the Day Week Page

  Scenario: Message of the Week page displays all seven days
    Given the user is on the message of the day week page
    Then confirm we see the following days
      | dayOfWeek |
      | Sunday    |
      | Monday    |
      | Tuesday   |
      | Wednesday |
      | Thursday  |
      | Friday    |
      | Saturday  |
