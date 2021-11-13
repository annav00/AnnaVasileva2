package Selenium_hw5.pageComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@FindBy(css = "table#user-table")
public class UserTable extends AbstractComponent{

    @FindBy(css = "tbody tr")
    private List<WebElement> tableLine;

    @FindBy(xpath = "//tr/td[1]")
    private List<WebElement> numberTypeDropdowns;

    @FindBy(css = "td>a")
    private List<WebElement> userNames;

    @FindBy(css = "td>select")
    private List<WebElement> dropdowns;

    @FindBy(css = "td>div>span")
    private List<WebElement> description;

    @FindBy(css = "td>div>input")
    private List<WebElement> checkboxes;

    public UserTable(WebDriver webDriver) {
        super(webDriver);
    }

    public List<WebElement> getNumberTypeDropdowns(){
        return numberTypeDropdowns;
    }

    public List<WebElement> getUserNames(){
        return userNames;
    }

    public List<WebElement> getCheckboxes(){
        return checkboxes;
    }

    public List<WebElement> getDescription(){
        return description;
    }

    public List<WebElement> getDropdown(){
        return dropdowns;
    }

    public List<List<String>> createUserTable() {
        List<List<String>> userTable = new ArrayList<>();

        for (int i = 0; i < numberTypeDropdowns.size(); i++) {
            userTable.add(new ArrayList<>(
                    List.of(numberTypeDropdowns.get(i).getText(),
                            userNames.get(i).getText(),
                            description.get(i).getText().replace("\n"," "))));
        }

        return userTable;
    }

    public List<String> getDropdownOptionForUser(int indexOfUser){
        Select dropdown = new Select(dropdowns.get(indexOfUser));
        return dropdown
                .getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getCheckboxForUser(int indexOfUser) {
        return checkboxes.get(indexOfUser);
    }
}
