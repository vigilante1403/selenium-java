package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Browser;

public class BMIPage {
    WebDriver driver;
    public BMIPage(WebDriver driver){
        this.driver=driver;
    }
//fill form, get result, clear form, select unit metrics
    void selectUnitMetric(){
        Browser
                .wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menuon a")))
                .click();
    }
    void clearForm(){
        driver.findElement(By.xpath("//input[@value='Clear']")).click();
    }
    public void bmiFillForm(){

    }
}
