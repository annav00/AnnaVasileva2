package hw1;
import com.epam.tat.module4.Calculator;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DivisionTests {
    private Calculator calculator;

    @BeforeClass
    void initCalculator() {
        calculator = new Calculator();
    }

    @AfterClass
    void cleanCalculator() {
        calculator = null;
    }

    @Test(dataProvider = "longData", dataProviderClass = DataProviderCalc.class, groups = "multiplyDivision")
    public void divLongTest(long a, long b) {
        if (b == 0L) {
            Assertions.assertThatThrownBy(() -> calculator.div(a, b))
                    .isInstanceOf(NumberFormatException.class);
        }
        else {
            Assertions.assertThat(calculator.div(a,b))
                    .as("Error in division long").isEqualTo(a / b);
        }
    }

    @Test(dataProvider = "doubleData", dataProviderClass = DataProviderCalc.class, groups = "multiplyDivision")
    public void divDoubleTest(double a, double b) {
        if (b == 0.0) {
            Assertions.assertThatThrownBy(() -> calculator.div(a, b)).isInstanceOf(NumberFormatException.class);
        } else {
            Assertions.assertThat(calculator.div(a, b))
                    .as("Error in division double").isEqualTo(a / b);
        }
    }
}
