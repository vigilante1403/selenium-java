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

public class HorizontalSlideTest {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(HorizontalSlideTest.class.getName());

    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));

    }
    @BeforeMethod
    void reloadPage(){
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");

    }
    @Test
    void performHorizontalSlider() throws InterruptedException {
        WebElement input = driver.findElement(By.cssSelector(".sliderContainer input"));
        // lay element input  cua class sliderContainer
        int width = input.getSize().width; // lay do dai cua input
        actions = new Actions(driver);
        actions.clickAndHold(input)
                .moveByOffset(width,0)
                .perform();

        actions.release().perform();
        // can action release de action stop click and hold
        Assert.assertEquals(driver.findElement(By.id("range")).getText(),"5");
    }
    @Test
    void infinityScrollDown(){

    }
    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
