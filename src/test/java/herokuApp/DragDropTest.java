package herokuApp;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.DragDropPage;
import utils.Browser;

import java.util.logging.Logger;

import static utils.Browser.openBrowser;

public class DragDropTest {
    DragDropPage dragDropPage;
    private static final Logger logger = Logger.getLogger(DragDropTest.class.getName());
    @Parameters({"browser"})
    @BeforeClass
    void setUp(@Optional("chrome") String browserName){
        openBrowser(browserName);
        dragDropPage = new DragDropPage();
        dragDropPage.open();
    }
    @Test
    void dragNdropBetweenElements(){
        dragDropPage.hoverFromElementAToB("column-a","column-b");
        Assert.assertTrue(Browser.getElement(By.cssSelector("#column-a header")).getText().contains("B"));
        Assert.assertTrue(Browser.getElement(By.cssSelector("#column-b header")).getText().contains("A"));
    }
//    high risk if use location (x,y) since each screen has different location
    @AfterClass
    void tearDown(){
        Browser.quit();
    }
}
