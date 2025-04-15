Feature: Ingredient Substitution Alerts
  As a chef,
  I want to be alerted when an ingredient substitution is applied
  So that I can approve or adjust the recipe.

  Scenario: Chef receives a substitution alert
    Given "butter" is unavailable in a recipe
    When "margarine" is substituted
    Then the chef is alerted
    And can approve or adjust.

  Scenario: Chef approves the substitution
    Given the chef is alerted
    When the chef approves "margarine"
    Then the recipe is updated
    And the approval logged.

  Scenario: Chef adjusts the substitution
    Given the chef is alerted
    When the chef selects "coconut oil"
    Then the recipe is updated with "coconut oil"
    And the adjustment logged.