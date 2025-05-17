package herokuApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class TableTest {
    WebDriver driver;
    WebDriverWait wait;
    @Parameters({"browser"})
    @BeforeClass
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @BeforeMethod
    void reloadPage() {
        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @Test
    void verifyMaxDuePersons() {

        List<Person> personList = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).
                stream().map(row -> row.findElements(By.tagName("td")))
                .map(cells -> new Person(cells.get(0).getText(), cells.get(1).getText(), cells.get(3).getText())).collect(Collectors.toList());
        OptionalDouble maxDueEstimated = personList.stream().mapToDouble(Person::getDue).max();
        List<Person> personsMaxDue = personList.stream().filter(person -> person.getDue() == maxDueEstimated.getAsDouble()).collect(Collectors.toList());

        Assert.assertEquals(personsMaxDue.stream().map(Person::getFullName).collect(Collectors.toList()), List.of("Jason Doe"));
    }

    @Test
    void verifyMinimumDuePersons() {

        List<Person> personList = driver.findElements(By.xpath("//table[@id='table2']/tbody/tr"))
                .stream().map(row -> row.findElements(By.tagName("td"))).map(cells -> new Person(cells.get(0).getText(), cells.get(1).getText(), cells.get(3).getText())).collect(Collectors.toList());
        OptionalDouble minDueEstimated = personList.stream().mapToDouble(Person::getDue).min();
        List<Person> leastDuePersons = personList.stream().filter(person -> person.getDue() == minDueEstimated.getAsDouble()).collect(Collectors.toList());
//        Thread.sleep(1000);
        Assert.assertEquals(leastDuePersons.stream().map(Person::getFullName).collect(Collectors.toList()), List.of("John Smith", "Tim Conway"));
    }

    @AfterClass
    void tearDown() {
        driver.quit();
    }
}
