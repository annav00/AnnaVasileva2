package Selenium_hw5.utils;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AssertionUtils {

    public static void assertNumberAndVisibilityElements(List<WebElement> elements, int expectedSize){
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(elements.size()).isEqualTo(expectedSize);
        elements.forEach(element -> softAssertions.assertThat(element.isDisplayed()).isTrue());
        softAssertions.assertAll();
    }
}
