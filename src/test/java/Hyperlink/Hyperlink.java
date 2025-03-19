package Hyperlink;

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

public class Hyperlink {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }
    @BeforeMethod
    void reloadPage(){
        driver.get("https://the-internet.herokuapp.com/redirector");
    }
    @Test
    void redirector() {
        //wait is extremely useful in asynchronous case
        driver.findElement(By.linkText("here")).click();
        // check if redirect to correct page
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes");
        // click link status code 200
        driver.findElement(By.linkText("200")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/200");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("content"))));
        String content = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(content.contains("This page returned a 200 status code"));
        // 301 test
        driver.findElement(By.linkText("here")).click();
        driver.findElement(By.linkText("301")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/301");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("content"))));
        content = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(content.contains("This page returned a 301 status code."));
        //404 test
        driver.findElement(By.linkText("here")).click();
        driver.findElement(By.linkText("404")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/404");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("content"))));
        content = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(content.contains("This page returned a 404 status code."));
        //500 test
        driver.findElement(By.linkText("here")).click();
        driver.findElement(By.linkText("500")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/500");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("content"))));
        content = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(content.contains("This page returned a 500 status code."));
    }




    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
