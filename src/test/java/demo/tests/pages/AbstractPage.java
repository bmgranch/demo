package demo.tests.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Slf4j
public abstract class AbstractPage implements SeleniumUtilsProvider {

	@Autowired
	protected WebDriver webDriver;

	@PostConstruct
	public void initPage() {
		PageFactory.initElements(this.webDriver, this);
	}

	@Override
	public WebDriver getDriver() {
		return this.webDriver;
	}

	public boolean webElementDisplayed(WebElement ele){
		try {
			WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(30));
			wait.until((ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(ele))));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
