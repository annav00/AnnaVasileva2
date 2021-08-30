package Selenium_hw3.ex2;

import Selenium_hw3.BaseTestClass;
import Selenium_hw3.pages.DifferentElementsPage;
import Selenium_hw3.pages.HomePage;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;


public class DifferentElementsTest extends BaseTestClass {

    @Test
    public void checkDifferentElementsTest() {
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


        //5. Open through the header menu Service -> Different Elements Page
        DifferentElementsPage differentElementsPage = homePage.getHeader().clickDifferentElement();
        softAssertions
                .assertThat(differentElementsPage.getURL())
                .isEqualTo(properties.getString("diffURL"));

        //6. Select checkboxes
        CheckBox checkBoxWater =  differentElementsPage.getDifferentElements().getCheckBox(properties.getList("checkbox").get(0).toString());
        CheckBox checkBoxWind = differentElementsPage.getDifferentElements().getCheckBox(properties.getList("checkbox").get(2).toString());

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
        Radio radio = differentElementsPage.getDifferentElements().getRadio(properties.getList("radio").get(3).toString());
        softAssertions
                .assertThat(radio)
                .isNotNull();
        radio.click();
        softAssertions
                .assertThat(radio.isSelected())
                .isTrue();

        //8. Select in dropdown
        Select dropdown = differentElementsPage.getDifferentElements().getDropDown(properties.getList("dropdown").get(3).toString());
        softAssertions
                .assertThat(dropdown)
                .isNotNull();
        softAssertions
                .assertThat(dropdown.getFirstSelectedOption().getText())
                .isEqualTo(properties.getList("dropdown").get(3).toString());

        //9. Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox.
        //   Assert that for radiobutton there is a logList row and value is corresponded to the status of radiobutton.
        //   Assert that for dropdown there is a logList row and value is corresponded to the selected value.
        softAssertions
                .assertThat(differentElementsPage.getLogPanel().findLogs(
                properties.getString("radio.log") + properties.getList("radio").get(3).toString()))
                .isNotNull();

        softAssertions
                .assertThat(differentElementsPage.getLogPanel().findLogs(
                        properties.getString("checkbox.log") + properties.getList("checkbox").get(0).toString()))
                .isNotNull();

        softAssertions
                .assertThat(differentElementsPage.getLogPanel().findLogs(
                        properties.getString("checkbox.log") + properties.getList("checkbox").get(2).toString()))
                .isNotNull();

        softAssertions
                .assertThat(differentElementsPage.getLogPanel().findLogs(
                        properties.getString("dropdown.log") + properties.getList("dropdown").get(3).toString()))
                .isNotNull();


        //AssertAll
        softAssertions.assertAll();
    }
}

