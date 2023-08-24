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

    Given the user is on the login page
    When the user enters their valid username and password
    And they click the "Log in" button
    Then the user should be redirected to the Homepage