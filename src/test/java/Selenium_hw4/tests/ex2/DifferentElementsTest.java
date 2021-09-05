package Selenium_hw4.tests.ex2;

import Selenium_hw4.listeners.ScreenshotListener;
import Selenium_hw4.tests.BaseTestClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static Selenium_hw4.DataClass.HOME_PAGE_TITLE;
import static Selenium_hw4.DataClass.*;


@Listeners({ScreenshotListener.class })
@Story("User should click on elements on Different Elements page and check actions in logs panel")
@Feature("Elements are clickable and logs panel shows user action")
public class DifferentElementsTest extends BaseTestClass {

    @Test(description = "Test clickable elements and logs of them")
    public void checkDifferentElementsTest() {
        //1. Open test site by URL
        actionStep.openHomePage(properties.getString("baseURL"));

        //2. Assert Browser title
        assertionStep.checkPageTitle(HOME_PAGE_TITLE);

        //3. Perform login
        actionStep.performLogin(properties.getString("login"), properties.getString("password"));

        //4. Assert Username is logged
        assertionStep.usernameIsLogged(EXPECTED_USER_NAME);


        //5. Open through the header menu Service -> Different Elements Page
        actionStep.openDifferentElementsPageThroughHeaderMenu();
        assertionStep.checkCurrentUrl(properties.getString("diffURL"));

        //6. Select checkboxes
        actionStep.selectCheckBox(CHECKBOX_TEXT.get(0));
        actionStep.selectCheckBox(CHECKBOX_TEXT.get(2));
        assertionStep.checkCheckboxIsSelected(CHECKBOX_TEXT.get(0));
        assertionStep.checkCheckboxIsSelected(CHECKBOX_TEXT.get(2));

        //7. Select radio
        actionStep.selectRadio(RADIO_TEXT.get(3));
        assertionStep.checkRadioIsSelected(RADIO_TEXT.get(3));

        //8. Select in dropdown
        actionStep.selectInDropDown(DROPDOWN_TEXT.get(3));
        assertionStep.checkOptionInDropDownIsSelected(DROPDOWN_TEXT.get(3));

        //9. Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        //   Assert that for radiobutton there is a logList row and value is corresponded to the status of radiobutton.
        //   Assert that for dropdown there is a logList row and value is corresponded to the selected value.
        List<String> expectedLog = new ArrayList<>(
                List.of(
                        CHECKBOX_LOG + CHECKBOX_TEXT.get(0),
                        CHECKBOX_LOG + CHECKBOX_TEXT.get(2),
                        RADIO_LOG + RADIO_TEXT.get(3),
                        DROPDOWN_LOG + DROPDOWN_TEXT.get(2)
                ));

        assertionStep.checkLogContain(expectedLog);
    }
}

