package pages;

import org.openqa.selenium.By;
import utils.Browser;
import static utils.Browser.click;
import static utils.Browser.visit;

// head first design pattern - factory, proxy, strategy, singleton

public class PoatazeldebsyPage {
    public void open(){
        visit("https://moatazeldebsy.github.io/test-automation-practices/#/checkboxes");
    }

    public void check(String checkboxName){
        Browser.check(By.xpath(String.format("//input[@data-test='checkbox-checkbox%s']",checkboxName)));
    }

    public void uncheck(String checkboxName){
        Browser.uncheck(By.xpath(String.format("//input[@data-test='checkbox-checkbox%s']",checkboxName)));
    }

    public void checkAll(){
        click(By.xpath("//button[@data-test='check-all-button']"));
    }
    public void uncheckAll(){
        click(By.xpath("//button[@data-test='uncheck-all-button']"));

    }
    public boolean isChecked(String checkboxName){
        return Browser.isSelected(By.xpath(String.format("//input[@data-test='checkbox-checkbox%s']",checkboxName)));
    }
}
