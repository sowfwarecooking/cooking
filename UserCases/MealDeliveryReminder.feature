Feature: Delivery reminders
  Customers get reminders for upcoming deliveries

  Scenario: 24-hour reminder is sent
    Given a delivery is scheduled for tomorrow
    When the system checks reminders
    Then a 24hour reminder is sent

  Scenario: 1-hour reminder is sent
    Given a delivery is scheduled for 1 hour from now
    When the system checks reminders
    Then a 1hour reminder is sent

  Scenario: No reminder for past deliveries
    Given a delivery was scheduled for yesterday
    When the system checks reminders
    Then special message is sent