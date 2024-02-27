package lesson5.actions;

import driver_factory.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionExampleClass {
    public enum Courses{
        DEVOPS, FRONTEND, JAVA
    }

    //DevOps Java FrontEnd

    public static void openCourseFromMainPage(Courses courses, WebDriver driver){
        Actions actions = new Actions(driver);
        String course = "";
        switch (courses){
            case JAVA -> course = "Java";
            case DEVOPS -> course = "DevOps";
            case FRONTEND -> course = "FrontEnd";
        }
        actions.moveToElement(driver.findElement(By.xpath("(//a[contains(@href,'adults/')][@class='header__nav-item-link'])[1]")))
                .moveToElement(driver.findElement(By.xpath("//a[text()='Програмування']")))
                .click(driver.findElement(By.xpath("//a[text()='"+course+"']"))).build().perform();

    }

    public static void goToMainPage(WebDriver driver){
        driver.get("https://dan-it.com.ua/uk/");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverSetUp.setUpDriver();
        //moveToElement()
       /* driver.get("https://dan-it.com.ua/uk/");
        Actions actions = new Actions(driver);*/
/*
        actions.moveToElement(driver.findElement(By.xpath("(//a[contains(@href,'adults/')][@class='header__nav-item-link'])[1]"))).perform();
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Програмування']"))).perform();
        driver.findElement(By.xpath("//a[text()='Java']")).click();
*/
/*
        actions.moveToElement(driver.findElement(By.xpath("(//a[contains(@href,'adults/')][@class='header__nav-item-link'])[1]")))
                .moveToElement(driver.findElement(By.xpath("//a[text()='Програмування']")))
                        .click(driver.findElement(By.xpath("//a[text()='Java']"))).build().perform();*/



        goToMainPage(driver);
        openCourseFromMainPage(Courses.FRONTEND, driver);
        Thread.sleep(1500);
        System.out.println(driver.getTitle());
        System.out.println("==============");
        goToMainPage(driver);
        openCourseFromMainPage(Courses.JAVA, driver);
        Thread.sleep(1500);
        System.out.println(driver.getTitle());
        System.out.println("==============");
        goToMainPage(driver);
        openCourseFromMainPage(Courses.DEVOPS, driver);
        Thread.sleep(1500);
        System.out.println(driver.getTitle());
        System.out.println("==============");
        driver.quit();

    }
}
