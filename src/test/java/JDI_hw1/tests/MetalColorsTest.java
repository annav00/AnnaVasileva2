package JDI_hw1.tests;

import JDI_hw1.entities.MetalColorsEntity;
import JDI_hw1.utils.JsonDataProvider;
import org.testng.annotations.Test;

import static JDI_hw1.site.JdiLightSite.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MetalColorsTest extends BaseTest{

    @Test(dataProvider = "metalColorsEntities", dataProviderClass = JsonDataProvider.class)
    public void metalColorsFormTest(MetalColorsEntity metalColorsEntity){
        assertThat(metalsColorsPage.isOpened()).isTrue();
        metalsColorsPage.getMetalsColorsForm().submit(metalColorsEntity);
        assertThat(metalsColorsPage.getResultAsListString())
                .isEqualTo(metalColorsEntity.getEntitytAsListString());
    }
}
