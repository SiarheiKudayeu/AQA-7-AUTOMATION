package lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public enum SubjectHeader{
        CUSTOM, WEBMASTER
    }
    public enum Alert{
        EMAIL, CHOSE, MESSAGE
    }


    private static class Locators{
        private final static By subjectHeading = By.id("id_contact");
    }
    private static class Strings{
        private final static String successMessage = "Your message has been successfully sent to our team.";
        private final static String selectOption1 = "Customer service";
        private final static String selectOption2 = "Webmaster";
        private final static String url = "http://www.automationpractice.pl/index.php?controller=contact";
        private final static String validMail = "abc@abc.abc";
        private final static String invalidMail = "abcabc";
        private final static String orderId = "1234567";
        private final static String alertInvalidEmailMessage = "Invalid email address.";
        private final static String alertInvalidChooseMessage = "Please select a subject from the list provided.";
        private final static String alertInvalidEmptyMessage = "The message cannot be blank.";

    }

    @FindBy(id = "id_contact")
    private static WebElement subjectHeading;

    @FindBy(id = "email")
    private static WebElement email;

    @FindBy(id = "id_order")
    private static WebElement orderId;

    @FindBy(id = "message")
    private static WebElement message;

    @FindBy(id = "submitMessage")
    private static WebElement buttonSend;

    @FindBy(css = ".alert.alert-success")
    private static WebElement successInfoMessage;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private static WebElement alertMessage;

    public ContactUsPage selectOptionFromSubjectHeading(SubjectHeader subjectHeader){
        Select select = new Select(subjectHeading);
        switch (subjectHeader){
            case WEBMASTER -> select.selectByVisibleText(Strings.selectOption2);
            case CUSTOM -> select.selectByVisibleText(Strings.selectOption1);
        }
        return this;
    }
    public ContactUsPage openContactUsPage(){
        driver.get(Strings.url);
        return this;
    }
    public ContactUsPage setValidEmail(){
        email.sendKeys(Strings.validMail);
        return this;
    }
    public ContactUsPage setInvalidEmail(){
        email.sendKeys(Strings.invalidMail);
        return this;
    }

    public ContactUsPage setOrderId(){
        orderId.sendKeys(Strings.orderId);
        return this;
    }
    public ContactUsPage enterMessage(String text){
        message.sendKeys(text);
        return this;
    }
    public ContactUsPage clickSendButton(){
        buttonSend.click();
        return this;
    }
    public ContactUsPage waitForResultMessage(){
        wait.until(ExpectedConditions.visibilityOf(successInfoMessage));
        return this;
    }

    public ContactUsPage waitForAlertMessage(){
        wait.until(ExpectedConditions.visibilityOf(alertMessage));
        return this;
    }

    public String getSuccessMessage(){
        return successInfoMessage.getText();
    }
    public String getAlertMessage(){
        return alertMessage.getText();
    }
    public String validSuccessMessage(){
        return Strings.successMessage;
    }
    public String alertResultMessage(Alert alert){
        String result ="";
        switch (alert){
            case CHOSE -> result = Strings.alertInvalidChooseMessage;
            case EMAIL -> result = Strings.alertInvalidEmailMessage;
            case MESSAGE -> result = Strings.alertInvalidEmptyMessage;
        }
        return result;
    }

}
