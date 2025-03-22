package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BraveBrowserTest {
    WebDriver driver;

    @BeforeClass
    void setUp(){
        driver= new ChromeDriver();
    }
    @BeforeMethod
    void reloadPage(){
        driver.get("https://brave.com/");
    }
    @Test
    void openBraveBrowser(){
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().contains("The browser that puts you first"));
        driver.quit();
    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
