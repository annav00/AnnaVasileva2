package Selenium_hw4.pageComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@FindBy(css = "ul.logs")
public class LogPanel extends AbstractComponent {
    @FindBy(tagName = "li")
    private List<WebElement> logList;

    public LogPanel(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> findLogs(String logText) {
        return logList
                .stream()
                .map(WebElement::getText)
                .filter(log -> log.contains(logText))
                .collect(Collectors.toList());
    }
}