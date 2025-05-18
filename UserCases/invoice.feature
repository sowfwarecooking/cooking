Feature: Invoice Generation
  As a customer,
  I want to receive an invoice
  So that I have a record of my purchase including details of items and payment.

  Scenario: Generate invoice for a single purchased item
    Given : the customer is logged in and is Vegetarian
    When the customer purchases see the menu
    Then the customer selects "Veggie Burger"
    Then the invoice should list the item "Veggie Burger"
    And the invoice should show the total amount as 8.99
    And the invoice should include the date of purchase