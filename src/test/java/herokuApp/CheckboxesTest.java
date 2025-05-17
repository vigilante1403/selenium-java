package herokuApp;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CheckboxPage;
import pages.PoatazeldebsyPage;
import utils.Browser;

import static utils.Browser.openBrowser;

public class CheckboxesTest {
    CheckboxPage checkboxPage;
    PoatazeldebsyPage poatazeldebsyPage;

    @BeforeClass
    void setUp() {
        openBrowser("chrome");
        checkboxPage = new CheckboxPage();
        poatazeldebsyPage = new PoatazeldebsyPage();
    }

    @Test
    void theCheckboxesShouldSelected() {
        checkboxPage.open();
        checkboxPage.select("1");
        Assert.assertTrue(checkboxPage.isSelected("1"));

        checkboxPage.select("2");
        Assert.assertTrue(checkboxPage.isSelected("2"));
    }

    @Test
    void theCheckboxesShouldDeselected() {
        checkboxPage.open();
        checkboxPage.unselect("1");
        Assert.assertFalse(checkboxPage.isSelected("1"));

        checkboxPage.unselect("2");
        Assert.assertFalse(checkboxPage.isSelected("2"));
    }

    @Test
    void verifyCheckAllButtonWorking() {
        poatazeldebsyPage.open();

        poatazeldebsyPage.checkAll();
        Assert.assertTrue(poatazeldebsyPage.isChecked("1"));
        Assert.assertTrue(poatazeldebsyPage.isChecked("2"));
        Assert.assertTrue(poatazeldebsyPage.isChecked("3"));
    }

    @Test
    void verifyUncheckAllButtonWorking() {
        poatazeldebsyPage.open();
        poatazeldebsyPage.uncheckAll();
        Assert.assertFalse(poatazeldebsyPage.isChecked("1"));
        Assert.assertFalse(poatazeldebsyPage.isChecked("2"));
        Assert.assertFalse(poatazeldebsyPage.isChecked("3"));
    }

    @AfterClass
    void tearDown() {
        Browser.quit();
    }
}
