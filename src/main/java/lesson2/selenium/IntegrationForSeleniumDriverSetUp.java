package lesson2.selenium;

import driver_factory.DriverSetUp;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IntegrationForSeleniumDriverSetUp {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverSetUp.setUpDriver();
        driver.get("https://www.google.com/");
        Thread.sleep(1500);
        try{
            driver.findElement(By.xpath("//div[text()='Принять все']")).click();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        Thread.sleep(1500);
        driver.quit();
    }
}
