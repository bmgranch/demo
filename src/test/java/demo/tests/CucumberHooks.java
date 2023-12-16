package demo.tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.time.Duration;

@Slf4j
public class CucumberHooks {

	@Autowired
	private ApplicationContext applicationContext;

	@Value("${domain}")
	private String domain;

	@Value("${test.env}")
	private String testEnv;

	@Value("${capture.screenshot.scenarios:FAILED}")
	String screenshotAllScenarios;

	@Autowired
	private Environment env;

	public String getBaseUrl() {
		return env.getProperty(testEnv + "." + domain + ".web.url");
	}

	public CucumberHooks() {
	}

	@Before(order = 1)
	public void beforeScenario(Scenario scenario) {
		log.debug("This will run before the Scenario={}", scenario.getName());
		MDC.put("scenarioName", scenario.getName());
	}

	@Before(order = 2)
	public void IntializeTest() {
		int i = 1;
		for (i = 1; i <= 4; i++) {
			try {
				this.applicationContext.getBean(WebDriver.class);

				i = 4;
			} catch (UnreachableBrowserException unreachableBrowserException) {
				log.info("Base Url Launch" + i + " FAILED");

				log.info(i + "Relaunch baseurl UnreachableBrowserException exception : " + unreachableBrowserException.getMessage());
			}
		}
	}


	@After
	public void afterScenario(Scenario scenario) {
		if (StringUtils.equalsIgnoreCase(screenshotAllScenarios, "NONE")) {
			return;
		}
		if (StringUtils.equalsIgnoreCase(screenshotAllScenarios, "FAILED") && scenario.isFailed()) {
			captureScreenshot(scenario);
		}
		if (StringUtils.equalsIgnoreCase(screenshotAllScenarios, "ALL")) {
			captureScreenshot(scenario);
			clickOnAlertIfPresent();
		}
	}

	private void captureScreenshot(Scenario scenario) {
		try {
			final byte[] screenshot = ((TakesScreenshot) this.applicationContext.getBean(WebDriver.class)).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getId() + "failed");
			log.info("Taking sreenshot for scenario:[{}] at {}", scenario.getName(), scenario.getUri());
		} catch (Exception e) {
			log.info("@After Capture Screenshot Exception and going to quit driver" + e.getMessage());
			this.applicationContext.getBean(WebDriver.class).quit();
		}
	}

	private void clickOnAlertIfPresent() {
		while (checkingAlert()) {
			this.applicationContext.getBean(WebDriver.class).switchTo().alert().accept();
		}
	}

	public boolean checkingAlert() {
		WebDriverWait wait = new WebDriverWait(this.applicationContext.getBean(WebDriver.class), Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}

}
