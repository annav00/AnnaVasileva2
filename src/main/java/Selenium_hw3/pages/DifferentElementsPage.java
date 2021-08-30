package Selenium_hw3.pages;

import Selenium_hw3.pageComponents.*;
import org.openqa.selenium.WebDriver;

public class DifferentElementsPage extends AbstractPage {
    private final DifferentElements differentElements = new DifferentElements(webDriver);
    private final LogPanel logPanel = new LogPanel(webDriver);
    private final Header header = new Header(webDriver);
    private final NavigationBar navigationBar = new NavigationBar(webDriver);

    public DifferentElementsPage(WebDriver driver) {
        super(driver);
    }

    public DifferentElements getDifferentElements(){
        return differentElements;
    }

    public LogPanel getLogPanel(){
        return logPanel;
    }

    public Header getHeader(){
        return header;
    }

    public NavigationBar getNavigationBar(){
        return navigationBar;
    }
}
