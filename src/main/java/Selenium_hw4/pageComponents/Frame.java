package Selenium_hw4.pageComponents;

import Selenium_hw4.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Frame extends AbstractComponent {

    @FindBy(id = "frame")
    private WebElement frame;

    @FindBy(id = "frame-button")
    private WebElement frameButton;

    private final AbstractPage parent;

    public Frame(AbstractPage parent) {
        super(parent.webDriver);
        this.parent = parent;
    }

    public WebElement getFrame() {
        return frame;
    }

    public WebElement getFrameButton() {
        return frameButton;
    }

    public boolean isFrameButtonDisplayed() {
        return frameButton.isDisplayed();
    }

    public boolean isFrameDisplayed() {
        return frame.isDisplayed();
    }

    public AbstractPage switchToDefault() {
        parent.webDriver.switchTo().parentFrame();
        return parent;
    }

}