Feature: Recipe Recommendation Assistant

  Scenario: chef provides dietary preferences
    Given the user has a dietary preference of "vegetarian"
    When the user requests recipe recommendations
    Then the assistant should recommend only vegetarian recipes

  Scenario: User requests recipes based on cooking time
    Given the user wants recipes that take less than 30 minutes to cook
    When the user requests recipe recommendations based on cooking time
    Then the assistant should recommend recipes with a cooking time of less than 30 minutes
