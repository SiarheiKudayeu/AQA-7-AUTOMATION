package lesson5.drag_n_drop;

import driver_factory.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class DragNDropEx1 {

public static void dragNDropIt(By source, By target, WebDriver driver){
    Actions actions = new Actions(driver);
    actions
            .moveToElement(driver.findElement(source))
            .pause(Duration.ofSeconds(2))
            .clickAndHold()
            .pause(Duration.ofSeconds(2))
            .moveToElement(driver.findElement(target))
            .pause(Duration.ofSeconds(2))
            .release().build().perform();
}
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverSetUp.setUpDriver();

        By centralButton = By.id("menu-btn");
        By courrier = By.xpath("//div[@data-title=\"Courrier\"]");
        By projets = By.xpath("//div[@data-title=\"Projets\"]");
        By propos = By.xpath("//div[@data-title=\"À propos\"]");

        Actions actions = new Actions(driver);

        driver.get("https://www.signesduquotidien.org/");
        Thread.sleep(4000);
        driver.findElement(centralButton).click();
        Thread.sleep(2500);
        actions
                .clickAndHold(driver.findElement(courrier))
                .moveToElement(driver.findElement(centralButton))
                .release()
                .build().perform();
        Thread.sleep(2500);

        driver.get("https://www.signesduquotidien.org/");
        Thread.sleep(4000);
        driver.findElement(centralButton).click();
        Thread.sleep(2500);
        actions.dragAndDrop(driver.findElement(projets), driver.findElement(centralButton)).perform();
        Thread.sleep(2500);

        driver.get("https://www.signesduquotidien.org/");
        Thread.sleep(4000);
        driver.findElement(centralButton).click();
        Thread.sleep(2500);
        dragNDropIt(propos, centralButton, driver);
        Thread.sleep(2500);
        driver.quit();


    }
}
