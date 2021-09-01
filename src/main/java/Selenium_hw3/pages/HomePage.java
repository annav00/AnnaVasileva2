package Selenium_hw3.pages;

import Selenium_hw3.pageComponents.*;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    private final Benefit benefit = new Benefit(webDriver);
    private final Frame frame = new Frame(this);

    public HomePage(WebDriver webDriver, String URL) {
        super(webDriver);
        webDriver.get(URL);
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

}
