Feature: Custom Meal Requests
  As a customer,
  I want to select ingredients and customize my meal,
  So that I can order meals according to my taste and dietary needs.

  Scenario: Successfully creating a custom meal request
    Given I am on the "Create Custom Meal" page
    When I select ingredients "Chicken", "Broccoli", and "Brown Rice"
    And I submit my custom meal request
    Then I should see "Custom meal created successfully."

  Scenario: Creating a custom meal with dietary preferences
    Given I am on the "Create Custom Meal" page
    When I select ingredients "Tofu" and "Spinach"
    And I choose dietary preferences "Vegan"
    And I submit my custom meal request
    Then I should see "Custom meal created successfully."

  Scenario: Attempting to create a meal without selecting ingredients
    Given I am on the "Create Custom Meal" page
    When I submit my custom meal request without selecting ingredients
    Then I should see "Please select at least one ingredient."