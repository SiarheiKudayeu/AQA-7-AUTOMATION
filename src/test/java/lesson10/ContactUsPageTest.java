package lesson10;

import driver_factory.DriverSetUp3;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.MyTestListeners;


public class ContactUsPageTest {
    private  WebDriver driver;
    private lesson10.ContactUsPage contactUsPage;

    @BeforeClass
    public void setUp(){
        driver = DriverSetUp3.startDriver();
        contactUsPage = new lesson10.ContactUsPage(driver);
    }
    @AfterClass
    public void stop(){
        driver.quit();
    }

    @Test
    public void sendMessagePositive(){
        String textToSend = "My text";
        contactUsPage
                .openContactUsPage()
                .selectOptionFromSubjectHeading(lesson10.ContactUsPage.SubjectHeader.WEBMASTER)
                .setValidEmail()
                .setOrderId()
                .enterMessage(textToSend)
                .clickSendButton()
                .waitForResultMessage();
        Assert.assertEquals(contactUsPage.getSuccessMessage(), contactUsPage.validSuccessMessage());
    }
    @Test
    public void sendMessagePositiveWithoutOrderId(){
        String textToSend = "My text";
        contactUsPage
                .openContactUsPage()
                .selectOptionFromSubjectHeading(lesson10.ContactUsPage.SubjectHeader.WEBMASTER)
                .setValidEmail()
                .enterMessage(textToSend)
                .clickSendButton()
                .waitForAlertMessage();
        Assert.assertEquals(contactUsPage.getSuccessMessage(), contactUsPage.validSuccessMessage());
    }


    @Test
    public void sendMessageNegativeEmptyMessage(){
        contactUsPage
                .openContactUsPage()
                .setValidEmail()
                .selectOptionFromSubjectHeading(lesson10.ContactUsPage.SubjectHeader.CUSTOM)
                .setOrderId()
                .clickSendButton()
                .waitForAlertMessage();
        Assert.assertEquals(contactUsPage.getAlertMessage(), contactUsPage.alertResultMessage(ContactUsPage.Alert.MESSAGE));
    }
    @Test
    public void sendMessageNegativeInvalidMail(){
        String textToSend = "My text";
        contactUsPage
                .openContactUsPage()
                .selectOptionFromSubjectHeading(lesson10.ContactUsPage.SubjectHeader.WEBMASTER)
                .setInvalidEmail()
                .setOrderId()
                .enterMessage(textToSend)
                .clickSendButton()
                .waitForAlertMessage();
        Assert.assertEquals(contactUsPage.getAlertMessage(), contactUsPage.alertResultMessage(lesson10.ContactUsPage.Alert.EMAIL));
    }
    @Test
    public void sendMessageNegativeNoChose(){
        String textToSend = "My text";
        contactUsPage
                .openContactUsPage()
                .setValidEmail()
                .setOrderId()
                .enterMessage(textToSend)
                .clickSendButton()
                .waitForAlertMessage();
        Assert.assertEquals(contactUsPage.getAlertMessage(), contactUsPage.alertResultMessage(lesson10.ContactUsPage.Alert.CHOSE));
    }
}
