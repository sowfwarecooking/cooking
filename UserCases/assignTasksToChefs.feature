Feature: Task Assignment for Chefs

  As a kitchen manager,
  I want to assign tasks to chefs based on their workload and expertise
  So that I can ensure balanced workloads and efficiency.

  Scenario: Assign a task to a chef with matching expertise
    Given Chef Alice specializes in pastry with a workload of 3
    And the task "Prepare dessert" requires pastry expertise
    When I assign the task
    Then it should be assigned to Chef Alice

  Scenario: Avoid overloading a chef
    Given Chef Bob specializes in grilling with a workload of 5
    And the task "Grill steaks" requires grilling expertise
    When I assign the task
    Then it should be assigned to Chef Bob

  Scenario: Notify when no chef is available
    Given all chefs have full workloads
    And the task "Prepare dessert" requires pastry expertise
    When I attempt to assign the task
    Then I should see "No chef is available for this task"