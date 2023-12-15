package demo.tests.pages;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CheckerPage extends AbstractPage {

    //12 piece
    @FindBy(how = How.XPATH, using = "//img[@src='me1.gif']")
    public List<WebElement> allYouYellow;

    //12 piece
    @FindBy(how = How.XPATH, using = "//img[@src='you1.gif']")
    public List<WebElement> allMeBlue;

    // 32 space available before game start
    @FindBy(how = How.XPATH, using = "//img[@src='black.gif']")
    public List<WebElement> allBlack;

    //only 8 space before game start
    @FindBy(how = How.XPATH, using = "////img[@src='gray.gif']")
    public List<WebElement> allGray;

    @FindBy(how = How.XPATH, using = "//img[@src='you1.gif' and @name='space02']")
    public WebElement firstMoveChecker;

    @FindBy(how = How.XPATH, using = "//img[@src='you1.gif' and @name='space11']")
    public WebElement secondMoveChecker;

    @FindBy(how = How.XPATH, using = "//img[@src='you1.gif' and @name='space13']")
    public WebElement thirdMoveChecker;

    @FindBy(how = How.XPATH, using = "//img[@src='you1.gif' and @name='space00']")
    public WebElement fourthMoveChecker;

    @FindBy(how = How.XPATH, using = "//img[@src='you1.gif' and @name='space62']")
    public WebElement fifthMoveChecker;

    @FindBy(how = How.XPATH, using = "//img[@src='gray.gif' and @name='space13']")
    public WebElement firstMoveToGray;

    @FindBy(how = How.XPATH, using = "//img[@src='gray.gif' and @name='space73']")
    public WebElement fifthMoveToGray;

    @FindBy(how = How.XPATH, using = "//img[@src='https://www.gamesforthebrain.com/game/checkers/gray.gif' and @name='space02']")
    public WebElement firstMoveComplete;

    @FindBy(how = How.XPATH, using = "//img[@src='https://www.gamesforthebrain.com/game/checkers/gray.gif' and @name='space11']")
    public WebElement secondMoveComplete;

    @FindBy(how = How.XPATH, using = "//img[@src='https://www.gamesforthebrain.com/game/checkers/gray.gif' and @name='space35']")
    public WebElement thirdMoveTakingBlue;


    @FindBy(how = How.XPATH, using = "//div[@id='board']")
    public WebElement board;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Restart...')]")
    public WebElement restartButton;
}
