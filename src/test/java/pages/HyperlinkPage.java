package pages;

import org.openqa.selenium.By;
import utils.Browser;

public class HyperlinkPage {
    public void open(){
        Browser.visit("https://the-internet.herokuapp.com/status_codes");
    }

    public void clickLink(String linkText){
        Browser.click(By.linkText(linkText));
    }

    public String getCurrentUrl(){
        return Browser.getCurrentUrl();
    }
}
