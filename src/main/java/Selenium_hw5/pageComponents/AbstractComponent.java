package Selenium_hw5.pageComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractComponent {
    protected WebDriver webDriver;
    protected WebDriverWait wait;

    protected AbstractComponent(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10).toSeconds());
        PageFactory.initElements(webDriver, this);
    }
}
