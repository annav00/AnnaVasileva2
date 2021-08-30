package Selenium_hw3;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.commons.configuration.*;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTestClass {
    protected WebDriver webDriver;
    protected SoftAssertions softAssertions;
    protected Configuration properties;
    private final String pathToProperties = "src/test/resources/Selenium_hw3/pageProperties.properties";

    @BeforeClass
    public void initialization() throws ConfigurationException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        properties =  new PropertiesConfiguration(pathToProperties);
        softAssertions = new SoftAssertions();
    }

    @AfterClass
    public void closeSession() {
        webDriver.quit();
    }
}
