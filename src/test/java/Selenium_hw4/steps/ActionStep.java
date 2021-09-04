package Selenium_hw4.steps;

import Selenium_hw4.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;

public class ActionStep extends AbstractStep {

    public ActionStep(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Open Open test site by URL {URL}")
    public void openHomePage(String URL) {
        homePage.open(URL);
    }

    @Step("Perform login: {login} : {password}")
    public void performLogin(String login, String password) {
        homePage.getHeader().signIn(login, password);
    }

    @Step("Switch to the iframe")
    public void switchToIframe() {
        homePage.switchToFrame();
    }

    @Step("Switch to original window back")
    public void switchToOriginalWindow() {
        homePage = (HomePage) homePage.getFrame().switchToDefault();
    }

    @Step("Open through the header menu Service -> Different Elements Page ")
    public void openDifferentElementsPageThroughHeaderMenu() {
        differentElementsPage = homePage.getHeader().clickDifferentElement();
    }

    @Step("Select checkboxes: '{checkBoxText}'")
    public void selectCheckBox(String checkBoxText) {
        CheckBox checkBox =  differentElementsPage.getDifferentElements().getCheckBox(checkBoxText);
        checkBox.click();
    }

    @Step("Select radio: '{radioText}'")
    public void selectRadio(String radioText) {
        Radio radio = differentElementsPage.getDifferentElements().getRadio(radioText);
        radio.click();
    }

    @Step("Select in dropdown: {dropdownText}")
    public void selectInDropDown(String dropdownText) {
        Select dropdown = differentElementsPage.getDifferentElements().getDropDown(dropdownText);
    }
}
