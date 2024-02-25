package lesson4.driverMethods;

import driver_factory.DriverSetUp;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowHandleExample {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverSetUp.setUpDriver();
        driver.get("https://uhomki.prom.ua/ua/");
        System.out.println(driver.getWindowHandles());
        Set<String> set1 = driver.getWindowHandles();

        ((JavascriptExecutor)driver).executeScript("window.open()");
        Thread.sleep(3000);
        Set<String> set2 = driver.getWindowHandles();
        System.out.println(driver.getWindowHandles());
        set2.removeAll(set1);
        String windowDescriptor2 = set2.iterator().next();
        System.out.println(windowDescriptor2);

        driver.switchTo().window(windowDescriptor2);
        driver.get("http://www.automationpractice.pl/index.php");
        Thread.sleep(4000);
        driver.quit();
    }
}
