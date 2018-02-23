package com.framework.ui.selenium.test;
import com.framework.ui.selenium.base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BasicTestWDIT extends DriverFactory {
    private void googleExampleThatSearchFor(final String searchString) throws Exception{
        WebDriver driver = DriverFactory.getDriver();

        driver.get("http://google.com");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.clear();
        searchField.sendKeys(searchString);
        System.out.println("Page title is : " + driver.getTitle());
        searchField.submit();

        (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driverObject){
                        return driverObject.getTitle().toLowerCase()
                                .startsWith(searchString.toLowerCase());
                    }
        });

        System.out.println("Page title is: " + driver.getTitle());
    }

    @Test
    public void googleCheeseExample() throws  Exception{
        System.out.print("Test 1");
        googleExampleThatSearchFor("Cheese!");
    }

    @Test
    public void googleMilkExample() throws Exception{
        System.out.print("Test 2");
        googleExampleThatSearchFor("Milk");
    }

    @Test
    public void googleSachinExample() throws Exception{
        System.out.print("Test 3");
        googleExampleThatSearchFor("Sachin");
    }
    @Test
    public void googleKholiExample() throws  Exception{
        System.out.print("Test 4");
        googleExampleThatSearchFor("Kholi!");
    }

    @Test
    public void googleDravidExample() throws Exception{
        System.out.print("Test 5");
        googleExampleThatSearchFor("Dravid");
    }

    @Test
    public void googleGeneralElectricExample() throws Exception{
        System.out.print("Test 6");
        googleExampleThatSearchFor("General Electric");
    }
}