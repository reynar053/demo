package common.driver;

import common.configmanager.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Waits {
    private static WebDriverWait wait;

    private Waits() {

    }

    static void initWaits() {
        wait = new WebDriverWait(BrowserSingleton.getBrowser().getDriver(), ConfigManager.getConfigData().getEXPLICIT_WAIT_TIME());
        BrowserSingleton.getBrowser().getDriver().manage().timeouts().implicitlyWait(ConfigManager.getConfigData().getIMPLICIT_WAIT_TIME());
    }

    static void clearWaits() {
        wait = null;
    }

    public static void waitToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitToBeInvisible(By locator){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForFrameAndSwitchToIt(WebElement webElement) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));
    }
}
