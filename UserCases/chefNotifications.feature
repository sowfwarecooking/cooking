Feature: Manage cooking tasks

  As a chef
  I want to manage my assigned cooking tasks
  So that I can prepare meals on time

  Scenario: View assigned tasks
    Given I have assigned cooking tasks
    When I open the task list
    Then I should see all my assigned tasks

  Scenario: Remove completed tasks
    Given I have completed a cooking task
    When I mark the task as done
    Then the task should be removed from my task list

  Scenario: Get notified of new tasks
    Given a new cooking task is assigned to me
    When the task is created
    Then I should receive a notification