package com.framework.ui.selenium.test;

import com.framework.ui.selenium.base.DriverFactory;
import com.framework.ui.selenium.flow.ProductSearchFlow;
import com.framework.ui.selenium.realtimeReporting.ExecutionListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;

@Listeners(ExecutionListener.class)
public class BasicTestWDIT extends DriverFactory {


//    @Test
//    public void searchUsingWorkFlowPattern() throws Exception{
//        DriverFactory.getDriver().get("https://www.walmart.com/account/login?tid=0&returnUrl=%2F");
//
//        ProductSearchFlow.loginToWalmart( loginPage ->{
//            loginPage.enterUserName("deepu105045@gmail.com")
//                    .enterPassword("Infy1234")
//                    .andLogin();
//        }).productSearchFlow(searchPage -> {
//            Assert.assertTrue(searchPage.isAt());
//            Assert.assertEquals(searchPage.LoggedInUserName(),"Deepu");
//
//            searchPage.searchForItem("558274970");
//        });
//    }

    @Test
    public void dragAndDropTest() throws Exception{

        DriverFactory.getDriver().get("https://html5demos.com/drag/");
        final String JQUERY_LOAD_SCRIPT = ("/Users/deepu/Documents/dev/selenium/selenium-testng/src/test/java/com/framework/ui/selenium/jsUtils/jquery_load_helper.js");
        String jQueryLoader = readFile(JQUERY_LOAD_SCRIPT);

        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        			        js.executeAsyncScript(jQueryLoader /* , http://localhost:8080/jquery-1.7.2.js */);

        js.executeScript("jQuery(function($) { " + " $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); "
                			                + " }); ");

        String filePath = "/Users/deepu/Documents/dev/selenium/selenium-testng/src/test/java/com/framework/ui/selenium/jsUtils/drag_and_drop_helper.js";
        String source = "section#wrapper article ul li:nth-child(4) a";
        String target =  "section#wrapper article div";


        StringBuffer buffer = new StringBuffer();
        			      String line;
        			      BufferedReader br = new BufferedReader(new FileReader(filePath));
        			      while((line = br.readLine())!=null)
            			          buffer.append(line);
                			      String javaScript = buffer.toString();

                			      javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
        			      ((JavascriptExecutor)DriverFactory.getDriver()).executeScript(javaScript);


                        			      Thread.sleep(1000);
        			      source = "section#wrapper article ul li:nth-child(2) a";
        			      javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
        			      ((JavascriptExecutor)DriverFactory.getDriver()).executeScript(javaScript);

                			      Thread.sleep(1000);
        			      source = "section#wrapper article ul li:nth-child(1) a";
        			      javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
        			      ((JavascriptExecutor)DriverFactory.getDriver()).executeScript(javaScript);

                			      Thread.sleep(1000);



       // jQueryDragAndDrop();

//        By from = By.cssSelector("#column-a");
//        By to = By.cssSelector("#column-b");
//        dragdrop(from,to);
        Thread.sleep(20000);


    }

    public static void dragdrop(By ByFrom, By ByTo) throws  Exception {
        WebElement LocatorFrom = DriverFactory.getDriver().findElement(ByFrom);
        WebElement LocatorTo = DriverFactory.getDriver().findElement(ByTo);
        String xto=Integer.toString(LocatorTo.getLocation().x);
        String yto=Integer.toString(LocatorTo.getLocation().y);
        ((JavascriptExecutor)DriverFactory.getDriver()).executeScript("function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
                        "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
                LocatorFrom,xto,yto);
    }

    public static void jQueryDragAndDrop() throws  Exception{
        String dragAndDropFilePath = "/Users/deepu/Documents/dev/selenium/selenium-testng/src/test/java/com/framework/ui/selenium/jsUtils/drag_and_drop_helper.js";
        String javaScript ="" ;
        String text;

        BufferedReader input = new BufferedReader(new FileReader(dragAndDropFilePath));
        StringBuffer buffer = new StringBuffer();

        while ((text = input.readLine()) != null)
            buffer.append(text+ "");
            javaScript = buffer.toString();
            input.close();

        String source = "column-a";
        String target = "column-b";

        javaScript = javaScript+"jQuery('#"+source+"').simulate( '#" +target+ "');" ;
//
//        String javascript =
//                "var dropTarget = jQuery(" + dropTargetJQuerySelector + ");" +
//                        "\n" +
//                        "jQuery("+ dragSourceJQuerySelector + ").simulate('drag', { dropTarget: dropTarget });";


        ((JavascriptExecutor)DriverFactory.getDriver()).executeScript(javaScript);
    }

        private static String readFile(String file) throws IOException {
                Charset cs = Charset.forName("UTF-8");
                FileInputStream stream = new FileInputStream(file);
                try {
                        Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
                        StringBuilder builder = new StringBuilder();
                        char[] buffer = new char[8192];
                        int read;
                        while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                                builder.append(buffer, 0, read);
                            }
                        return builder.toString();
                    } finally {
                        stream.close();
                    }
            }

}