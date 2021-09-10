package JDI_hw1.site;

import JDI_hw1.site.pages.HomePage;
import JDI_hw1.site.pages.MetalColorsPage;
import com.epam.jdi.light.elements.pageobjects.annotations.JSite;

@JSite("https://jdi-testing.github.io/jdi-light")
public class JdiLightSite {

    public static HomePage homePage;
    public static MetalColorsPage metalsColorsPage;
}
