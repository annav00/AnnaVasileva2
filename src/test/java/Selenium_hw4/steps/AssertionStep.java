package Selenium_hw4.steps;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;

import java.util.List;

public class AssertionStep extends AbstractStep {

    public AssertionStep(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Check if Browser title equal to {title}")
    public void checkPageTitle(String title) {
        Assertions
                .assertThat(homePage.getTitle())
                .isEqualTo(title);
    }

    @Step("Check if username is loggined by {expectedUserName}")
    public void usernameIsLoggined(String expectedUserName) {
        Assertions
                .assertThat(homePage.getHeader().isNameDisplayed())
                .isTrue();
        Assertions
                .assertThat(homePage.getHeader().getName())
                .isEqualTo(expectedUserName);
    }

    @Step("Check that there are {expectedSize} items on header")
    public void checkNumberOfItemsOnHeader(int expectedSize) {
        Assertions
                .assertThat(homePage.getHeader().getHeaderElementsText())
                .hasSize(expectedSize);
    }

    @Step("Check that items on header have proper name {expectedName}")
    public void checkNameItemsOnHeader(List<String> expectedName) {
        Assertions
                .assertThat(homePage.getHeader().getHeaderElementsText())
                .isEqualTo(expectedName);
    }

    @Step("Check that there are {expectedSize} images on the Index Page")
    public void checkNumberOfBenefitImages(int expectedSize) {
        Assertions
                .assertThat(homePage.getBenefit().getBenefitIconsNumber())
                .isEqualTo(expectedSize);
    }

    @Step("Check that images on the Index Page are displayed")
    public void checkIfDisplayedBenefitImages() {
        SoftAssertions softAssertions = new SoftAssertions();
        homePage.getBenefit().getBenefitIcons()
                .forEach(element ->
                        softAssertions
                                .assertThat(element.isDisplayed())
                                .isTrue());
        softAssertions.assertAll();
    }

    @Step("Check that there are {expectedSize} texts on the Index Page under icons")
    public void checkNumberOfBenefitText(int expectedSize) {
        Assertions
                .assertThat(homePage.getBenefit().getBenefitTextsNumber())
                .isEqualTo(expectedSize);
    }

    @Step("Check that texts on the Index Page under icons have proper text: {properText}")
    public void checkBenefitText(List<String> properText) {
        Assertions
                .assertThat(homePage.getBenefit().getBenefitTextsExtracted())
                .isEqualTo(properText);
    }

    @Step("Check that there is the iframe with 'Frame Button' exist")
    public void checkFrameExist() {
        Assertions
                .assertThat(homePage.getFrame().getFrame())
                .isNotNull();
    }

    @Step("Check that there is the iframe with 'Frame Button' displayed")
    public void checkFrameDisplayed() {
        Assertions
                .assertThat(homePage.getFrame().isFrameDisplayed())
                .isTrue();
    }

    @Step("Check that there is the 'Frame Button' on iframe exist")
    public void checkFrameButtonExist() {
        Assertions
                .assertThat(homePage.getFrame().getFrameButton())
                .isNotNull();
    }

    @Step("Check that there is the 'Frame Button' on iframe displayed")
    public void checkFrameButtonDisplayed() {
        Assertions
                .assertThat(homePage.getFrame().isFrameButtonDisplayed())
                .isTrue();
    }

    @Step("Check that there are {expectedSize} items in the Left Section are displayed")
    public void checkNumberOfItemsInLeftSection(int expectedSize) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(homePage.getNavigationBar().getNavigationBarNumber())
                .isEqualTo(expectedSize);
        homePage.getNavigationBar().getNavigationBar()
                .forEach(element ->
                        softAssertions
                                .assertThat(element.isDisplayed())
                                .isTrue());
        softAssertions.assertAll();
    }

    @Step("Check that items in the Left Section have proper text: {properText}")
    public void checkTextInItemsInLeftSection(List<String> properText) {
        Assertions
                .assertThat(homePage.getNavigationBar().getNavigationBarExtracted())
                .isEqualTo(properText);

    }

    @Step("Check that current URL is equal to expected: {expectedDiffURL}")
    public void checkCurrentUrl(String expectedDiffURL) {
        Assertions
                .assertThat(differentElementsPage.getURL())
                .isEqualTo(expectedDiffURL);
    }

    @Step("Check that checkbox {checkBoxText} is selected")
    public void checkCheckboxIsSelected(String checkBoxText) {
        CheckBox checkBox =  differentElementsPage.getDifferentElements().getCheckBox(checkBoxText);
        Assertions
                .assertThat(checkBox.isSelected())
                .isTrue();
    }

    @Step("Check that radio {radioText} is selected")
    public void checkRadioIsSelected(String radioText) {
        Radio radio = differentElementsPage.getDifferentElements().getRadio(radioText);
        Assertions
                .assertThat(radio.isSelected())
                .isTrue();
    }

    @Step("Check that in DropDown option {dropDownText} is selected")
    public void checkOptionInDropDownIsSelected(String dropDownText) {
        Select dropdown = differentElementsPage.getDifferentElements().getDropDown(dropDownText);
        Assertions
                .assertThat(dropdown.getFirstSelectedOption().getText())
                .isEqualTo(dropDownText);
    }

    @Step("Check that logPanel contain logs: {expectedLog}")
    public void checkLogContain(List<String> expectedLog) {
        SoftAssertions softAssertions = new SoftAssertions();
        expectedLog.forEach(expLog ->
                softAssertions
                        .assertThat(differentElementsPage.getLogPanel().findLogs(expLog))
                        .isNotNull()
        );
        softAssertions.assertAll();
    }
}
