package Selenium_hw5.pages;

import Selenium_hw5.pageComponents.Header;
import Selenium_hw5.pageComponents.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage{

    public WebDriver webDriver;

    private final Header header;
    private final NavigationBar navigationBar;

    public AbstractPage(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(driver, this);
        header = new Header(webDriver);
        navigationBar = new NavigationBar(webDriver);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public String getURL() {
        return webDriver.getCurrentUrl();
    }

    public Header getHeader(){
        return header;
    }

    public NavigationBar getNavigationBar(){
        return navigationBar;
    }
}
