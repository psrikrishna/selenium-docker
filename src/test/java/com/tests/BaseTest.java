package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        // BROWSER => chrome / firefox
        // HUB_HOST => localhost / 10.0.1.3 / hostname

        String host = "localhost";
//        System.setProperty("webdriver.chrome.driver","/Users/srikrishnapeddinti/Downloads/chromedriver_mac64/chromedriver");
//        DesiredCapabilities dc = DesiredCapabilities.chrome();
//        dc.setVersion("87");
        //      ChromeOptions options = new ChromeOptions();
        // ChromeDriver driver = new ChromeDriver(options);
        DesiredCapabilities dc;
        //  System.setProperty("webdriver.chrome.driver","/Users/srikrishnapeddinti/Downloads/chromedriver_mac64/chromedriver");

        //     dc.setVersion("87.0");
        // dc.setCapability("os", "OS X");
        // dc.setCapability("os_version", "Catalina");
        //   dc.setCapability("browser", "Chrome");
        //      dc.setCapability("browser_version", "latest");
        //  driver = new RemoteWebDriver(new URL(remoteAddress), capabilities);

        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
            System.out.println("Launching FF...");
            dc = DesiredCapabilities.firefox();
        } else if  (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("chrome")) {
                dc = DesiredCapabilities.chrome();
            System.out.println("Launching CHROME...");
         }
        else{
            System.out.println("Unsupported browser**************");
            dc = null;
        }
            if (System.getProperty("HUB_HOST") != null) {
                host = System.getProperty("HUB_HOST");
            }

            String testName = ctx.getCurrentXmlTest().getName();

            String completeUrl = "http://" + host + ":4444/wd/hub";
            dc.setCapability("name", testName);
            this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
        }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }



}
