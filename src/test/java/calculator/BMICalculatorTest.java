package calculator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Browser;

import java.time.Duration;

import static utils.Browser.openBrowser;

public class BMICalculatorTest {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    void setUp(){
        Browser.openBrowser("chrome");
    }
    @DataProvider
    public Object[][] testData(){
        return new Object[][]{
                {"25","175","70","BMI = 22.9 kg/m2   (Normal)"},
                {"25", "175", "90", "BMI = 29.4 kg/m2   (Overweight)"},
                {"25", "175", "100", "BMI = 32.7 kg/m2   (Obese Class I)"}
        };
    }


    @Test(dataProvider = "testData")
    void verifyBIMCalculatorClassification(String age, String height, String weight, String expectedResult) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.calculator.net/bmi-calculator.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menuon a"))).click();
        driver.findElement(By.xpath("//input[@value='Clear']")).click();

        driver.findElement(By.id("cage")).sendKeys(age);
        if(!driver.findElement(By.id("csex1")).isSelected()) {
            driver.findElement(By.id("csex1")).click();
        }
        driver.findElement(By.id("cheightmeter")).sendKeys(height);
        driver.findElement(By.id("ckg")).sendKeys(weight);
        driver.findElement(By.xpath("//input[@value='Calculate']")).click();
        String resultText = driver.findElement(By.cssSelector(".bigtext")).getText();
        Assert.assertEquals(resultText,expectedResult);
        driver.quit();

    }
}
