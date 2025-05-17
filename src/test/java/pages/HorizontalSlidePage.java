package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Browser;

public class HorizontalSlidePage {
    public void open(){
        Browser.visit("https://the-internet.herokuapp.com/horizontal_slider");
    }
    public void performSlide(){
        int width = Browser.getElement(By.cssSelector(".sliderContainer input")).getSize().width; // lay do dai cua input
        Browser.action.clickAndHold(Browser.getElement(By.cssSelector(".sliderContainer input")))
                .moveByOffset(width,0)
                .perform();
        Browser.action.release().perform();
    }
}
