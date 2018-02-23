package com.framework.ui.selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public interface DriverSetup {
    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) throws IOException;
    DesiredCapabilities getDesiredCapabilities();
}
