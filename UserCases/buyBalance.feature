Feature: as a admin i want to see balance after buying

Scenario: as a admin i want to see balance after buying
    Given i am a admin
    When i buy product
    Then i should see balance
Scenario: as a admin i want to see balance after buying multiple times
    Given i am a admin
    When i buy product multiple times
    Then i should see total balance