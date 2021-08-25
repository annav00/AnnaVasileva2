package hw1;

import com.epam.tat.module4.Calculator;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddTests {
    private Calculator calculator;

    @BeforeClass
    void initCalculator() {
        calculator = new Calculator();
    }

    @AfterClass
    void cleanCalculator() {
        calculator = null;
    }

    @Test(dataProvider = "longData", dataProviderClass = DataProviderCalc.class, groups = "addSubtract")
    public void sumLongTest(long a, long b) {
        Assertions.assertThat(calculator.sub(a,b)).as("Error in adding long").isEqualTo(a + b);
    }

    @Test(dataProvider = "doubleData", dataProviderClass = DataProviderCalc.class, groups = "addSubtract")
    public void sumDoubleTest(double a, double b) {
        Assertions.assertThat(calculator.sub(a,b)).as("Error in adding double").isEqualTo(a + b);
    }
}