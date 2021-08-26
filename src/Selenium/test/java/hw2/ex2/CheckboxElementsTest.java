package hw2.ex2;

import hw2.BaseTestClass;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CheckboxElementsTest extends BaseTestClass {
        private static final List<String> LOG_LIST = new ArrayList<>(
            List.of(
                    "Wind: condition changed to true",
                    "Water: condition changed to true",
                    "metal: value changed to Selen",
                    "Colors: value changed to Yellow"
            ));

    private static final Map<String, String> EXPECTED_LOG_NAME_STATUS = Map.of(
            "Water", "true",
            "Wind", "true",
            "metal", "Selen",
            "Colors", "Yellow");

    @Test
    public void openPageTest() {
        //1. Open test site by URL
        webDriver.navigate().to(BASEURL);

        //2. Assert Browser title
        softAssertions.assertThat(webDriver.getTitle()).isEqualTo(HOME_PAGE_TITLE);

        //3. Perform login
        webDriver.findElement(By.id("user-icon")).click();
        webDriver.findElement(By.id("name")).sendKeys(USERNAME);
        webDriver.findElement(By.id("password")).sendKeys(PASS);
        webDriver.findElement(By.id("login-button")).click();

        //4. Assert Username is loggined
        WebElement userName = webDriver.findElement(By.id("user-name"));
        softAssertions.assertThat(userName.isDisplayed()).isTrue();
        softAssertions.assertThat(userName.getText()).isEqualTo(EXPECTED_USER_NAME);


        //5. Open through the header menu Service -> Different Elements Page
        WebElement dropDown = webDriver.findElement(By.className("dropdown-toggle"));
        dropDown.click();
        dropDown.findElement(By.xpath("//li[8]")).click();

        softAssertions.assertThat(webDriver.getCurrentUrl()).isEqualTo("https://jdi-testing.github.io/jdi-light/different-elements.html");

        //6. Select checkboxes
        List <WebElement> checkbox = webDriver.findElements(By.cssSelector("input[type='checkbox']"));
        checkbox.get(0).click();
        checkbox.get(2).click();
        softAssertions.assertThat(checkbox.get(0).isSelected()).isTrue();
        softAssertions.assertThat(checkbox.get(2).isSelected()).isTrue();

        //7. Select radio
        List <WebElement> radio = webDriver.findElements(By.cssSelector("input[type='radio']"));
        radio.get(3).click();
        softAssertions.assertThat(radio.get(3).isSelected()).isTrue();

        //8. Select in dropdown
        List <WebElement> dropdown = webDriver.findElements(By.cssSelector("select.uui-form-element>option"));
        dropdown.get(3).click();
        softAssertions.assertThat(dropdown.get(3).isSelected()).isTrue();

        //9. Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        //   Assert that for radiobutton there is a logList row and value is corresponded to the status of radiobutton.
        //   Assert that for dropdown there is a logList row and value is corresponded to the selected value.
        List<WebElement> logList = webDriver.findElements(By.cssSelector("ul.panel-body-list.logs li"));
        softAssertions.assertThat(logList.isEmpty()).isFalse();

        Map<String, String> logItem = new HashMap<>();
        for (WebElement panelLogList : logList) {
            String itemName = panelLogList.getText().split("\\p{Punct}? +")[1];
            String itemStatus = panelLogList.getText().split("\\p{Punct}? +")[5];
            logItem.put(itemName, itemStatus);
        }
        Assertions.assertThat(logItem).isEqualTo(EXPECTED_LOG_NAME_STATUS);

        //AssertAll
        softAssertions.assertAll();
    }
}

