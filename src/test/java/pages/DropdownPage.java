package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import utils.Browser;

public class DropdownPage {
    public void open(){
        Browser.visit("https://the-internet.herokuapp.com/dropdown");
    }

    public void selectElement(String option){
        Select dropdown = new Select(Browser.getElement(By.id("dropdown")));
        dropdown.selectByVisibleText(option);
    }

}
