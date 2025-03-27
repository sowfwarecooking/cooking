Feature: Ingredients Compatibility
  To ensure customers do not select incompatible or unavailable ingredients,
  the system should validate ingredient combinations.

  Scenario: Valid ingredient combination
    Given the following available ingredients:
      | Ingredient  |
      | Chicken      |
      | Lettuce      |
      | Tomato       |
      | Cheese       |
    When a customer selects "Chicken", "Lettuce", and "Tomato"
    Then the meal request is valid

  Scenario: Invalid ingredient combination
    Given the following incompatible ingredient combinations:
      | Ingredient A | Ingredient B |
      | Chicken      | Cheese       |
    And the following available ingredients:
      | Ingredient  |
      | Chicken      |
      | Cheese       |
    When a customer selects "Chicken" and "Cheese"
    Then the meal request is invalid with the message "Chicken and Cheese are incompatible."

  Scenario: Unavailable ingredient
    Given the following available ingredients:
      | Ingredient  |
      | Chicken      |
      | Lettuce      |
      | Tomato       |
    When a customer selects "Chicken" and "Cheese"
    Then the meal request is invalid with the message "Cheese is unavailable."