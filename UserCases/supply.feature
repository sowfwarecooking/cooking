Feature: admin view the supply status

  Scenario: Updating Ingredient Stock in Real Time
    Given the kitchen manager is logged into the system
    When they update the stock level of an ingredient after usage
    Then the system should reflect the updated stock level in real time

  Scenario: Low Stock Alert and Restocking Suggestion
    Given an ingredient's stock level falls below a predefined threshold
    When the system detects the low stock level
    Then it should automatically generate a restocking suggestion and notify the kitchen manager

