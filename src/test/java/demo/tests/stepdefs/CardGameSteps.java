package demo.tests.stepdefs;

import demo.tests.ConfigBean;
import demo.tests.pages.CardGamePage;
import demo.tests.service.deck.DeckService;
import demo.tests.service.deck.RestOperation;
import demo.tests.service.deck.cards.CardInHand;
import demo.tests.service.deck.cards.CardsItem;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class CardGameSteps extends AppBaseSteps {

    @Autowired
    RestOperation restOperation;

    ValidatableResponse validatableResponse;

    public CardGamePage cardGamePage;

    public String shuffledDeckId;

    public boolean isBlackJackPlayerOne;

    public boolean isBlackJackPlayerTwo;

    @Autowired
    ConfigBean configBean;

    public CardGameSteps(CardGamePage cardGamePage) {
        this.cardGamePage = cardGamePage;
    }


    @Given("^User open the web browser and navigate to card game base url and validate page is displayed")
    public void userNavigateToCheckerBaseUrl() {
        this.webDriver.get(configBean.getCardGameBaseUrl());
        cardGamePage.waitForPageToLoad();
        Assert.assertTrue(cardGamePage.deckOfCardHeader.isDisplayed());
    }

    @And("Get a new deck and shuffle")
    public void getANewDeckAndShuffle() {
        String unShuffledDeckId = restOperation.getCall(DeckService.GET_A_NEW_DECK).extract().body().jsonPath().get("deck_id");
        shuffledDeckId = restOperation.getCallWithPathParam(DeckService.GET_SHUFFLE_NEW_DECK, "deck_id", unShuffledDeckId).extract().body().jsonPath().get("deck_id");

    }

    @And("Deal three cards for {string} players and check for blackjack")
    public void dealThreeCardsForPlayersAndCheckForBlackjack(String numberOfPlayers) {
        ObjectMapper mappper = new ObjectMapper();
        CardInHand firstPlayerDealCards = null;
        CardInHand secondPlayerDealCards = null;
        Map<String, String> queryParam = new HashMap<>() {{
            put("count", "3");
        }};

        try {
            firstPlayerDealCards = mappper.readValue(restOperation.getCallWithPathParamAndQueryParam(DeckService.DRAW_CARD, "deck_id", shuffledDeckId, queryParam).extract().body().asPrettyString(), CardInHand.class);
            secondPlayerDealCards = mappper.readValue(restOperation.getCallWithPathParamAndQueryParam(DeckService.DRAW_CARD, "deck_id", shuffledDeckId, queryParam).extract().body().asPrettyString(), CardInHand.class);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        assert firstPlayerDealCards != null;
        log.info("Checking for player 1");
        if (checkBlackJack(firstPlayerDealCards)) {
            isBlackJackPlayerOne = true;
            System.out.println("PAYER 1 : You hit the black jack");
        }
        assert secondPlayerDealCards != null;
        log.info("Checking for player 2");
        if (checkBlackJack(secondPlayerDealCards)) {
            isBlackJackPlayerTwo = true;
            System.out.println("PAYER 2 : You hit the black jack");
        }
    }

    public boolean checkBlackJack(CardInHand cardInHand) {
        int sum = 0;
        for (CardsItem card : cardInHand.getCards()) {
            log.info("CARD Value: " + card.getValue());
            if (card.getValue().equalsIgnoreCase("ACE")) {
                sum = sum + 1;
                continue;
            }
            if (card.getValue().equalsIgnoreCase("KING")) {
                sum = sum + 13;
                continue;
            }
            if (card.getValue().equalsIgnoreCase("JACK")) {
                sum = sum + 11;
                continue;
            }
            if (card.getValue().equalsIgnoreCase("QUEEN")) {
                sum = sum + 11;
                continue;
            } else {
                sum = sum + Integer.parseInt(card.getValue());
            }

        }
        log.info("Total count is : " + sum);
        return sum == 21;
    }


    @Then("Verify blackjack hit by either player")
    public void verifyEitherHasBlackjackAndPrint() {
        if (isBlackJackPlayerOne)
            log.info("Player 1 hit black jack");
        if (isBlackJackPlayerTwo)
            log.info("Player 2 hit black jack");
    }

}
