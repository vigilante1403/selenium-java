package herokuApp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HyperlinkPage;
import utils.Browser;



import static utils.Browser.openBrowser;

public class Hyperlink {
    HyperlinkPage hyperlinkPage;

    @BeforeClass
    void setUp(@Optional("chrome") String browserName){
        openBrowser(browserName);
        hyperlinkPage = new HyperlinkPage();
        hyperlinkPage.open();
    }

    @Test
    void checkStatusCodes() {

        // click link status code 200
        hyperlinkPage.clickLink("200");
        Assert.assertEquals(Browser.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/200");
        Browser.visible(By.id("content"));
        Assert.assertTrue(Browser.getElement(By.id("content")).getText().contains("This page returned a 200 status code"));
        // 301 test
        Browser.goBack();
        hyperlinkPage.clickLink("301");
        Assert.assertEquals(Browser.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/301");
        Browser.visible(By.id("content"));
        Assert.assertTrue(Browser.getElement(By.id("content")).getText().contains("This page returned a 301 status code"));

        //404 test
        Browser.goBack();
        hyperlinkPage.clickLink("404");
        Assert.assertEquals(Browser.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/404");
        Browser.visible(By.id("content"));
        Assert.assertTrue(Browser.getElement(By.id("content")).getText().contains("This page returned a 404 status code"));
        //500 test
        Browser.goBack();
        hyperlinkPage.clickLink("500");
        Assert.assertEquals(Browser.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/500");
        Browser.visible(By.id("content"));
        Assert.assertTrue(Browser.getElement(By.id("content")).getText().contains("This page returned a 500 status code"));

    }




    @AfterClass
    void tearDown(){
        Browser.quit();
    }
}
