@register_login
Feature: User Registration and Login

  @register
  Scenario Outline: Successful registration
    Given the user is on the registration page
    When the user input to "First name" textbox with value "<firstname>"
    And the user input to "Last name" textbox with value "<lastname>"
    And the user input to "Email" textbox with value "<email>"
    And the user input to "Password" textbox with value "<password>"
    And the user input to "Confirm password" textbox with value "<password>"
    And they click the "Register" button
    Then they should see a registration success message

    Examples:
      | firstname | lastname | email                | password   |
      | Tho       | Le       | 2use435r123@gmail.com  | Pa$$w0rd   |
      | Minh      | Tuan     | 2anothe435remail2@gmail.com  | StrongP@ss |
      | Ngoc      | Trang    | 2tester343525@example.com | Secret123  |

  @login
  Scenario: User login
    Given the user is on the login page
    When the user input to "Email" textbox with value "2use435r123@gmail.com"
    And the user input to "Password" textbox with value "Pa$$w0rd"
    And they click the "Log in" button
    Then they should be logged in successfully



