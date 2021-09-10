package Selenium_hw4.tests.ex1;

import Selenium_hw4.listeners.ScreenshotListener;
import Selenium_hw4.tests.BaseTestClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Selenium_hw4.DataClass.*;


@Listeners({ScreenshotListener.class })
@Story("User should see all header section items, benefits, \"Button\" iframe and navigation items")
@Feature("Home Page contains header section items, benefits, \"Button\" iframe and navigation items")
public class ElementExistTestWithFail extends BaseTestClass {

    @Test(description = "Test the test with fail")
    public void checkElementsTest() {
        //1. Open test site by URL
        actionStep.openHomePage(properties.getString("baseURL"));

        //2. Assert fail Browser title
        assertionStep.checkPageTitle("Fail");

        //3. Perform login
        actionStep.performLogin(properties.getString("login"), properties.getString("password"));

        //4. Assert Username is logged
        assertionStep.usernameIsLogged(EXPECTED_USER_NAME);

        //5. Assert that there are 4 items on the header section are displayed, and they have proper texts
        assertionStep.checkNumberOfItemsOnHeader(TOOLBAR_TEXT.size());
        assertionStep.checkNameItemsOnHeader(TOOLBAR_TEXT);

        //6. Assert that there are 4 images on the Index Page, and they are displayed
        assertionStep.checkNumberOfBenefitImages(BENEFIT_INDEX_PAGE_IMAGES_SIZE);
        assertionStep.checkIfDisplayedBenefitImages();

        //7. Assert that there are 4 texts on the Index Page under icons, and they have proper text
        assertionStep.checkNumberOfBenefitText(BENEFIT_INDEX_PAGE_TEXT.size());
        assertionStep.checkBenefitText(BENEFIT_INDEX_PAGE_TEXT);

        //8. Assert that there is the iframe with “Frame Button” exist
        assertionStep.checkFrameExist();
        assertionStep.checkFrameDisplayed();

        //9. Switch to the iframe and check that there is “Frame Button” in the iframe
        actionStep.switchToIframe();
        assertionStep.checkFrameButtonExist();
        assertionStep.checkFrameButtonDisplayed();

        //10. Switch to original window back
        actionStep.switchToOriginalWindow();

        //11. Assert that there are 5 items in the Left Section are displayed, and they have proper text
        assertionStep.checkNumberOfItemsInLeftSection(NAVIGATIONBAR_TEXT.size());
        assertionStep.checkTextInItemsInLeftSection(NAVIGATIONBAR_TEXT);
    }
}

