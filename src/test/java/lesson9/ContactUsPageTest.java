package lesson9;

import driver_factory.DriverSetUp3;
import lesson9.pages.ContactUsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.MyTestListeners;

@Listeners({MyTestListeners.class})
public class ContactUsPageTest {
    private  WebDriver driver;
    private ContactUsPage contactUsPage;

    @BeforeClass
    public void setUp(){
        driver = DriverSetUp3.startDriver();
        contactUsPage = new ContactUsPage(driver);
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
                .selectOptionFromSubjectHeading(ContactUsPage.SubjectHeader.WEBMASTER)
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
                .selectOptionFromSubjectHeading(ContactUsPage.SubjectHeader.WEBMASTER)
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
                .selectOptionFromSubjectHeading(ContactUsPage.SubjectHeader.CUSTOM)
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
                .selectOptionFromSubjectHeading(ContactUsPage.SubjectHeader.WEBMASTER)
                .setInvalidEmail()
                .setOrderId()
                .enterMessage(textToSend)
                .clickSendButton()
                .waitForAlertMessage();
        Assert.assertEquals(contactUsPage.getAlertMessage(), contactUsPage.alertResultMessage(ContactUsPage.Alert.EMAIL));
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
        Assert.assertEquals(contactUsPage.getAlertMessage(), contactUsPage.alertResultMessage(ContactUsPage.Alert.CHOSE));
    }
}
