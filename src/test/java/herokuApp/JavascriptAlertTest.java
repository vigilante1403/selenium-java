package herokuApp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import pages.JavascriptAlertPage;
import utils.Browser;

import java.util.logging.Logger;

import static utils.Browser.openBrowser;


public class JavascriptAlertTest {
    private static final Logger logger = Logger.getLogger(JavascriptAlertTest.class.getName());
    JavascriptAlertPage javascriptAlertPage;

    @BeforeClass
    void setUp(@Optional("chrome") String browserName) {
        openBrowser(browserName);
        javascriptAlertPage = new JavascriptAlertPage();
        javascriptAlertPage.open();
        logger.info("Browser opened");
    }

    @Test
    void ableCloseAlert() {
        javascriptAlertPage.clickAlertButton("Click for JS Alert");
        javascriptAlertPage.acceptAlert();
        Assert.assertTrue(Browser.getElement(By.id("result")).getText().contains("You successfully clicked an alert"));
    }

    @Test
    void ableCancelAlert() {
        javascriptAlertPage.clickAlertButton("Click for JS Confirm");
        javascriptAlertPage.dismissAlert();
        Assert.assertTrue(Browser.getElement(By.id("result")).getText().contains("You clicked: Cancel"));
    }

    @Test
    void ableToSendKeyJSPrompt() {
        javascriptAlertPage.clickAlertButton("Click for JS Prompt");
        javascriptAlertPage.sendKeysToAlert("hello");
        javascriptAlertPage.acceptAlert();
        Assert.assertTrue(Browser.getElement(By.id("result")).getText().contains("You entered: hello"));
    }

    @AfterClass
    void tearDown() {
        Browser.quit();
        logger.info("Browser closed");
    }


}
