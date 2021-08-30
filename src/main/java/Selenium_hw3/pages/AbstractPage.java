package Selenium_hw3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage{

    public WebDriver webDriver;

    public AbstractPage(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public String getURL() {
        return webDriver.getCurrentUrl();
    }
}
