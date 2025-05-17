package pages;

import org.openqa.selenium.By;

public class TablePage {
    public void open() {
        utils.Browser.visit("https://the-internet.herokuapp.com/tables");
    }

    public String getCellText(int row, int column) {
        return utils.Browser.getElement(By.xpath("//table[@id='table1']/tbody/tr[" + row + "]/td[" + column + "]")).getText();
    }

    public String getRowText(int row) {
        return utils.Browser.getElement(By.xpath("//table[@id='table1']/tbody/tr[" + row + "]")).getText();
    }

    public String getHeaderText(int column) {
        return utils.Browser.getElement(By.xpath("//table[@id='table1']/thead/tr/th[" + column + "]")).getText();
    }
}
