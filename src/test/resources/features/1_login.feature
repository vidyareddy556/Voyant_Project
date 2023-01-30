Feature: Login to Voyant Application

  Background:
    Given User is on Voyant Login page "https://ca-test.planwithvoyant.com/advisergo/"
    #And I take a screenshot "login screenshot"

  @ValidCredentials
  Scenario: Login with valid credentials
    When User enters username as "voyant-exercise" and password as "usefulpassword"
    Then User should be able to login sucessfully

  @ignore
  Scenario Outline: Login with invalid credentials
    When User enters username as <userName> and password as <passWord>
    Then User login should fail
    Examples:
      | userName          | passWord      |
      | "voyant-exercise" | "somepass"    |
      | "testuser"        | "somepass"    |
