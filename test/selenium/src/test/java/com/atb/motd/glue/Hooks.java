package com.atb.motd.glue;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Hooks {

    private static WebDriver driver;
    private static Properties properties;

    @Before(order = 1)
    public void beforeScenarioLoadConfiguration() throws IOException {

        File file = new File("config.properties");
        FileInputStream fileInput = new FileInputStream(file);
        properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        // override the properties from the config file with any that were specified as system properties to mvn
        properties.putAll(System.getProperties());
    }

    @Before(order = 2)
    public void beforeScenarioSetupSeleniumDriver() throws Throwable {

        String browser = properties.getProperty("config.browser");

        if (properties.getProperty("config.grid").equalsIgnoreCase("true")) {

            // use remote selenium grid hub

            if (browser.equalsIgnoreCase("firefox")) {
                driver = new RemoteWebDriver(new URL(properties.getProperty("config.hub")), DesiredCapabilities.firefox());
            }
            else if (browser.equalsIgnoreCase("chrome")) {
                driver = new RemoteWebDriver(new URL(properties.getProperty("config.hub")), DesiredCapabilities.chrome());
            }
            else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

        }
        else {

            // use local browser

            if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
            else if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            }
            else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        /*
            configure the driver to implicitly wait for elements to be loaded.
            10 seconds may seem like a long time, but selenium polls the DOM, so it will
            return quickly if the element is found.  This eliminates the need for Thread.sleep(...)
            throughout the tests.  For more details, please refer to implicit waits in the Selenium documentation.
        */
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1200, 750));
    }

    @After(order = 1)
    public void afterScenarioCloseSeleniumDriver() {

        if (driver != null) {
          driver.close();
          driver.quit();
        }
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    public static String getSiteURL() {

        String environment = properties.getProperty("config.environment");

        if ("local".equals(environment)) {
            return properties.getProperty("config.url.local");
        }
        else if ("buildverification".equals(environment)) {
            return properties.getProperty("config.url.buildverification");
        }
        else if ("dev".equals(environment)) {
            return properties.getProperty("config.url.dev");
        }
        else if ("prod".equals(environment)) {
            return properties.getProperty("config.url.prod");
        }
        else {
            throw new IllegalArgumentException("Unsupported target environment: " + environment);
        }
    }
}
