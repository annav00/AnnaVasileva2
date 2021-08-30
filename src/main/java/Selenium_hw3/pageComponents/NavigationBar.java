package Selenium_hw3.pageComponents;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "ul.sidebar-menu")
public class NavigationBar extends AbstractComponent {
    @FindBy(css = "ul.sidebar-menu>li")
    private List<WebElement> navigationBar;

    public NavigationBar(WebDriver webDriver) {
        super(webDriver);
    }

    public List<WebElement> getNavigationBar() {
        return navigationBar;
    }

    public List<String> getNavigationBarExtracted() {
        return navigationBar
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getNavigationBarNumber() {
        return navigationBar.size();
    }
}