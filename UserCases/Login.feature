Feature: User Login

  Background:
    Given the data loaded from file
  Scenario: Successful Login
    Given the user is on the login page
    When the user enters a valid "admin" and "1234"
    Then the system grants access


  Scenario: Unsuccessful Login with Incorrect Credentials
    Given the user is on the login page
    When the user enters a valid "admin" and wrong "wrongpassword"
    Then the system displays the error message

  Scenario: Unsuccessful Login with Unregistered Credentials
    Given the user is on the login page
    When the user enters an unregistered username "guest"
    Then the system displays the error message saying "User not registered";


