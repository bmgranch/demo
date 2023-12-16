package demo.tests.pages;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CardGamePage extends AbstractPage {

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Deck of Cards')]")
    public WebElement deckOfCardHeader;

}
