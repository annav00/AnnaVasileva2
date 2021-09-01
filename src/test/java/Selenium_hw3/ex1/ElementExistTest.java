package Selenium_hw3.ex1;

import Selenium_hw3.BaseTestClass;
import Selenium_hw3.pageComponents.Frame;
import Selenium_hw3.pages.HomePage;
import org.testng.annotations.Test;
import static Selenium_hw3.DataClass.*;

public class ElementExistTest extends BaseTestClass {

    @Test
    public void checkElementsTest() {
        //1. Open test site by URL
        HomePage homePage = new HomePage(webDriver, properties.getString("baseURL"));

        //2. Assert Browser title
        homePage.getTitle();
        softAssertions
                .assertThat(homePage.getTitle())
                .isEqualTo(HOME_PAGE_TITLE);

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
                .isEqualTo(EXPECTED_USER_NAME);

        //5. Assert that there are 4 items on the header section are displayed and they have proper texts
        softAssertions
                .assertThat(homePage.getHeader().getHeaderElementsText())
                .hasSize(TOOLBAR_TEXT.size());
//                .hasSize(properties.getList("toolbar").size());

        softAssertions
                .assertThat(homePage.getHeader().getHeaderElementsText())
                .isEqualTo(TOOLBAR_TEXT);

        //6. Assert that there are 4 images on the Index Page and they are displayed
        softAssertions
                .assertThat(homePage.getBenefit().getBenefitIconsNumber())
                .isEqualTo(BENEFIT_INDEX_PAGE_IMAGES_SIZE);

        homePage.getBenefit().getBenefitIcons()
                .forEach(element ->
                        softAssertions
                                .assertThat(element.isDisplayed())
                                .isTrue());

        //7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        softAssertions
                .assertThat(homePage.getBenefit().getBenefitTextsNumber())
                .isEqualTo(BENEFIT_INDEX_PAGE_TEXT.size());

        softAssertions
                .assertThat(homePage.getBenefit().getBenefitTextsExtracted())
                .isEqualTo(BENEFIT_INDEX_PAGE_TEXT);

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
                .isEqualTo(NAVIGATIONBAR_TEXT.size());

        homePage.getNavigationBar().getNavigationBar()
                .forEach(element ->
                        softAssertions
                                .assertThat(element.isDisplayed())
                                .isTrue());
        softAssertions
                .assertThat(homePage.getNavigationBar().getNavigationBarExtracted())
                .isEqualTo(NAVIGATIONBAR_TEXT);

        //AssertAll
        softAssertions.assertAll();
    }
}
