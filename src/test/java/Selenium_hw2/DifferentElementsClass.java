package Selenium_hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.Radio;

import java.util.List;

public class DifferentElementsClass {
    private static final By inputInLabelLocator = By.tagName("input");

    public WebElement getCheckBox(List<WebElement> checkBoxList, String name) {
        return checkBoxList
                .stream()
                .filter(x -> x.getText().contains(name))
                .findFirst()
                .map(webElement -> webElement.findElement(inputInLabelLocator))
                .map(CheckBox::new)
                .orElse(null);
    }

    public WebElement getRadio(List<WebElement> RadioList, String name) {
        return RadioList
                .stream()
                .filter(x -> x.getText().contains(name))
                .findFirst()
                .map(webElement -> webElement.findElement(inputInLabelLocator))
                .map(Radio::new)
                .orElse(null);
    }

    public Select getDropDown(List<WebElement> RadioList, String name) {
        Select dropDownButton = new Select(RadioList.get(0));
        dropDownButton.selectByVisibleText(name);
        return dropDownButton;
    }
}
