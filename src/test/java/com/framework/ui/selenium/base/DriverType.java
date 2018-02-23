package com.framework.ui.selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public enum  DriverType implements DriverSetup {

    CHROME {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));

            HashMap<String,String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled","false");
            capabilities.setCapability("chrome.prefs", chromePreferences);

            return capabilities;
        }

        @Override
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities)  {
            ChromeDriverService service;
            ChromeOptions options;

            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("src/test/resources/driver/chromedriver"))
                    .usingAnyFreePort()
                    .build();
            try {
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.addArguments("disable-infobars");
            options.addArguments("-incognito");
            options.addArguments("start-maximized");
            options.merge(desiredCapabilities);
            return new ChromeDriver(service, options);


        }
    },

    SAFARI {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession",true);
            return capabilities;
        }

        @Override
        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            SafariDriverService service;
            SafariOptions options;

            service = new SafariDriverService.Builder()
                    .usingAnyFreePort()
                    .build();

            try{
                service.start();
            }catch (IOException e){
                e.printStackTrace();
            }

            options = new SafariOptions();
            options.merge(desiredCapabilities);
            return new SafariDriver(service,options);
        }
    }



}
