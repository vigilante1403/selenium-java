//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import utils.Browser;
//
//public class BMIPage {
//    WebDriver driver;
//    public BMIPage(WebDriver driver){
//        this.driver=driver;
//    }
////fill form, get result, clear form, select unit metrics
//    void selectUnitMetric(){
//        Browser
//                .wait
//                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menuon a")))
//                .click();
//    }
//    void clearForm(){
//        driver.findElement(By.xpath("//input[@value='Clear']")).click();
//    }
//    public void bmiFillForm(){
//
//    }
//}
package pages.calculator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Browser;

import static utils.Browser.*;

public class BMIPage {
    /*
    fill form
    get result
    clear form
    select unitmetric
     */

    public void open(){
        visit("https://www.calculator.net/bmi-calculator.html");
    }

    public void selectUnitMetric() {
        click(By.cssSelector("#menuon a"));

    }
    public void clearForm(){
        click(By.xpath("//input[@value='Clear']"));
    }
    public void fillForm(String age,String gender, String height, String weight) {
        fill(By.id("cage"),age);

        if(gender.equalsIgnoreCase("male")){
            check(By.id("csex1"));

        }else {
            check(By.id("csex2"));
        }
        fill(By.id("cheightmeter"),height);
        fill(By.id("ckg"),weight);
        click(By.xpath("//input[@value='Calculate']"));
    }
    public String getResultText() {
        return getText(By.cssSelector(".bigtext"));
    }

}
 