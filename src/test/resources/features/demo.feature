Feature: Basic Cucumber Test

  Scenario: Simple Test
    Given I start the test
    When I execute the step
    Then I should see the result

  @API
  Scenario: Verify momo api
    When I send creativecdn api
    Then I should receive a successful api response
#    And response should contain 3 items
#    And repsonse sholud have expected types
#    And repsonse sholud have expected prefix URL

