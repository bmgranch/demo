package demo.tests.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;

public interface SeleniumUtilsProvider {

	static final Logger log = LoggerFactory.getLogger(SeleniumUtilsProvider.class);

	abstract WebDriver getDriver();

	default WebDriverWait getWebWait(int seconds) {
		return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
	}

	default void waitForPageToLoad() {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		String jsScript = "return document.readyState";
		String STATUS = "complete";
		ExpectedCondition<Boolean> jsLoad = webDriver -> StringUtils.equals(jsExecutor.executeScript(jsScript).toString(), STATUS);
		boolean jsReady = jsExecutor.executeScript(jsScript).toString().equals(STATUS);
		if (!jsReady)
			wait.until(jsLoad);
		log.trace("wait for page load is complete");
	}

	default void waitForElementVisible(final WebElement elementFindBy) {
		log.info("Waiting 30 seconds for visibility of webelement " + elementFindBy);
		getWebWait(30).until(ExpectedConditions.visibilityOf(elementFindBy));
	}

	default void clickElement(WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
	}

}