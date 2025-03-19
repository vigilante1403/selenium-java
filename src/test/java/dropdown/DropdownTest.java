package dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DropdownTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    void option1ShouldBeSelected(){
        WebElement element = driver.findElement(By.xpath("//select[@id='dropdown']"));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText("Option 1");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@id='dropdown']/option[.='Option 1']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//select[@id='dropdown']/option[text()='Option 1']")).isSelected());
    }
    @Test
    void verifyCanSelectMultipleOptions(){
        driver.get("https://output.jsbin.com/osebed/2");
        Select select = new Select(driver.findElement(By.id("fruits")));

        select.selectByVisibleText("Banana");
        select.selectByVisibleText("Orange");
        select.selectByVisibleText("Grape");

        Assert.assertTrue(driver.findElement(By.xpath("//select[@id='fruits']/option[.='Banana']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//select[@id='fruits']/option[.='Orange']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//select[@id='fruits']/option[.='Grape']")).isSelected());

        select.deselectAll();

        Assert.assertFalse(driver.findElement(By.xpath("//select[@id='fruits']/option[.='Banana']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//select[@id='fruits']/option[.='Orange']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//select[@id='fruits']/option[.='Grape']")).isSelected());
    }
    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
