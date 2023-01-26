Feature: Add income for Client
  Background:
    Given User is logged with username: "voyant-exercise" and password: "password100"

  @PreRetirementGoal
  Scenario: Test to add income for a Client
    When User clicks income Button for a client
    And  clicks Employment Button
    And  Enters Employment Information

      | employmentInputName        | google |
      | Source                     | Employed |
      | Salary                     | 150000  |
      | BonusesOrCommissions      | 15000   |
      | TaxableEmployeeBenefits  | 5000    |
    And  Submits the Employment details Form
    Then User should be able to successfully add Income and navigate to Dashboard
