package lesson7.waiters;

import driver_factory.DriverSetUp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FluentWaitClass {
    public static void main(String[] args) {
        //Свободные ожидания
        WebDriver driver = DriverSetUp.setUpDriver();
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(InvalidElementStateException.class)
                .ignoring(NoAlertPresentException.class)
                .ignoring(NoSuchFrameException.class);
        //visibilityOfElementLocated
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accept-choices"))).click();
    }
}
