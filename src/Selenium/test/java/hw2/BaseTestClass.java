package hw2;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTestClass {
    protected WebDriver webDriver;
    protected SoftAssertions softAssertions;
    protected static final String BASEURL = "https://jdi-testing.github.io/jdi-light/index.html";
    protected static final String HOME_PAGE_TITLE = "Home Page";
    protected static final String USERNAME = "Roman";
    protected static final String PASS = "Jdi1234";
    protected static final String EXPECTED_USER_NAME = "ROMAN IOVLEV";

    @BeforeClass
    public void initialization() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        softAssertions = new SoftAssertions();
    }

    @AfterClass
    public void closeSession(){
        webDriver.quit();
    }
}
