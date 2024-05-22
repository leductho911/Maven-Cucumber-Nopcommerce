@register_and_login
Feature: User Registration and Login
  As a user
  I want to be able to register an account
  So that I can access the system

  Background:
    Given the user is on the registration page

  Scenario: Successful registration and login
    When the user enter valid registration details
    And they click the "Register" button
    Then they should see Registration completed message appeared
    And the user is on the login page
    When the user enters their valid username and password
    And they click the "Log in" button
    Then the user should be redirected to the Homepage

  @register_with_datatable
  Scenario: Successful registration and login with table
    And I perform Register new user with valid information as below
      | First name | Last name | Email           | Password   | Confirm password |
      | James      | Marry     | james@gmail.com | 3435ADA#@$ | 3435ADA#@$       |
    Then they should see Registration completed message appeared