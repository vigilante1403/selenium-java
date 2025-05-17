package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HoverPage;
import utils.Browser;

import java.time.Duration;
import java.util.logging.Logger;

import static utils.Browser.*;

public class HoverTest {
    HoverPage  hoverPage;
    private static final Logger logger = Logger.getLogger(HoverTest.class.getName());
    @Parameters({"browser"})
    @BeforeClass
    void setUp(@Optional("chrome") String browserName) {
        openBrowser(browserName);
        hoverPage = new HoverPage();
        logger.info("Create instance then instantiate actions and wait objects");
        hoverPage.open();
        logger.info("Redirect to page to perform action");
    }

    @Test
    void hoverOnAnElement(){
        hoverPage.hoverOverElement();
        logger.info("Hover over the element");
        Assert.assertTrue(Browser.getElement(By.xpath("(//div[@class='figure'])[1]/div[@class='figcaption']")).getText().contains("user1"));
        hoverPage.viewProfile();
        hoverPage.confirmElement();
        Assert.assertEquals(Browser.getElement(By.tagName("h1")).getText(),"Not Found");
    }

    @AfterClass
    void tearDown(){
        Browser.quit();
    }
}
