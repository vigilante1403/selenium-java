package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HorizontalSlidePage;
import utils.Browser;

import java.time.Duration;
import java.util.logging.Logger;

import static utils.Browser.openBrowser;

public class HorizontalSlideTest {

    HorizontalSlidePage horizontalSlidePage;
    private static final Logger logger = Logger.getLogger(HorizontalSlideTest.class.getName());

    @BeforeClass
    void setUp(@Optional("chrome") String browserName){
        openBrowser(browserName);
        horizontalSlidePage = new HorizontalSlidePage();
        horizontalSlidePage.open();
        logger.info("Create instance then instantiate actions and wait objects");
    }

    @Test
    void performHorizontalSlider(){
        horizontalSlidePage.performSlide();
        // can action release de action stop click and hold
        Assert.assertEquals(Browser.getElement(By.id("range")).getText(),"5");
    }
//    @Test
    void infinityScrollDown(){

    }
    @AfterClass
    void tearDown(){
        Browser.quit();
    }
}
