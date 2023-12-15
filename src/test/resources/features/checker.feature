@checker
Feature: checker game feature

  @sanity
  Scenario: Validate that checker game site is up and running
    Given User open the web browser and navigate to checker base url
    Then Validate that page is displayed
    And Validate all moves are legal
    And Restart the game and confirm game is restarted




