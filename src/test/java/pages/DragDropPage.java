package pages;

import org.openqa.selenium.By;
import utils.Browser;

public class DragDropPage {
    public void open(){
        Browser.visit("https://the-internet.herokuapp.com/drag_and_drop");
    }

    public void hoverFromElementAToB(String elementA, String elementB){
        Browser.action.dragAndDrop(Browser.getElement(By.id(elementA)),Browser.getElement(By.id(elementB))).perform();
    }

}
