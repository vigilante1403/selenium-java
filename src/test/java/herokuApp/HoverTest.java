package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Logger;

public class HoverTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    private static final Logger logger = Logger.getLogger(HoverTest.class.getName());
    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        logger.info("Create instance then instantiate actions and wait objects");
    }
    @BeforeMethod
    void reloadPage(){
        driver.get("https://the-internet.herokuapp.com/hovers");
        logger.info("Redirect to page to perform action");
    }

    @Test
    void hoverOnAnElement(){
        WebElement element = driver.findElement(By.xpath("(//div[@class='figure'])[1]"));
        actions.moveToElement(element).perform();
        WebElement caption = driver.findElement(By.xpath("(//div[@class='figure'])[1]/div[@class='figcaption']"));
        Assert.assertTrue(caption.getText().contains("user1"));
        WebElement viewProfile = driver.findElement(By.xpath("(//div[@class='figure'][1])/div[@class='figcaption']/a[.='View profile']"));
        actions.click(viewProfile).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(),"Not Found");
    }



    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
