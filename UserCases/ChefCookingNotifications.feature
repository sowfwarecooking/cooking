Feature: Cooking task reminders
  As a chef, I want to get notified of scheduled cooking tasks so that I can prepare meals on time

  Scenario: 24-hour cooking reminder is sent
    Given a cooking task "Beef Stew" is scheduled for tomorrow
    When the system checks cooking reminders
    Then a 24hour cooking reminder for "Beef Stew" is sent

  Scenario: 1-hour cooking reminder is sent
    Given a cooking task "Grilled Salmon" is scheduled for 1 hour from now
    When the system checks cooking reminders
    Then a 1hour cooking reminder for "Grilled Salmon" is sent

  Scenario: No reminder for past cooking tasks
    Given a cooking task "Roast Chicken" was scheduled for yesterday
    When the system checks cooking reminders
    Then a past-task warning for "Roast Chicken" is sent
