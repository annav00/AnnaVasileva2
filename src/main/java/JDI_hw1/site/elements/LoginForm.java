package JDI_hw1.site.elements;

import JDI_hw1.entities.UserEntity;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import org.openqa.selenium.WebElement;

public class LoginForm extends Form<UserEntity> {

    @FindBy(id = "name")
    public static WebElement name;

    @FindBy(id = "password")
    public static WebElement password;

    @FindBy(id = "login-button")
    public static WebElement loginButton;
}
