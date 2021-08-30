package Selenium_hw2.ex2;

import Selenium_hw2.BaseTestClass;
import Selenium_hw2.DifferentElementsClass;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DifferentElementsTest extends BaseTestClass {

    private static final String DIFF_URL = "https://jdi-testing.github.io/jdi-light/different-elements.html";

    private static final List<String> CHECKBOX = new ArrayList<>(
            List.of("Water", "Earth",  "Wind", "Fire"));

    private static final String CHECKBOX_LOG = ": condition changed to true";

    private static final List<String> RADIO = new ArrayList<>(
            List.of("Gold", "Silver",  "Bronze", "Selen"));

    private static final String RADIO_LOG = "metal: value changed to";

    private static final List<String> DROPDOWN = new ArrayList<>(
            List.of("Red", "Green",  "Blue", "Yellow"));

    private static final String DROPDOWN_LOG = "Colors: value changed to";

    @Test
    public void checkDifferentElementsTest() {
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

        softAssertions.assertThat(webDriver.getCurrentUrl()).isEqualTo(DIFF_URL);

        //6. Select checkboxes
        List <WebElement> checkbox = webDriver.findElements(By.className("label-checkbox"));
        WebElement checkboxWater = new DifferentElementsClass().getCheckBox(checkbox, CHECKBOX.get(0));
        WebElement checkboxWind = new DifferentElementsClass().getCheckBox(checkbox, CHECKBOX.get(2));
        checkboxWater.click();
        checkboxWind.click();
        softAssertions.assertThat(checkboxWater.isSelected()).isTrue();
        softAssertions.assertThat(checkboxWind.isSelected()).isTrue();

        //7. Select radio
        List <WebElement> radio = webDriver.findElements(By.className("label-radio"));
        WebElement radioSelen = new DifferentElementsClass().getRadio(radio, RADIO.get(3));
        radioSelen.click();
        softAssertions.assertThat(radioSelen.isSelected()).isTrue();

        //8. Select in dropdown
        List <WebElement> dropdown = webDriver.findElements(By.tagName("select"));
        Select dropdownYellow = new DifferentElementsClass().getDropDown(dropdown, DROPDOWN.get(3));
        softAssertions
                .assertThat(dropdownYellow.getFirstSelectedOption().getText())
                .isEqualTo(DROPDOWN.get(3));

        //9. Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        //   Assert that for radiobutton there is a logList row and value is corresponded to the status of radiobutton.
        //   Assert that for dropdown there is a logList row and value is corresponded to the selected value.
        List<WebElement> logList = webDriver.findElements(By.cssSelector("ul.panel-body-list.logs li"));
        softAssertions.assertThat(logList.isEmpty()).isFalse();

        List<String> expectedLog = new ArrayList<>(
                List.of(CHECKBOX_LOG + CHECKBOX.get(0),
                        CHECKBOX_LOG + CHECKBOX.get(2),
                        RADIO_LOG + RADIO.get(3),
                        DROPDOWN_LOG + DROPDOWN.get(3)
                ));

        expectedLog.forEach(expLog ->
                softAssertions
                        .assertThat(logList
                        .stream()
                        .map(WebElement::getText)
                        .filter(log -> log.contains(expLog))
                        .collect(Collectors.toList()))
                        .isNotNull()
        );

        //AssertAll
        softAssertions.assertAll();
    }
}

