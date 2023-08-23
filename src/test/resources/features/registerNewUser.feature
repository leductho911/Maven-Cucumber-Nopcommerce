
Feature: Register New User
  As a user
  I want to be able to register a new account
  So that I can access the system

  @register_success
  Scenario Outline: Successful registration
    Given the user is on the registration page
    When the user input to "First name" textbox with value "<firstname>"
    And the user input to "Last name" textbox with value "<lastname>"
    And the user input to "Email" textbox with value "<email>"
    And the user input to "Password" textbox with value "<password>"
    And the user input to "Confirm password" textbox with value "<password>"
    When they click the "Register" button
    Then they should see Registration completed message appeared

    Examples:
      | firstname | lastname | email                | password   |
      | Ngoc      | Trang    | tester0022@example.com | Secret123  |