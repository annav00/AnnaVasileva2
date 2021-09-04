package Selenium_hw4.steps;

import Selenium_hw4.pages.DifferentElementsPage;
import Selenium_hw4.pages.HomePage;
import org.openqa.selenium.WebDriver;

public abstract class AbstractStep {

    protected WebDriver webDriver;

    protected HomePage homePage;
    protected DifferentElementsPage differentElementsPage;

    protected AbstractStep(WebDriver webDriver) {
         this.webDriver = webDriver;
         homePage = new HomePage(webDriver);
         differentElementsPage = new DifferentElementsPage(webDriver);
    }

}