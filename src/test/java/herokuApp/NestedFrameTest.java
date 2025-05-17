package herokuApp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import pages.NestedFramePage;
import utils.Browser;
import java.util.logging.Logger;

import static utils.Browser.openBrowser;

public class NestedFrameTest {
    NestedFramePage nestedFramePage;
    private static final Logger logger = Logger.getLogger(NestedFrameTest.class.getName());

    @BeforeClass
    void setUp(@Optional("chrome") String browserName){
        openBrowser(browserName);
        logger.info("Create instance for driver and wait");
        nestedFramePage = new NestedFramePage();
        nestedFramePage.open();
        logger.info("Redirect to nested frames page");
    }

    @Test
    void verifyFrameContent(){
        try{
            nestedFramePage.switchToFrame(0);
            logger.info("Choosing frame top");
            nestedFramePage.switchToFrame(0);
            logger.info("Choosing first child of frame top => Frame left");
            Boolean bool= Browser.getElement(By.tagName("body")).getText().contains("LEFT");
            Assert.assertTrue(bool);
            logger.info("Result: "+bool);

            nestedFramePage.switchToParentFrame();
            logger.info("Go back to parent frame [frame top]");
            nestedFramePage.switchToFrame(1);
            logger.info("Choosing frame middle");
            bool = Browser.getElement(By.id("content")).getText().contains("MIDDLE");
            Assert.assertTrue(bool);
            logger.info("Result: "+bool);

            nestedFramePage.switchToParentFrame();
            logger.info("Go back to parent frame");
            nestedFramePage.switchToFrame("frame-right");
            logger.info("Choosing frame by name");
            bool = Browser.getElement(By.tagName("body")).getText().contains("RIGHT");
            Assert.assertTrue(bool);
            logger.info("Result: "+bool);

            nestedFramePage.switchToDefaultContent();
            logger.info("Back to beginning");
            nestedFramePage.switchToFrame("frame-bottom");
            logger.info("Go frame bottom");
            bool = Browser.getElement(By.tagName("body")).getText().contains("BOTTOM");
            Assert.assertTrue(bool);
            logger.info("Result: "+bool);
        }catch(Exception e){
            logger.severe("Exception while switching frames: "+e.getMessage());
            throw e;
        }


    }

    @AfterClass
    void tearDown(){
        Browser.quit();
        logger.info("Close test window. Testing end!");
    }

}
