package lesson6.task_to_remember;

import driver_factory.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HoverActionClass {
    public static void main(String[] args) throws InterruptedException {
                   /*
        Открыть страницу https://www.guinnessworldrecords.com/records/apply-to-set-or-break-a-record/
        навести курсор на "RECORDS", навести курсор на "APPLY TO SET OR BREAK A RECORD", открыть "Standard Applications"
        в новой вкладке. Перейти на новую вкладку и вывести в консоль название страницы.
        */
        //Standard applications | Guinness World Records
        WebDriver driver = DriverSetUp.setUpDriver();

        driver.get("https://www.guinnessworldrecords.com/records/apply-to-set-or-break-a-record/");
        Thread.sleep(1500);
        Actions actions = new Actions(driver);
        actions
                .moveToElement(driver.findElement(By.xpath("//a[@href='/records/']")))
                .moveToElement(driver.findElement(By.xpath("//a[contains(@href,'a-record/')]")))
                .click(driver.findElement(By.xpath("(//a[contains(@href,'standard-applications')])[1]")))
                .build().perform();
        Thread.sleep(1500);
        boolean result = driver.getTitle().equals("Standard applications | Guinness World Records");
        System.out.println("test status is " + result);
        driver.quit();
    }
}
