package herokuApp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import pages.DropdownPage;
import pages.OutputJsbinPage;
import utils.Browser;

import java.time.Duration;
import java.util.List;

import static utils.Browser.openBrowser;

public class DropdownTest {
    DropdownPage dropdownPage;
    OutputJsbinPage outputJsbinPage;

    @BeforeClass
    void setUp(@Optional("chrome") String browserName){
        openBrowser(browserName);
        dropdownPage = new DropdownPage();
        outputJsbinPage = new OutputJsbinPage();

    }

    @Test
    void verifyCanSelectOptions(){
        dropdownPage.open();
        dropdownPage.selectElement("Option 1");
        Assert.assertTrue(Browser.getElement(By.xpath("//option[.='Option 1']")).isSelected());
        Assert.assertTrue(Browser.getElement(By.xpath("//option[text()='Option 1']")).isSelected());
        dropdownPage.selectElement("Option 2");
        Assert.assertTrue(Browser.getElement(By.xpath("//option[.='Option 2']")).isSelected());
        Assert.assertTrue(Browser.getElement(By.xpath("//option[text()='Option 2']")).isSelected());
    }
    @Test
    void verifyCanSelectMultipleOptions(){
        outputJsbinPage.open();
        outputJsbinPage.selectMultiElements(List.of("Banana","Orange","Grape"));
        Assert.assertTrue(Browser.getElement(By.xpath("//select[@id='fruits']/option[.='Banana']")).isSelected());
        Assert.assertTrue(Browser.getElement(By.xpath("//select[@id='fruits']/option[.='Orange']")).isSelected());
        Assert.assertTrue(Browser.getElement(By.xpath("//select[@id='fruits']/option[.='Grape']")).isSelected());
        outputJsbinPage.deselectMultiElements();
        Assert.assertFalse(Browser.getElement(By.xpath("//select[@id='fruits']/option[.='Banana']")).isSelected());
        Assert.assertFalse(Browser.getElement(By.xpath("//select[@id='fruits']/option[.='Orange']")).isSelected());
        Assert.assertFalse(Browser.getElement(By.xpath("//select[@id='fruits']/option[.='Grape']")).isSelected());
    }
//    @Test
    void verifyEnabledInputField(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        Assert.assertTrue(driver.findElement(By.cssSelector("form#input-example input")).isEnabled());
    }
//    @Test
    void verifybasicAuthForm(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p")).getText().contains("Congratulations! You must have the proper credentials"));

    }
//    @Test
    void verifyBrokenImage(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/broken_images");
        List<WebElement> list = driver.findElements(By.cssSelector(".example img"));
        list.stream().forEach(img->{
            String width = img.getDomProperty("naturalWidth");
            String height = img.getDomProperty("naturalHeight");
            System.out.println("Width: "+width);
            System.out.println("Height: "+height);
        });
    }
    @AfterClass
    void tearDown(){
        Browser.quit();
    }
}
