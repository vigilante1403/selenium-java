package pages;

import org.openqa.selenium.By;

public class HoverPage {
    public void open(){
        utils.Browser.visit("https://the-internet.herokuapp.com/hovers");
    }

    public void hoverOverElement(String element){
        utils.Browser.action.moveToElement(utils.Browser.getElement(By.xpath("//div[@class='figure'][1]"))).perform();
    }

    public String getTextOfElement(String element){
        return utils.Browser.getElement(By.xpath("//div[@class='figure'][1]//h5")).getText();
    }
}
