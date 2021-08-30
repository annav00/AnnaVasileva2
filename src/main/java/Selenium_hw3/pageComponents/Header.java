package Selenium_hw3.pageComponents;

import Selenium_hw3.pages.DifferentElementsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@FindBy(tagName = "header")
public class Header extends AbstractComponent{

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

    @FindBy(css = "ul.nav>li")
    private List<WebElement> headerElements;

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

    public List<String> getHeaderElementsText() {
        return headerElements
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public DifferentElementsPage clickDifferentElement() {
        service.click();
        differentElement.click();
        return new DifferentElementsPage(webDriver);
    }
}

