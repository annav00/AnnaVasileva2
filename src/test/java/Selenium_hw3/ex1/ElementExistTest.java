package Selenium_hw3.ex1;

import Selenium_hw3.BaseTestClass;
import Selenium_hw3.pageComponents.Frame;
import Selenium_hw3.pages.HomePage;
import org.testng.annotations.Test;

public class ElementExistTest extends BaseTestClass {

    @Test
    public void checkElementsTest() {
        //1. Open test site by URL
        HomePage homePage = new HomePage(webDriver, properties.getString("baseURL"));

        //2. Assert Browser title
        homePage.getTitle();
        softAssertions
                .assertThat(homePage.getTitle())
                .isEqualTo(properties.getString("title"));

        //3. Perform login
        homePage.getHeader().signIn(
                properties.getString("login"),
                properties.getString("password"));

        //4. Assert Username is loggined
        softAssertions
                .assertThat(homePage.getHeader().isNameDisplayed())
                .isTrue();
        softAssertions
                .assertThat(homePage.getHeader().getName())
                .isEqualTo(properties.getString("name"));

        //5. Assert that there are 4 items on the header section are displayed and they have proper texts
        softAssertions
                .assertThat(homePage.getHeader().getHeaderElementsText())
                .hasSize(properties.getList("toolbar").size());

        softAssertions
                .assertThat(homePage.getHeader().getHeaderElementsText())
                .isEqualTo(properties.getList("toolbar"));

        //6. Assert that there are 4 images on the Index Page and they are displayed
        softAssertions
                .assertThat(homePage.getBenefit().getBenefitIconsNumber())
                .isEqualTo(properties.getInt("images.size"));

        homePage.getBenefit().getBenefitIcons()
                .forEach(element ->
                        softAssertions
                                .assertThat(element.isDisplayed())
                                .isTrue());

        //7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        softAssertions
                .assertThat(homePage.getBenefit().getBenefitTextsNumber())
                .isEqualTo(properties.getList("benefit.text").size());

        softAssertions
                .assertThat(homePage.getBenefit().getBenefitTextsExtracted())
                .isEqualTo(properties.getList("benefit.text"));

        //8. Assert that there is the iframe with “Frame Button” exist
        softAssertions
                .assertThat(homePage.getFrame().getFrame())
                .isNotNull();

        softAssertions
                .assertThat(homePage.getFrame().isFrameDisplayed())
                .isTrue();

        //9. Switch to the iframe and check that there is “Frame Button” in the iframe
        Frame frame = homePage.switchToFrame();
        softAssertions
                .assertThat(frame.getFrameButton())
                .isNotNull();

        softAssertions
                .assertThat(frame.isFrameButtonDisplayed())
                .isTrue();

        //10. Switch to original window back
        homePage = (HomePage) frame.switchToDefault();

        //11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        softAssertions
                .assertThat(homePage.getNavigationBar().getNavigationBarNumber())
                .isEqualTo(properties.getList("text.navigationbar").size());

        homePage.getNavigationBar().getNavigationBar()
                .forEach(element ->
                        softAssertions
                                .assertThat(element.isDisplayed())
                                .isTrue());
        softAssertions
                .assertThat(homePage.getNavigationBar().getNavigationBarExtracted())
                .isEqualTo(properties.getList("text.navigationbar"));

        //AssertAll
        softAssertions.assertAll();
    }
}
