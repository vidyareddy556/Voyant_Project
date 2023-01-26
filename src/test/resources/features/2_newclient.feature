Feature: Create New Client22
  Background:
    Given User is logged with username: "voyant-exercise" and password: "password100"
  @NewClient
  Scenario: Test New Client With Valid Values
    When User clicks create New Client form
    And Enter New client form details
      | FirstName                  | Phil   |
      | LastName                   | Murray  |
      | BirthYear                  | 1985    |
      | MonthOfPlan                | 01      |
      | DateOfPlan                 | 01      |
      | YearOfPlan                 | 2023    |
      | Province                   | Alberta |
      | isRetired                  | No      |
      | RetirementAge              | 65      |
    And When User submits form
    Then User should be able to create a New Client Record

