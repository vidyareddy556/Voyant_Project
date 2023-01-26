Feature: Add Pre Retiremental Goal for Client
  Background:
    Given User is logged with username: "voyant-exercise" and password: "password100"

  @PreRetirementGoal
  Scenario: Test to add Pre Retiremental Goal for a Client
    When User clicks Pre-Retiremental Goal Button
    And  Enters PreRName "Pre-Retirment car" Amount "120000" TaxDeductible "No"
    And Submits the form
    Then User should be able to create a New Pre-Retiremental goal and navigate to Dashboard