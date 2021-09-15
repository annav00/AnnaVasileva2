package Selenium_hw5.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/Selenium_hw5/UserTablePageCheckCheckbox.feature"},
        glue = {"Selenium_hw5.steps", "Selenium_hw5.hooks"}
)
public class RunUserTablePageCheckCheckbox extends AbstractTestNGCucumberTests {
}
