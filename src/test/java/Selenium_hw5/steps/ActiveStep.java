package Selenium_hw5.steps;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ActiveStep extends AbstractStep {

    @When("I click on {string} button in Header")
    public void clickOnButtonInHeader(String buttonName){
        homePage.getHeader().getHeaderElement(buttonName).click();
    }

    @When("I click on {string} button in Service dropdown")
    public void clickOnButtonInServiceDropdown(String buttonName){
        homePage.getHeader().getServiceElement(buttonName).click();
    }

    @When("I select checkBoxes")
    public void selectCheckBox(List<String> checkBoxText){
        checkBoxText.forEach(checkBox -> differentElementsPage.getDifferentElements().getCheckBox(checkBox).click());
    }

    @When("I select radio buttons {string}")
    public void selectRadio(String radioText){
        differentElementsPage.getDifferentElements().getRadio(radioText).click();
    }

    @When("I select dropdown {string}")
    public void selectInDropDown(String dropdownText){
        differentElementsPage.getDifferentElements().getDropDown(dropdownText);
    }

    @When("I select 'vip' checkbox for {string}")
    public void clickOnVipCheckboxForUser(String userName){
        userTablePage.getUserTable().createUserTable().forEach(line -> {
                    if(line.get(1).equals(userName)) {
                        WebElement checkbox = userTablePage.getUserTable().getCheckboxForUser(Integer.parseInt(line.get(0)));
                        checkbox.click();
                        if(!checkbox.isSelected())
                            checkbox.click();
                    }
                }
        );
    }
}
