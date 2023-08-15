@register_login
Feature: User Registration and Login

  @register
  Scenario: User registration with valid information
    Given the user is on the registration page
    When they fill in their registration details
      | First Name | Last Name | Email           | Password | Confirm Password |
      | John       | Mai Van   | 1thso101@gmail.com | 123456   | 123456           |

    And they click the Register button
    Then they should see a registration success message

  @login
  Scenario: User login
    Given the user is on the login page
    When they enter their login credentials
      | Email           | Password |
      | 1thso101@gmail.com | 123456   |
    And they click the login button
    Then they should be logged in successfully



