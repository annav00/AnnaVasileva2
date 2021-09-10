package Selenium_hw4.pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;

import java.util.List;

@FindBy(className = "main-content-hg")
public class DifferentElements extends AbstractComponent {

    @FindBy(className = "label-checkbox")
    private List<WebElement> checkBoxesInput;

    @FindBy(className = "label-radio")
    private List<WebElement> radioButtonsInput;

    @FindBy(tagName = "select")
    private WebElement dropDown;

    private static final By inputInLabelLocator = By.tagName("input");

    public DifferentElements(WebDriver webDriver) {
        super(webDriver);
    }

    public Select getDropDown(String name) {
        Select dropDownButton = new Select(dropDown);
        dropDownButton.selectByVisibleText(name);
        return dropDownButton;
    }

    public CheckBox getCheckBox(String name) {
        return checkBoxesInput
                .stream()
                .filter(x -> x.getText().contains(name))
                .findFirst()
                .map(webElement -> webElement.findElement(inputInLabelLocator))
                .map(CheckBox::new)
                .orElse(null);
    }

    public Radio getRadio(String name) {
        return radioButtonsInput
                .stream()
                .filter(x -> x.getText().contains(name))
                .findFirst()
                .map(webElement -> webElement.findElement(inputInLabelLocator))
                .map(Radio::new)
                .orElse(null);
    }
}

