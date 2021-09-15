package JDI_hw1.site.pages;

import JDI_hw1.site.elements.Header;
import JDI_hw1.site.elements.MetalColorsForm;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Url("/metals-colors.html")
@Title("Metal and Colors")
@Getter
public class MetalColorsPage extends WebPage {

    private Header header;
    private MetalColorsForm metalsColorsForm;

    @FindBy(css = "ul[class=\"panel-body-list results\"]>li")
    public WebList results;

    public List<String> getResultAsListString() {
        return this.getResults()
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }
}
