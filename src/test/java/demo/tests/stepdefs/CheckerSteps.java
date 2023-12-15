package demo.tests.stepdefs;

import demo.tests.ConfigBean;
import demo.tests.SpringConfig;
import demo.tests.pages.CheckerPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@ContextConfiguration(classes= SpringConfig.class)
public class CheckerSteps extends AppBaseSteps {

    public CheckerPage checkerPage;

    @Autowired
    ConfigBean configBean;

    public CheckerSteps(CheckerPage checkerPage) {
        this.checkerPage = checkerPage;
    }

    @Given("User open the web browser and navigate to checker base url")
    public void userNavigateToCheckerBaseUrl() {
        this.webDriver.get(configBean.getAppBaseUrl());
        checkerPage.waitForPageToLoad();
    }

    @Then("Validate all moves are legal")
    public void validateAllMovesAreLegal() throws InterruptedException {
        //first move
        checkerPage.clickElement(checkerPage.firstMoveChecker);
        checkerPage.firstMoveToGray.click();
        checkerPage.waitForElementVisible(checkerPage.firstMoveComplete);
        Assert.assertTrue(checkerPage.firstMoveComplete.isDisplayed());
        Thread.sleep(2000);

        //second move
        checkerPage.secondMoveChecker.click();
        checkerPage.firstMoveComplete.click();
        checkerPage.waitForElementVisible(checkerPage.secondMoveComplete);
        Assert.assertTrue(checkerPage.secondMoveComplete.isDisplayed());
        Thread.sleep(2000);

        //third move
        checkerPage.thirdMoveChecker.click();
        checkerPage.thirdMoveTakingBlue.click();
        Thread.sleep(2000);

        //fourth move
        checkerPage.fourthMoveChecker.click();
        checkerPage.secondMoveComplete.click();
        Thread.sleep(2000);

        //fifth move
        checkerPage.fifthMoveChecker.click();
        checkerPage.fifthMoveToGray.click();

    }

    @Then("Validate that page is displayed")
    public void validateThatPageIsDisplayed() {
        Assert.assertTrue(checkerPage.webElementDisplayed(checkerPage.board));
    }

    @And("Restart the game and confirm game is restarted")
    public void restartTheGameAndConfirmGameIsRestarted() {
        checkerPage.restartButton.click();
        Assert.assertEquals(12, checkerPage.allYouYellow.size());
    }
}
