package com.framework.ui.selenium.test;

import com.framework.ui.selenium.base.DriverFactory;
import com.framework.ui.selenium.flow.ProductSearchFlow;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicTestWDIT extends DriverFactory {


    @Test
    public void searchUsingWorkFlowPattern() throws Exception{
        DriverFactory.getDriver().get("https://www.walmart.com/account/login?tid=0&returnUrl=%2F");

        ProductSearchFlow.loginToWalmart( loginPage ->{
            loginPage.enterUserName("deepu105045@gmail.com")
                    .enterPassword("Infy1234")
                    .andLogin();
        }).productSearchFlow(searchPage -> {
            Assert.assertTrue(searchPage.isAt());
            Assert.assertEquals(searchPage.LoggedInUserName(),"Deepu");

            searchPage.searchForItem("558274970");
        });
    }

}