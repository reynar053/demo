package common.driver;

import common.logger.LoggerSingleton;

public abstract class BrowserSingleton {
    private static Browser browser;

    private BrowserSingleton() {
    }

    public static Browser getBrowser() {
        if (browser == null) {
            browser = BrowserFactory.initBrowser();
            Waits.initWaits();
        }
        return browser;
    }

    public static void quit() {
        LoggerSingleton.info("Terminating the browser");
        browser.quit();
        browser = null;
        Waits.clearWaits();
    }
}
