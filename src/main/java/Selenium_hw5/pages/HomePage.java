package Selenium_hw5.pages;

import Selenium_hw5.pageComponents.*;
import Selenium_hw5.pageComponents.Frame;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    private final Benefit benefit = new Benefit(webDriver);
    private final Frame frame = new Frame(this);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public Benefit getBenefit(){
        return benefit;
    }

    public Frame getFrame(){
        return frame;
    }

    public Frame switchToFrame() {
        webDriver.switchTo().frame(frame.getFrame());
        return new Frame(this);
    }

    public HomePage open(String URL) {
        webDriver.get(URL);
        return this;
    }
}
