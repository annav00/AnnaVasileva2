package JDI_hw1.tests;

import JDI_hw1.site.JdiLightSite;
import com.epam.jdi.light.driver.WebDriverUtils;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.init.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static JDI_hw1.data.UserData.DEFAULT_USER_ROMAN;
import static JDI_hw1.data.UserData.USER_NAME;
import static JDI_hw1.site.JdiLightSite.homePage;
import static JDI_hw1.site.JdiLightSite.metalsColorsPage;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        PageFactory.initElements(JdiLightSite.class);
    }

    @BeforeMethod(alwaysRun = true)
    public void logIn() {
        if(!metalsColorsPage.isOpened()) {
            homePage.open();
            homePage.getHeader().loginAs(DEFAULT_USER_ROMAN);
            homePage.getHeader().getUserName().is().text(USER_NAME);
            homePage.getHeader().getMetalColors().click();
        }
    }

    @AfterMethod
    void clearForm(){
        WebPage.refresh();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        WebDriverUtils.killAllSeleniumDrivers();
    }
}
