Feature: Real-Time Supplier Pricing and Ordering Integration

  As a kitchen manager,
  I want the system to fetch real-time ingredient prices from suppliers
  So that I can make cost-effective purchasing decisions.

  Scenario: Fetch real-time prices for ingredients
    Given the system is connected to the suppliers
    When the kitchen manager requests the price for an ingredient
    Then the system should return the current price for ingredient from the suppliers

  Scenario: reload the stock
    Given the system is connected to the suppliers
    When the kitchen manager requests the price for an ingredient
    Then the system should return the current price for ingredient from the suppliers
    And the system should reload the stock by number