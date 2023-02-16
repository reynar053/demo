package common.driver;

import common.logger.LoggerSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Browser {
    private final WebDriver driver;

    Browser(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void goToURL(String url) {
        LoggerSingleton.info("Opening {}", url);
        driver.get(url);
    }

    public void quit() {
        LoggerSingleton.info("Closing the driver window");
        driver.quit();
    }

    public void close() {
        LoggerSingleton.info("Closing the driver tab");
        driver.close();
    }

    public void maximize() {
        LoggerSingleton.info("Maximising the browser");
        driver.manage().window().maximize();
    }

    public void switchToFrame(WebElement frameElement) {
        Waits.waitForFrameAndSwitchToIt(frameElement);
    }

    public void switchWindowHandle(int windowIndex) {
        ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(windowIndex));
    }

    public void switchWindowHandleToNewOpenedTab() {
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(tabs2.size() - 1));
    }

    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }
}
