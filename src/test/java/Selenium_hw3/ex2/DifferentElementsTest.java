package Selenium_hw3.ex2;

import Selenium_hw3.BaseTestClass;
import Selenium_hw3.pages.DifferentElementsPage;
import Selenium_hw3.pages.HomePage;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;

import java.util.ArrayList;
import java.util.List;

import static Selenium_hw3.DataClass.*;

public class DifferentElementsTest extends BaseTestClass {

    @Test
    public void checkDifferentElementsTest() {
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


        //5. Open through the header menu Service -> Different Elements Page
        DifferentElementsPage differentElementsPage = homePage.getHeader().clickDifferentElement();
        softAssertions
                .assertThat(differentElementsPage.getURL())
                .isEqualTo(properties.getString("diffURL"));

        //6. Select checkboxes
        CheckBox checkBoxWater =  differentElementsPage.getDifferentElements().getCheckBox(CHECKBOX_TEXT.get(0));
        CheckBox checkBoxWind = differentElementsPage.getDifferentElements().getCheckBox(CHECKBOX_TEXT.get(2));

        softAssertions
                .assertThat(checkBoxWater)
                .isNotNull();
        softAssertions
                .assertThat(checkBoxWind)
                .isNotNull();

        checkBoxWater.click();
        checkBoxWind.click();

        softAssertions
                .assertThat(checkBoxWater.isSelected())
                .isTrue();
        softAssertions
                .assertThat(checkBoxWind.isSelected())
                .isTrue();

        //7. Select radio
        Radio radio = differentElementsPage.getDifferentElements().getRadio(RADIO_TEXT.get(3));
        softAssertions
                .assertThat(radio)
                .isNotNull();
        radio.click();
        softAssertions
                .assertThat(radio.isSelected())
                .isTrue();

        //8. Select in dropdown
        Select dropdown = differentElementsPage.getDifferentElements().getDropDown(DROPDOWN_TEXT.get(3));
        softAssertions
                .assertThat(dropdown)
                .isNotNull();
        softAssertions
                .assertThat(dropdown.getFirstSelectedOption().getText())
                .isEqualTo(DROPDOWN_TEXT.get(3));

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

        expectedLog.forEach(expLog ->
                softAssertions
                        .assertThat(differentElementsPage.getLogPanel().findLogs(expLog))
                        .isNotNull()
        );

        //AssertAll
        softAssertions.assertAll();
    }
}

