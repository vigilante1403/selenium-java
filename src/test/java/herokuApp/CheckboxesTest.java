package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckboxesTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

//    @BeforeMethod
    void reloadPage(){
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }
    @Test
    void theCheckboxesShouldSelected(){
        WebElement checkbox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        check(checkbox1);
        Assert.assertTrue(driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).isSelected());
        WebElement checkbox2 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));
        check(checkbox2);
        Assert.assertTrue(driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected());
    }
    @Test
    void theCheckboxesShouldDeselected(){
        WebElement checkbox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        uncheck(checkbox1);
        Assert.assertFalse(driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).isSelected());
        WebElement checkbox2 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));
        uncheck(checkbox2);
        Assert.assertFalse(driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected());
    }
    void check(WebElement element){
        if(!element.isSelected()) element.click();
    }
    void uncheck(WebElement element){
        if(element.isSelected())element.click();
    }
    @AfterClass
    void tearDown(){
        driver.quit();
    }
    @Test
    void test(){
        WebDriver driver1= new ChromeDriver();
        WebDriverWait wait1 = new WebDriverWait(driver1,Duration.ofSeconds(4));
        driver1.get("https://the-internet.herokuapp.com/checkboxes");
        wait1.until(ExpectedConditions.titleIs("The Internet"));
//        WebElement checkbox1 = driver1.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        WebElement checkbox1 = driver1.findElement(By.cssSelector("#checkboxes input:first-of-type"));

        check(checkbox1);
        Assert.assertTrue(driver1.findElement(By.xpath("//form[@id='checkboxes']/input[1]")).isSelected());
        WebElement checkbox2 = driver1.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));
        check(checkbox2);
        Assert.assertTrue(driver1.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected());
        driver1.quit();
    }
    @Test
    void verifyCheckAllButtonWorkingTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
        driver.get("https://moatazeldebsy.github.io/test-automation-practices/#/checkboxes");
        wait.until(ExpectedConditions.titleContains("Test Automation Practices"));

        driver.findElement(By.xpath("//button[@data-test='check-all-button']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox1']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox2']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox3']")).isSelected());


        driver.quit();
    }
    @Test
    void verifyUncheckAllButtonWorkingTest() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
        driver.get("https://moatazeldebsy.github.io/test-automation-practices/#/checkboxes");
        wait.until(ExpectedConditions.titleContains("Test Automation Practices"));

        driver.findElement(By.xpath("//button[@data-test='check-all-button']")).click();
        driver.findElement(By.xpath("//button[@data-test='uncheck-all-button']")).click();

        Assert.assertTrue(!driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox1']")).isSelected());
        Assert.assertTrue(!driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox2']")).isSelected());
        Assert.assertTrue(!driver.findElement(By.xpath("//input[@data-test='checkbox-checkbox3']")).isSelected());

        driver.quit();
    }
}
