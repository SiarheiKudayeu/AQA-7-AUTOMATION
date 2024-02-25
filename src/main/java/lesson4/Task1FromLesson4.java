package lesson4;

import driver_factory.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Task1FromLesson4 {

    @FindBy(xpath = "(//button[contains(@class,'header__button ng-star-inserted')])[1]")
    private static WebElement loginWindowButton;

    @FindBy(id = "auth_email")
    private static WebElement loginField;

    @FindBy(id = "auth_pass")
    private static WebElement passwordField;

    @FindBy(css = ".auth-modal__register-link.button.button--link.ng-star-inserted")
    private static WebElement loginButton;
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverSetUp.setUpDriver();
        Task1FromLesson4 page = new Task1FromLesson4();
        PageFactory.initElements(driver, page);
        driver.get("https://rozetka.com.ua/");
        loginWindowButton.click();
        Thread.sleep(2000);
        loginField.sendKeys("email@email.com");
        passwordField.sendKeys("password");
        Thread.sleep(2000);
        System.out.println(loginButton.getText());
        Thread.sleep(2000);
        driver.quit();
    }

}
