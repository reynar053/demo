package com.a1qa;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import com.a1qa.utils.Configs;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected Browser browser;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(Configs.getValueFromData("/url"));
        browser.waitForPageToLoad();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        browser.quit();
        browser = null;
    }
}
