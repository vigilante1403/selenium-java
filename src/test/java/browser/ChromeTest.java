package browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v131.emulation.Emulation;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.devtools.v131.network.model.ConnectionType;
import org.openqa.selenium.devtools.v85.performance.Performance;
import org.openqa.selenium.devtools.v85.performance.model.Metric;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChromeTest {
    @Test
    void openWithDefaultMode(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }
    @Test
    void openWithHeadlessMode(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }
    @Test
    void openWithMobileViewport(){
        Map<String,Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width",360);
        deviceMetrics.put("height",640);
        Map<String,Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics",deviceMetrics);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation",mobileEmulation);

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }
    @Test
    void openWithOldChromeVersion(){
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("130");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }
    @Test
    void openBetaVersion(){
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("136");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }
    @Test
    void openWithFakeGeoLocation(){
        WebDriver driver = new ChromeDriver();
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        // Mountain view
        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(37.386052),
                Optional.of(-122.083851),
                Optional.of(1)
        ));
        driver.get("https://the-internet.herokuapp.com/geolocation");
        driver.findElement(By.xpath("//button[.='Where am I?']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#lat-value")).getText(),"37.386052");
        Assert.assertEquals(driver.findElement(By.cssSelector("#long-value")).getText(),"-122.083851");
        driver.quit();

    }
    @Test
    void testInteractWithTag(){
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // open target
        driver.get("https://testingvn.gitbook.io/automationtester/selenium-basic/markdown");
        //click the link (ensure visible)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/automationtester/selenium-basic/images-and-media']"))).click();
        // verify H1 text
        Assert.assertEquals("Form Authentication Test",wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[.='Form Authentication Test']"))).getText());

        Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'text-xs')]")).getText(),"Previous");
        driver.quit();
    }
    @Test
    void captureNetwork(){
        WebDriver driver = new ChromeDriver();
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(),reqSent->{
            System.out.println("Request URL => "+reqSent.getRequest().getUrl());
            System.out.println("Request Method => "+reqSent.getRequest().getMethod());
            System.out.println("Request Headers => "+reqSent.getRequest().getHeaders().toString());
            System.out.println("----------------------------------------------------------------");
        });
        devTools.addListener(Network.responseReceived(),responseReceived -> {
            System.out.println("Response URL => "+responseReceived.getResponse().getUrl());
            System.out.println("Response StatusCode => "+responseReceived.getResponse().getStatusText());
            System.out.println("Response headers => "+responseReceived.getResponse().getHeaders().toString());
            System.out.println("Response Mime Type => "+responseReceived.getResponse().getMimeType());
            System.out.println("----------------------------------------------------------------");
        });
        driver.get("https://www.selenium.dev/");
    }
    @Test
    void openSeleniumHomepageThenCapturePerformanceMetrics(){
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metrics = devTools.send(Performance.getMetrics());
        driver.get("https://selenium.dev");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();

        metrics.stream().forEach(metric-> System.out.println(metric.getName()+" = "+metric.getValue()));
    }

    @Test
    void simulate2GNetworkCondition(){
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        //enable emulation
        devTools.send(Network.enable(Optional.of(100000000),Optional.empty(),Optional.empty()));
        // set network conditions to emulate 3G or 4G
        devTools.send(Network.emulateNetworkConditions(false,100,75000,25000,Optional.of(ConnectionType.CELLULAR2G),Optional.of(0),Optional.of(0),Optional.of(false)));
        driver.get("https://selenium.dev");
        driver.quit();
    }
    @Test
    void simulate3GNetworkCondition(){
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        //enable emulation
        devTools.send(Network.enable(Optional.of(100000000),Optional.empty(),Optional.empty()));
        // set network conditions to emulate 3G or 4G
        devTools.send(Network.emulateNetworkConditions(false,100,75000,25000,Optional.of(ConnectionType.CELLULAR3G),Optional.of(0),Optional.of(0),Optional.of(false)));
        driver.get("https://selenium.dev");
        driver.quit();
    }
    @Test
    void simulate4GNetworkCondition(){
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        //enable emulation
        devTools.send(Network.enable(Optional.of(100000000),Optional.empty(),Optional.empty()));
        // set network conditions to emulate 3G or 4G
        devTools.send(Network.emulateNetworkConditions(false,100,75000,25000,Optional.of(ConnectionType.CELLULAR4G),Optional.of(0),Optional.of(0),Optional.of(false)));
        driver.get("https://selenium.dev");
        driver.quit();
    }

}
