Feature: Invoice Generation
  As a customer,
  I want to receive an invoice
  So that I have a record of my purchase including details of items and payment.

  Scenario: Generate invoice for a single purchased item
    Given : the customer is logged in and is Vegetarian
    When the customer purchases see the menu
    And the customer selects a single item
    Then the invoice is generated

  Scenario: Generate invoice for multiple purchased items
    Given : the customer is logged in and is Vegetarian
    When the customer purchases see the menu
    And the customer selects multiple items
    Then the invoice is generated for multiple items
Scenario: : add balance to the system
    Given : the customer is logged in and is Vegetarian
    When the customer purchases see the menu
    And the customer selects multiple items
    Then the invoice is generated for multiple items
    Then the balance is updated
