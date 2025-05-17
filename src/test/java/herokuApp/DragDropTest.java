package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Logger;

public class DragDropTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    private static final Logger logger = Logger.getLogger(DragDropTest.class.getName());

    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        actions = new Actions(driver);
    }
    @BeforeMethod
    void reloadPage(){
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        logger.info("Reload page to perform test");
    }
    @Test
    void dragNdropBetweenElements(){
        WebElement elementA = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement elementB = driver.findElement(By.id("column-b"));
        actions.dragAndDrop(elementA,elementB).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("#column-a header")).getText().contains("B"));
        Assert.assertTrue(driver.findElement(By.cssSelector("#column-b header")).getText().contains("A"));
    }
//    high risk if use location (x,y) since each screen has different location
    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
