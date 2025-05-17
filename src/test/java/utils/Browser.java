package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// selenium owner methods
public class Browser {
    private static WebDriver driver;
    public static WebDriverWait wait;
    /*
     open browser: chrome, firefox, edge, safari
     return WebDriver
     static method
     */
    public static void openBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public static WebDriver getDriver() {
        return driver;
    }
    public static void visit(String url){
        driver.get(url);
    }
    public static void quit() {
        driver.quit();
    }
    public static void click(By by){
        wait
                .until(ExpectedConditions.elementToBeClickable(by))
                .click();
    }
    public static void fill(By by,String withText){
        driver.findElement(by).sendKeys(withText);
    }

    public static boolean isSelected(By by){
        return driver.findElement(by).isSelected();
    }

    public static void check(By by){
        if(!isSelected(by)){
            click(by);
        }
    }

    public static String getText(By by){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    }
    public static String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

}

