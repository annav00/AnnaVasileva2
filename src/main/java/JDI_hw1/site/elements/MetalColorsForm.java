package JDI_hw1.site.elements;

import JDI_hw1.entities.MetalColorsEntity;
import com.epam.jdi.light.elements.complex.Checklist;
import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.ui.html.elements.common.Button;

@FindBy(className = "uui-main-container page-inside")
public class MetalColorsForm extends Form<MetalColorsEntity> {
    @FindBy(id = "summary-block]")
    private SummaryBlock summary;

    @FindBy(css = "div[id=elements-block] input")
    private Checklist elements;

    @JDropdown(root = "div[ui=dropdown]",
            value = ".filter-option",
            list = "li",
            expand = ".caret")
    private Dropdown colors;

    @JDropdown(root = "div[ui=combobox]",
            value = ".filter-option",
            list = "li",
            expand = ".caret")
    private Dropdown metals;

    @JDropdown(root = "div[ui=droplist]",
            //value = ".filter-option",
            list = "li",
            expand = ".caret")
    private Dropdown vegetables;

    @FindBy(id = "submit-button")
    private Button submit;

    private static final String DEFAULT_VEGETABLES = "Vegetables";

    private void clearForm() {
        vegetables.select(DEFAULT_VEGETABLES);
        elements.uncheckAll();
    }

    @Override
    public void submit(MetalColorsEntity entity) {
        clearForm();

        summary.getOddRadio().select(entity.getSummary().get(0).toString());
        summary.getEvenRadio().select(entity.getSummary().get(1).toString());
        elements.select(entity.getElements().toArray(String[]::new));
        colors.select(entity.getColor());
        metals.select(entity.getMetals());
        entity.getVegetables().forEach(vegetable -> vegetables.select(vegetable));

        this.submit.click();
    }
}
