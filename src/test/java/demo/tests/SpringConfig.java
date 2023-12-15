package demo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@ComponentScan({ "demo.tests", "demo.tests" })
@PropertySource("application-${test.env:qa}.properties")
@PropertySource(value = "application-local.properties", ignoreResourceNotFound = true)
public class SpringConfig {

	@Autowired
	ConfigBean config;

	@Autowired
	Environment env;

	@Bean
	@Profile("local")
	public WebDriver getLocalDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		String downloadLoc = System.getProperty("user.dir") + "\\src\\test\\resources" + File.separator + "download" + File.separator;
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("use-fake-device-for-media-stream");
		options.addArguments("use-fake-ui-for-media-stream");
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		List<String> arguments = new ArrayList<String>();
		arguments.add("--lang=en");
		arguments.add("--allow-file-access-from-files");
		options.addArguments(arguments);
		Map<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("download.default_directory", downloadLoc);
		chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
		options.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
}
