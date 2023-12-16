@checker
Feature: care game feature

  @sanity
  Scenario:  Get a new deck and Shuffle and Deal three cards to each of two players
    Given User open the web browser and navigate to card game base url and validate page is displayed
    And Get a new deck and shuffle
    And Deal three cards for "2" players and check for blackjack
    Then Verify blackjack hit by either player




