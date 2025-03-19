package formAuthentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Authentication {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    void setup(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }
    @BeforeMethod
    void load(){
        driver.get("https://the-internet.herokuapp.com/login");
    }
    @Test
    void validCredential(){
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='/logout']"))).getText();
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");
        Assert.assertTrue(driver.findElement(By.id("flash-messages")).isDisplayed());
    }
    @Test
    void invalidCredential(){
        driver.findElement(By.id("username")).sendKeys(" or 1=1;--");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.xpath("//*[@type='submit']")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/login");
        Assert.assertTrue(driver.findElement(By.className("error")).isDisplayed());
    }
    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
