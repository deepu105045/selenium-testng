package com.framework.ui.selenium.pageObjects;

import com.framework.ui.selenium.base.DriverFactory;
import com.framework.ui.selenium.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchPage extends Page{
    WebDriver driver;

    public SearchPage() throws Exception{
        this.driver = DriverFactory.getDriver();
    }

    private static final By username = By.cssSelector(".header-GlobalAccountFlyout-customerName");
    private static final By searchbox = By.cssSelector("[data-automation-id='header-search-input']");


    public String LoggedInUserName(){
        return driver.findElement(username).getText();
    }

    public void searchForItem(String item){
        driver.findElement(searchbox).sendKeys(item);
        driver.findElement(searchbox).submit();
    }

    @Override
    public boolean isAt(){
        try{
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(username));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
