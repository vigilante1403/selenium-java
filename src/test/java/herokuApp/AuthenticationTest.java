package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.Browser;

import java.time.Duration;

import static utils.Browser.openBrowser;

public class AuthenticationTest {
    WebDriver driver;
    WebDriverWait wait;
    @Parameters({"browser"})
    @BeforeClass
    void setup(String browser){
        Browser.openBrowser(browser);
        driver = Browser.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }
    @BeforeMethod
    void load(){
        driver.get("https://the-internet.herokuapp.com/login");
    }
    @Test
    void validCredential(){
        By usernameInput = RelativeLocator.with(By.tagName("input")).below(By.xpath("//label[.='Username']"));

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='/logout']"))).getText();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");
        Assert.assertTrue(driver.findElement(By.id("flash-messages")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.tagName("h4")).getText().contains("Welcome to the Secure Area"));
    }
//    @Test
    void invalidCredential(){
        driver.findElement(By.id("username")).sendKeys(" or 1=1;--");
        driver.findElement(By.id("password")).sendKeys("abc");
        driver.findElement(By.xpath("//*[@type='submit']")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/login");
        Assert.assertTrue(driver.findElement(By.className("error")).isDisplayed());
    }
    @AfterClass
    void tearDown(){
        driver.quit();
    }

    void test(){
//        driver.findElement(By.tagName("input")).sendKeys("SuperSecretPassword");

        driver.findElement(By.cssSelector(".radius")).click();
        driver.findElement(By.cssSelector("[type=submit]")).click();
        driver.findElement(By.cssSelector("button.radius")).click();
        driver.findElement(By.cssSelector("button[type=submit]")).click();
        driver.findElement(By.cssSelector("button[class*='radius']")).click();

        driver.findElement(By.xpath("//*[@type='submit']")).click();
        driver.findElement(By.xpath("//*[contains(@class,'radius')]")).click();
        driver.findElement(By.xpath("//button[contains(@class,'radius')]")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//button[@class='radius']")).click();















    }
}
