package lesson4.driverMethods;

import driver_factory.DriverSetUp;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowHandleExample2 {
    public static void openLinkInNewTab(WebDriver driver, String url){
        Set<String> set1 = driver.getWindowHandles();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        Set<String> set2 = driver.getWindowHandles();
        set2.removeAll(set1);
        String windowDescriptor2 = set2.iterator().next();
        driver.switchTo().window(windowDescriptor2);
        driver.get(url);
    }

    /*  Открыть в браузере три различные вкладки.
    На каждой вкладке открыть отдельно различные любые страницы,
    вывести в консоль названия открытых страниц. Закрыть браузер.*/
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverSetUp.setUpDriver();
        driver.get("https://uhomki.prom.ua/ua/");
        openLinkInNewTab(driver, "https://rozetka.com.ua/");
        openLinkInNewTab(driver, "http://www.automationpractice.pl/index.php");
        Set<String> windowHandles = driver.getWindowHandles();
        for(String windowId: windowHandles){
            driver.switchTo().window(windowId);
            System.out.println(driver.getTitle());
        }
        driver.quit();
    }
}
