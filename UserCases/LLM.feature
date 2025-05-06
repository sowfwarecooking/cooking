Feature: Recipe Recommendation Assistant
  Background: seter up the recipe recommendation assistant

  Scenario: chef provides dietary preferences
    Given the user has a dietary preference of "vegetarian"
    When the user requests recipe recommendations
    Then the assistant should recommend only vegetarian recipes

  Scenario: User requests recipes based on cooking time
    Given the user wants recipes that take less than 30 minutes to cook
    When the user requests recipe recommendations based on cooking time
    Then the assistant should recommend recipes with a cooking time of less than 30 minutes

    Scenario: user requests recipes based on cooking time and dietary preferences and available ingredient and dietary preferences
    Given the user has a dietary preference is "keto"
    And the user wants recipes that take  30 minutes to cook
    And the user has a ingredient of "chicken breast , pasta , tomato"
    When the user requests recipe recommendations based on cooking time and dietary preferences and available ingredient and dietary preferences
    Then the assistant should recommend recipes with a cooking time of less than 30 minutes and dietary preferences of keto


