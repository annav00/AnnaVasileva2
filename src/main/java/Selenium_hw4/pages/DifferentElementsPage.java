package Selenium_hw4.pages;

import Selenium_hw4.pageComponents.*;
import org.openqa.selenium.WebDriver;

public class DifferentElementsPage extends AbstractPage {

    private final DifferentElements differentElements = new DifferentElements(webDriver);
    private final LogPanel logPanel = new LogPanel(webDriver);

    public DifferentElementsPage(WebDriver driver) {
        super(driver);
    }

    public DifferentElements getDifferentElements(){
        return differentElements;
    }

    public LogPanel getLogPanel(){
        return logPanel;
    }

}
