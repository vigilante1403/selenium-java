package herokuApp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AuthenticationPage;
import utils.Browser;

import java.time.Duration;

import static utils.Browser.openBrowser;

public class AuthenticationTest {

    AuthenticationPage authenticationPage;
    @Parameters({"browser"})
    @BeforeClass
    void setup(@Optional("chrome") String browser){
        openBrowser(browser);
        authenticationPage = new AuthenticationPage();
        authenticationPage.open();
    }

    @Test
    void validCredential(){
//        By usernameInput = RelativeLocator.with(By.tagName("input")).below(By.xpath("//label[.='Username']"));
        authenticationPage.sendCredential("username","tomsmith");
        authenticationPage.sendCredential("password","SuperSecretPassword!");
        authenticationPage.submit();
        authenticationPage.confirmLogin("//*[@href='/logout']");
        Assert.assertEquals(Browser.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");
        Assert.assertTrue( Browser.getElement(By.id("flash-messages")).isDisplayed());
        Assert.assertTrue(Browser.getElement(By.tagName("h4")).getText().contains("Welcome to the Secure Area"));
    }
    @Test
    void invalidCredential(){
        authenticationPage.sendCredential("username"," or 1=1;--");
        authenticationPage.sendCredential("password","abc");
        authenticationPage.submit();

        Assert.assertEquals(Browser.getCurrentUrl(),"https://the-internet.herokuapp.com/login");
        Assert.assertTrue( Browser.getElement(By.className("error")).isDisplayed());
    }
    @AfterClass
    void tearDown(){
        Browser.quit();
    }

//    void test(){
////        driver.findElement(By.tagName("input")).sendKeys("SuperSecretPassword");
//
//        driver.findElement(By.cssSelector(".radius")).click();
//        driver.findElement(By.cssSelector("[type=submit]")).click();
//        driver.findElement(By.cssSelector("button.radius")).click();
//        driver.findElement(By.cssSelector("button[type=submit]")).click();
//        driver.findElement(By.cssSelector("button[class*='radius']")).click();
//
//        driver.findElement(By.xpath("//*[@type='submit']")).click();
//        driver.findElement(By.xpath("//*[contains(@class,'radius')]")).click();
//        driver.findElement(By.xpath("//button[contains(@class,'radius')]")).click();
//        driver.findElement(By.xpath("//button[@type='submit']")).click();
//        driver.findElement(By.xpath("//button[@class='radius']")).click();















    }

