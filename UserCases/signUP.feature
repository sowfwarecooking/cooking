Feature: Sign Up

  Scenario: Valid username and valid password
    Given user at sign up page
    When the user enters a valid username "validUseer" and password "Strong_Pass123"
    Then the system grantes access

  Scenario: Invalid username and valid password
    Given user at sign up page
    When the user enters an taken username "admin" and password "Strong_Pass123"
    Then the system denies access

  Scenario: Valid username and invalid password
    Given user at sign up page
    When the user enters a valid username "user_is_not_registered" and invalid password "weak1234"
    Then the system denies access with message "Weak password"
