package pages;

import org.openqa.selenium.By;
import utils.Browser;

public class HoverPage {
    public void open(){
        utils.Browser.visit("https://the-internet.herokuapp.com/hovers");
    }

    public void hoverOverElement(){
        utils.Browser.action.moveToElement(utils.Browser.getElement(By.xpath("//div[@class='figure'][1]"))).perform();
    }
    public void viewProfile(){
        utils.Browser.action.click(Browser.getElement(By.xpath("(//div[@class='figure'][1])/div[@class='figcaption']/a[.='View profile']"))).perform();
    }
    public void confirmElement(){
        Browser.visible(By.tagName("h1"));
    }
    public String getTextOfElement(String element){
        return utils.Browser.getElement(By.xpath("//div[@class='figure'][1]//h5")).getText();
    }
}
