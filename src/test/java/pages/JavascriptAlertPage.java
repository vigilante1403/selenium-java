package pages;

import org.openqa.selenium.By;
import utils.Browser;

public class JavascriptAlertPage {
    public void open() {
        utils.Browser.visit("https://the-internet.herokuapp.com/javascript_alerts");
    }

    public void clickAlertButton(String button) {
        Browser.click(By.xpath("//button[text()='" + button + "']"));
    }

    public String getAlertText() {
        return utils.Browser.getAlertText();
    }

    public void sendKeysToAlert(String keys) {
        utils.Browser.sendKeys(keys);
    }

    public void acceptAlert() {
        utils.Browser.acceptAlert();
    }

    public void dismissAlert() {
        utils.Browser.dismissAlert();
    }
}
