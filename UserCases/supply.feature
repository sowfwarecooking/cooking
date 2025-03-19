Feature: admin view the supply status

  Scenario: Low Stock Alert and Restocking Suggestion
    Given an ingredient's stock level falls below a predefined threshold
    When the system detects the low stock level
    Then it should automatically generate a restocking suggestion and notify the kitchen manager
