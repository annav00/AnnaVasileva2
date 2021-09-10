package JDI_hw1.site.pages;

import JDI_hw1.site.elements.Header;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import lombok.Getter;

@Url("/index.html")
@Title("Home page")
@Getter
public class HomePage extends WebPage {
    private Header header;
}
