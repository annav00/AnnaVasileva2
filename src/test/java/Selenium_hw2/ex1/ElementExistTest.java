package Selenium_hw2.ex1;

import Selenium_hw2.BaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class ElementExistTest extends BaseTestClass {
    private static final List<String> headerSectionItemNames = new ArrayList<>(
            List.of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"));
    private static final int INDEX_PAGE_IMAGES_SIZE = 4;
    private static final List<String> INDEX_PAGE_TEXT = new ArrayList<>(
            List.of(
                    "To include good practices\n" +
                            "and ideas from successful\n" +
                            "EPAM project",
                    "To be flexible and\n" +
                            "customizable",
                    "To be multiplatform",
                    "Already have good base\n" +
                            "(about 20 internal and\n" +
                            "some external projects),\n" +
                            "wish to get more…"
            )
    );
    private static final List<String> SIDEBAR_NAMES = new ArrayList<>(
            List.of("Home", "Contact form", "Service", "Metals & Colors", "Elements packs"));

    @Test
    public void checkElementsTest() {
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

        //5. Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerElements = webDriver.findElements(By.xpath("//header/div/nav/ul[1]/li/a"));
        softAssertions.assertThat(headerElements.size()).isEqualTo(headerSectionItemNames.size());

        for (int i = 0; i < headerSectionItemNames.size(); i++) {
            softAssertions.assertThat(headerElements.get(i).isDisplayed()).isTrue();
            softAssertions.assertThat(headerElements.get(i).getText())
                    .isEqualTo(headerSectionItemNames.get(i));
        }

        //6. Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> indexPageImages = webDriver.findElements(By.className("benefit-icon"));
        softAssertions.assertThat(indexPageImages.size()).isEqualTo(INDEX_PAGE_IMAGES_SIZE);

        indexPageImages.forEach(element -> softAssertions.assertThat(element.isDisplayed()).isTrue());

        //7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> indexPageIcons = webDriver.findElements(By.className("benefit-txt"));
        softAssertions.assertThat(indexPageIcons.size()).isEqualTo(INDEX_PAGE_TEXT.size());

        for (int i = 0; i < INDEX_PAGE_TEXT.size(); i++) {
            softAssertions.assertThat(indexPageIcons.get(i).isDisplayed()).isTrue();
            softAssertions.assertThat(indexPageIcons.get(i).getText()).isEqualTo(INDEX_PAGE_TEXT.get(i));
        }

        //8. Assert that there is the iframe with “Frame Button” exist
        List<WebElement> iframeElements = webDriver.findElements(By.id("frame"));
        softAssertions.assertThat(iframeElements.isEmpty()).isFalse();

        WebElement frameButtonIframe = iframeElements.get(0);
        softAssertions.assertThat(frameButtonIframe.isDisplayed()).isTrue();

        //9. Switch to the iframe and check that there is “Frame Button” in the iframe
        webDriver.switchTo().frame(frameButtonIframe);

        WebElement frameButton = webDriver.findElement(By.id("frame-button"));
        softAssertions.assertThat(frameButton.isDisplayed()).isTrue();

        //10. Switch to original window back
        webDriver.switchTo().parentFrame();

        //11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        List<WebElement> sidebarElements = webDriver.findElements(
                By.cssSelector("ul[class=\"sidebar-menu left\"]>li"));

        softAssertions.assertThat(sidebarElements.size()).isEqualTo(SIDEBAR_NAMES.size());

        for (int i = 0; i < SIDEBAR_NAMES.size(); i++) {
            softAssertions.assertThat(sidebarElements.get(i).isDisplayed()).isTrue();
            softAssertions.assertThat(sidebarElements.get(i).getText()).isEqualTo(SIDEBAR_NAMES.get(i));
        }

        //AssertAll
        softAssertions.assertAll();
    }
}
