package com.framework.ui.selenium.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.framework.ui.selenium.base.DriverFactory.getDriver;

public class ScreenshotListener extends TestListenerAdapter{

    private boolean createFile(File screenshot) throws IOException{
        boolean fileCreated = false;
        if(screenshot.exists()){
            fileCreated = true;
        }else{
            File parentDirectory = new File(screenshot.getParent());
            if(parentDirectory.exists() || parentDirectory.mkdir()){
                fileCreated = screenshot.createNewFile();
            }
        }
        return fileCreated;
    }

    private void writeScreenshotToFile(WebDriver driver, File screenshot) throws IOException{
        FileOutputStream screenshotStream = new FileOutputStream(screenshot);
        screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        screenshotStream.close();
    }

    @Override
    public void onTestFailure(ITestResult failingTest){
        try{
            WebDriver driver = getDriver();
            String screenshotDirectory = System.getProperty("screenshotDirectory");
            String screenshotAbsolutepath = screenshotDirectory + File.separator + failingTest.getName()  +"_"
                    + System.currentTimeMillis() + ".png";

            File screenshot = new File(screenshotAbsolutepath);
            if(createFile(screenshot)){
                try{
                    writeScreenshotToFile(driver,screenshot);
                }catch (ClassCastException weNeedToAugmentOurDriverObject){
                    writeScreenshotToFile(new Augmenter().augment(driver),screenshot);
                }
                System.out.println("Written screenshot to " + screenshotAbsolutepath);
            }else {
                System.err.println("Unable to create " + screenshotAbsolutepath);
            }
        }catch (Exception ex){
            System.err.println("Unable to capture screenshot ...");
            ex.printStackTrace();
        }
    }
}
