Feature: Ingredient Substitution System
  Suggests ingredient substitutions based on dietary restrictions or availability.

  Scenario: Suggesting Alternatives Based on Dietary Restrictions
    Given the user follows a specific diet type (e.g., VEGAN, KETO, LOW_CARB, VEGETARIAN, GLUTEN_FREE)
    And the user inputs a recipe containing restricted ingredients
    When the system processes the recipe
    Then the system suggests suitable alternatives that adhere to the user's dietary restrictions


  Scenario: Suggesting Alternatives for Unavailable Ingredients
    Given the user indicates an ingredient is unavailable
    And the user inputs a recipe containing that ingredient
    When the system processes the recipe
    Then the system suggests suitable alternatives based on availability

