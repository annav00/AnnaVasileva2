package Selenium_hw4.tests;

import Selenium_hw4.steps.ActionStep;
import Selenium_hw4.steps.AssertionStep;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.commons.configuration.*;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTestClass {
    protected WebDriver webDriver;
    protected Configuration properties;
    private final String pathToProperties = "src/test/resources/Selenium_hw3/pageProperties.properties";
    protected ActionStep actionStep;
    protected AssertionStep assertionStep;

    @BeforeClass (description = "Set up WebDriver. Create properties, actionStep, assertionStep objects")
    public void initialization(ITestContext testContext) throws ConfigurationException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        properties =  new PropertiesConfiguration(pathToProperties);
        //softAssertions = new SoftAssertions();

        actionStep = new ActionStep(webDriver);
        assertionStep = new AssertionStep(webDriver);
        testContext.setAttribute("webDriver", webDriver);
    }

    @AfterClass
    public void closeSession() {
        webDriver.quit();
    }
}
