package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Browser;

import java.util.List;

public class OutputJsbinPage {
    public void open(){
        Browser.visit("https://output.jsbin.com/osebed/2");
    }

    public void selectMultiElements(List<String> optionList){

        Select dropdown = new Select(Browser.getElement(By.id("fruits")));
        optionList.stream().forEach(el->{
            dropdown.selectByVisibleText(el);
        });
    }
    public void deselectMultiElements(){
        Select dropdown = new Select(Browser.getElement(By.id("fruits")));
        dropdown.deselectAll();
    }
}
