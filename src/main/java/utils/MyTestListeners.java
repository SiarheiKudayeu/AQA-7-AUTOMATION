package utils;

import driver_factory.DriverSetUp3;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class MyTestListeners implements ITestListener {

    public static void makeScreenshot(String method){
        File screenshotFile = ((TakesScreenshot) DriverSetUp3.startDriver()).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenshotFile, new File("./target/screenshots/" + method + ".png"));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test with name " + result.getMethod().getMethodName() + " started!!!");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenshot(result.getMethod().getMethodName());
    }
}
