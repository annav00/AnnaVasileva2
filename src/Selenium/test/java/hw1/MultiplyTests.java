package hw1;

import com.epam.tat.module4.Calculator;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MultiplyTests {
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
    public void multLongTest(long a, long b) {
        Assertions.assertThat(calculator.mult(a,b)).as("Error in multiply long").isEqualTo(a * b);
    }

    @Test(dataProvider = "doubleData", dataProviderClass = DataProviderCalc.class, groups = "multiplyDivision")
    public void multDoubleTest(double a, double b) {
        Assertions.assertThat(calculator.mult(a,b)).as("Error in multiply double").isEqualTo(a * b);
    }
}
