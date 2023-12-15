package demo.tests.stepdefs;

import demo.tests.SpringConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@Getter
@NoArgsConstructor
@CucumberContextConfiguration
@ContextConfiguration(classes= SpringConfig.class)
public class AppBaseSteps {

    @Autowired
    protected WebDriver webDriver;
}
