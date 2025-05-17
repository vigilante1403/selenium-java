package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Logger;


public class JavascriptAlertTest {
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(JavascriptAlertTest.class.getName());

    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

    }
    @Test
    void ableCloseAlert(){
        driver.findElement(By.xpath("//button[.='Click for JS Alert']")).click();
        driver.switchTo().alert().accept();// click ok
     //   driver.switchTo().alert().dismiss();// click cancel
        Assert.assertTrue(driver.findElement(By.id("result")).getText().contains("You successfully clicked an alert"));
    }
    @Test
    void ableCancelAlert(){
        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();
        Assert.assertTrue(driver.findElement(By.id("result")).getText().contains("You clicked: Cancel"));
    }
    @Test
    void ableToSendKeyJSPrompt(){
        driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();
        driver.switchTo().alert().sendKeys("hello");
        driver.switchTo().alert().accept();

        Assert.assertTrue(driver.findElement(By.id("result")).getText().contains("You entered: hello"));
    }
    @AfterClass
    void tearDown(){
        driver.quit();
    }


}
