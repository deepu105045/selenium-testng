package com.framework.ui.selenium.flow;

import com.framework.ui.selenium.base.DriverFactory;
import com.framework.ui.selenium.pageObjects.LoginPage;
import com.framework.ui.selenium.pageObjects.SearchPage;

import java.util.function.Consumer;

public class ProductSearchFlow {

    public static ProductSearchFlow loginToWalmart(Consumer<LoginPage> c) throws Exception{
        LoginPage loginPage = new LoginPage();
        c.accept(loginPage);
        return new ProductSearchFlow();
    }

    public ProductSearchFlow productSearchFlow(Consumer<SearchPage> s) throws Exception{
        SearchPage searchPage = new SearchPage();
        s.accept(searchPage);
        return this;
    }

}
