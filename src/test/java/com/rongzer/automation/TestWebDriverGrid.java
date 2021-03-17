package com.rongzer.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestWebDriverGrid {
    public static By name = By.id("name");
    @DataProvider(name = "haha")
    public Object[][] haha() {
        return new Object[][]{{"chrome"}, {"firefox"}};
    }

    @Test(dataProvider = "haha")
    public void test(String param1) throws InterruptedException, MalformedURLException {
        DesiredCapabilities dc = null;
        if(param1.equals("chrome")){
            dc = DesiredCapabilities.chrome();
        }else if(param1.equals("firefox")){
//            System.setProperty("webdriver.firefox.marionette","E:\\Automation\\Project1\\src\\drivers\\geckodriver.exe");
            dc = DesiredCapabilities.firefox();
        }

        WebDriver webDriver = new RemoteWebDriver(new URL("http://10.254.201.25:4446/wd/hub"),dc);
        webDriver.get("http://www.baidu.com");
        Thread.sleep(5000);
        webDriver.quit();
    }

    @Test
    public void testWebDriverGrid() throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        WebDriver webDriver = new RemoteWebDriver(new URL("http://10.254.201.25:4446/wd/hub"), dc);
//        WebDriver webDriver = new RemoteWebDriver(new URL("http://10.254.201.25:5566/wd/hub"),dc);
//        WebDriver webDriver = new RemoteWebDriver(new URL("http://10.254.201.25:5555/wd/hub"),dc);
        webDriver.get("http://www.baidu.com");

        Thread.sleep(5000);
        webDriver.quit();
    }
}