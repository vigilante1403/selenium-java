package pages;

import org.openqa.selenium.By;

import utils.Browser;

public class CheckboxPage {

    public void open(){
        Browser.visit("https://the-internet.herokuapp.com/checkboxes");
    }

    public void select(String checkboxName){
        Browser.check(By.xpath(String.format("//form[@id='checkboxes']/input[%s]",checkboxName)));
    }
    public void unselect(String checkboxName){
        Browser.uncheck(By.xpath(String.format("//form[@id='checkboxes']/input[%s]",checkboxName)));
    }
    public boolean isSelected(String checkboxName){
        return Browser.isSelected(By.xpath(String.format("//form[@id='checkboxes']/input[%s]",checkboxName)));
    }




}
