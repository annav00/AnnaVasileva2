package Selenium_hw1;

import com.epam.tat.module4.Calculator;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SubtractTests {
    private Calculator calculator;

    @BeforeClass
    void initCalculator() {
        calculator = new Calculator();
    }

    @AfterClass
    void cleanCalculator() {
        calculator = null;
    }

    @Test(dataProvider = "longData", dataProviderClass = DataProviderCalc.class)
    public void addLongTest(long a, long b) {
        Assertions.assertThat(calculator.sub(a,b)).as("Error in subtract long").isEqualTo(a - b);
    }

    @Test(dataProvider = "doubleData", dataProviderClass = DataProviderCalc.class)
    public void addDoubleTest(double a, double b) {
        Assertions.assertThat(calculator.sub(a,b)).as("Error in subtract double").isEqualTo(a - b);
    }
}
