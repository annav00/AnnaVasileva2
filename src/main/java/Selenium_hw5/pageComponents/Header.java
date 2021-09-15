package Selenium_hw5.pageComponents;

import Selenium_hw5.pages.DifferentElementsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@FindBy(tagName = "header")
public class Header extends AbstractComponent {

    @FindBy(id = "user-icon")
    private WebElement userIcon;

    @FindBy(id = "name")
    private WebElement loginInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(css = "ul.nav")
    private WebElement headerElements;

    @FindBy(css = "a.dropdown-toggle")
    private  WebElement service;

    @FindBy(css = "ul[role=\"menu\"]>li>a[href=\"different-elements.html\"]")
    private  WebElement differentElement;

    public Header(WebDriver webDriver) {
        super(webDriver);
    }

    public Header inputLogin(String login) {
        loginInput.sendKeys(login);
        return this;
    }

    public Header inputPasswd(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public Header clickLoginButton() {
        loginButton.click();
        return this;
    }

    public String getName() {
        return userName.getText();
    }

    public boolean isNameDisplayed() {
        return userName.isDisplayed();
    }

    public Header signIn(String login, String password) {
        userIcon.click();
        inputLogin(login);
        inputPasswd(password);
        clickLoginButton();
        return this;
    }

    public WebElement getHeaderElement(String buttonName) {
        return  headerElements.findElement(By.xpath("./li/a[contains(text(), \"" + buttonName + "\")]"));
    }

    public WebElement getServiceElement(String buttonName) {
        return getHeaderElement("Service").findElement(By.xpath("//a[contains(text(), \"" + buttonName + "\")]"));
    }

    public DifferentElementsPage clickDifferentElement() {
        service.click();
        differentElement.click();
        return new DifferentElementsPage(webDriver);
    }
}

