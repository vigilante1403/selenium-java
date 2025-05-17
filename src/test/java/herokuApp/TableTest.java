package herokuApp;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.TablePage;
import utils.Browser;
import java.util.List;


import static utils.Browser.openBrowser;

public class TableTest {

    TablePage tablePage;

    @Parameters({"browser"})
    @BeforeClass
    void setUp(@Optional("chrome") String browserName) {
        openBrowser(browserName);
        tablePage = new TablePage();
        tablePage.open();
    }


    @Test
    void verifyMaxDuePersons() {
        tablePage.getAllDues("1");
        tablePage.getMaxDue("1");
        Assert.assertEquals(tablePage.getMaxDuePersons("1"), List.of("Jason Doe"));
    }

    @Test
    void verifyMinimumDuePersons() {
        tablePage.getAllDues("2");
        tablePage.getMinDue("2");
        Assert.assertEquals(tablePage.getMinDuePersons("2"), List.of("John Smith", "Tim Conway"));
    }

    @AfterClass
    void tearDown() {
        Browser.quit();
    }
}
