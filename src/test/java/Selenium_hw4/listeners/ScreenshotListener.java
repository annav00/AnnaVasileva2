package Selenium_hw4.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver webDriver = (WebDriver) result.getTestContext().getAttribute("webDriver");
        if (webDriver != null) {
            attachScreenShot(webDriver);
        }
    }

    private byte[] attachScreenShot(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }
}
