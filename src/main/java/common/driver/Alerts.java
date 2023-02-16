package common.driver;

import common.logger.LoggerSingleton;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

public abstract class Alerts {
    private Alerts() {
    }

    public static boolean isAlertPresent() {
        try {
            BrowserSingleton.getBrowser().getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public static String getAlertText() {
        LoggerSingleton.info("Getting text from the alert");
        if (isAlertPresent()) {
            String alertText = BrowserSingleton.getBrowser().getDriver().switchTo().alert().getText();
            LoggerSingleton.info("Text from alert: {}", alertText);
            return alertText;
        }
        throw new RuntimeException("No alerts is opened");
    }

    public static void acceptAlert() {
        LoggerSingleton.info("Accepting the alert");
        BrowserSingleton.getBrowser().getDriver().switchTo().alert().accept();
    }

    public static void typeInPromptAlert(String textToType) {
        LoggerSingleton.info("'{}' text is typed in prompt", textToType);
        Alert alert = BrowserSingleton.getBrowser().getDriver().switchTo().alert();
        alert.sendKeys(textToType);
    }
}
