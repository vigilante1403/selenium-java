package herokuApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContextMenuTest {
    WebDriver driver;
    Actions action;

    @BeforeClass
    void setUp(){
        driver = new ChromeDriver();
        action = new Actions(driver);
    }
    @Test
    void testRightClick(){

    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
