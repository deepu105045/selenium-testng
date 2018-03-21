package com.framework.ui.selenium.pageObjects;

import com.framework.ui.selenium.base.DriverFactory;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;

    public ProductPage() throws  Exception{
        this.driver= DriverFactory.getDriver();
    }
}
