package pages;

import org.openqa.selenium.By;
import utils.Browser;

public class AuthenticationPage {
    public void open(){
        Browser.visit("https://the-internet.herokuapp.com/login");
    }

    public void sendCredential(String fieldName, String fieldValue){
        Browser.getElement(By.id(String.format("%s",fieldName))).sendKeys(fieldValue);
    }
    public void submit(){
        Browser.click(By.xpath("//*[@type='submit']"));
    }
    public boolean confirmLogin(String checkValue){
        return Browser.getText(By.xpath("//*[@href='/logout']")).isEmpty()?false:true;
    }
}
