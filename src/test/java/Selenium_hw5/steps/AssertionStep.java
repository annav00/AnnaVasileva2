package Selenium_hw5.steps;

import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;
import java.util.List;

import static Selenium_hw5.utils.AssertionUtils.assertNumberAndVisibilityElements;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AssertionStep extends AbstractStep {

    @Then("{string} page should be opened")
    public void checkBrowserTitle(String pageName){
        assertThat(webDriver.getTitle()).isEqualTo(pageName);
    }

    @Then("Username is {string}")
    public void checkUsername(String expectedUsername){
        assertThat(homePage.getHeader().isNameDisplayed()).isTrue();
        assertThat(homePage.getHeader().getName()).isEqualTo(expectedUsername);
    }

    @Then("I see in Log section")
    public void checkLogContain(List<String> expectedLog){
        SoftAssertions softAssertions = new SoftAssertions();
        expectedLog.forEach(expLog -> softAssertions.assertThat(differentElementsPage.getLogPanel().findLog(expLog)).contains(expLog));
        softAssertions.assertAll();
    }

    @Then("{int} Number Type Dropdowns should be displayed on Users Table on User Table Page")
    public void checkNumberTypeDropdowns(int expectedNumberSize){
        assertNumberAndVisibilityElements(userTablePage.getUserTable().getNumberTypeDropdowns(), expectedNumberSize);
    }

    @Then("{int} Usernames should be displayed on Users Table on User Table Page")
    public void checkUsernames(int expectedUserNameSize){
        assertNumberAndVisibilityElements(userTablePage.getUserTable().getUserNames(), expectedUserNameSize);
    }

    @Then("{int} Description texts under images should be displayed on Users Table on User Table Page")
    public void checkDescription(int expectedDescriptionSize){
        assertNumberAndVisibilityElements(userTablePage.getUserTable().getDescription(), expectedDescriptionSize);
    }

    @Then("{int} checkboxes should be displayed on Users Table on User Table Page")
    public void checkCheckboxes(int expectedCheckboxesSize){
        assertNumberAndVisibilityElements(userTablePage.getUserTable().getCheckboxes(), expectedCheckboxesSize);
    }

    @Then("User table should contain following values:")
    public void checkCheckboxes(List<List<String>> dataTable){
        dataTable.remove(0);
        assertThat(userTablePage.getUserTable().createUserTable()).isEqualTo(dataTable);
    }

    @Then("droplist should contain values in column Type for user {string}")
    public void checkDroplistContainValues(String userName, List<String> dropdownDataTable){
        List<String> cloneDropdownDataTable = new ArrayList<>(dropdownDataTable);
        cloneDropdownDataTable.remove(0);

        userTablePage.getUserTable().createUserTable().forEach(line -> {
            if(line.get(1).equals(userName)) {
                assertThat(userTablePage.getUserTable().getDropdownOptionForUser(Integer.parseInt(line.get(0)))).isEqualTo(cloneDropdownDataTable);
            }
        }
        );
    }

    @Then("1 log row has {string} text in log section")
    public void checkLogRowHasTextInLogSection(String expectedLogText){
        assertThat(userTablePage.getLogPanel().findLog(expectedLogText)).contains(expectedLogText);
    }
}
