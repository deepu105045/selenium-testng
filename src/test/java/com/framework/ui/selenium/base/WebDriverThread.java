package com.framework.ui.selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

import static com.framework.ui.selenium.base.DriverType.CHROME;
import static com.framework.ui.selenium.base.DriverType.valueOf;

public class WebDriverThread {
    private WebDriver webdriver;
    private DriverType selectedDriverType;

    private final DriverType defaultDriverType = CHROME;
    private final String browser = System.getProperty("browser").toUpperCase();
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch").toUpperCase();

    public WebDriver getDriver() throws Exception {
        if(null == webdriver){
            selectedDriverType = determineEffectiveDriverType();
            DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities();
            instantiateWebDriver(desiredCapabilities);
        }

        return webdriver;
    }

    public void quiteDriver(){
        if (null != webdriver){
            webdriver.quit();
            webdriver = null;
        }
    }

    private DriverType determineEffectiveDriverType(){
        DriverType driverType = defaultDriverType;
        try{
            driverType =  valueOf(browser);
        }catch (IllegalArgumentException ignored){
            System.err.println("Unknown driver specified  ... defaulting to " + driverType);
        }catch(NullPointerException ignored){
            System.err.println("No driver specified  ... defaulting to " + driverType);
        }
        return driverType;
    }

    private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) throws IOException {
        System.out.println("********************************");
        System.out.println("Current operating system: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current browser selection: " + selectedDriverType);
        System.out.println("********************************");

        webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);

    }
}
