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

public class NestedFrameTest {
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(NestedFrameTest.class.getName());

    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        logger.info("Create instances for driver and wait");
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        logger.info("Redirect to nested frames page");
    }

    @Test
    void verifyFrameContent(){
        try{
            driver.switchTo().frame(0);
            logger.info("Choosing frame top");
            driver.switchTo().frame(0);
            logger.info("Choosing first child of frame top => Frame left");
            Boolean bool= driver.findElement(By.tagName("body")).getText().contains("LEFT");
            Assert.assertTrue(bool);
            logger.info("Result: "+bool);

            driver.switchTo().parentFrame();
            logger.info("Go back to parent frame [frame top]");
            driver.switchTo().frame(1);
            logger.info("Choosing frame middle");
            bool = driver.findElement(By.id("content")).getText().contains("MIDDLE");
            Assert.assertTrue(bool);
            logger.info("Result: "+bool);

            driver.switchTo().parentFrame();
            logger.info("Go back to parent frame");
            driver.switchTo().frame("frame-right");
            logger.info("Choosing frame by name");
            bool = driver.findElement(By.tagName("body")).getText().contains("RIGHT");
            Assert.assertTrue(bool);
            logger.info("Result: "+bool);

            driver.switchTo().defaultContent();
            logger.info("Back to beginning");
            driver.switchTo().frame("frame-bottom");
            logger.info("Go frame bottom");
            bool = driver.findElement(By.tagName("body")).getText().contains("BOTTOM");
            Assert.assertTrue(bool);
            logger.info("Result: "+bool);
        }catch(Exception e){
            logger.severe("Exception while switching frames: "+e.getMessage());
            throw e;
        }


    }

    @AfterClass
    void tearDown(){
        driver.quit();
        logger.info("Close test window. Testing end!");
    }

}
