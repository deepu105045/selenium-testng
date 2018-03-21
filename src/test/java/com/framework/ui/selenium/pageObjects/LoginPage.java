package com.framework.ui.selenium.pageObjects;

import com.framework.ui.selenium.base.DriverFactory;
import com.framework.ui.selenium.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page{
    WebDriver driver;

    public LoginPage() throws Exception {
        this.driver= DriverFactory.getDriver();
    }
    private static final By email = By.name("email");
    private static final By password = By.name("password");
    private static final By signInButton = By.cssSelector("[data-automation-id='signin-submit-btn']");

    public LoginPage enterUserName(String userName){
        driver.findElement(email).sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String pwd){
        driver.findElement(password).sendKeys(pwd);
        return this;
    }

    public void andLogin(){
        driver.findElement(signInButton).submit();
    }

    @Override
    public boolean isAt(){
        return true;
    }
}
