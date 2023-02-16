package common.driver;

import common.configmanager.ConfigManager;
import common.logger.LoggerSingleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;

public abstract class BrowserFactory {
    private BrowserFactory() {
    }

    static Browser initBrowser() {
        LoggerSingleton.info("Creating the browser");
        WebDriver driver;
        switch (ConfigManager.getBrowserCaps().getDRIVER_TO_USE()) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                for (String option : ConfigManager.getBrowserCaps().getCHROME_OPTIONS()) {
                    chromeOptions.addArguments(option);
                }
                driver = new ChromeDriver(chromeOptions);
                LoggerSingleton.info("{} Driver has been created with provided options: {}",
                        ConfigManager.getBrowserCaps().getDRIVER_TO_USE(), ConfigManager.getBrowserCaps().getCHROME_OPTIONS());
            }
            case SAFARI -> {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                LoggerSingleton.info("{} Driver has been created with provided options: {}",
                        ConfigManager.getBrowserCaps().getDRIVER_TO_USE(), "No options for SAFARI");
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().forceDownload();
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                for (String option : ConfigManager.getBrowserCaps().getFIREFOX_OPTIONS()) {
                    firefoxOptions.addArguments(option);
                }
                driver = new FirefoxDriver(firefoxOptions);
                LoggerSingleton.info("{} Driver has been created with provided options: {}",
                        ConfigManager.getBrowserCaps().getDRIVER_TO_USE(), ConfigManager.getBrowserCaps().getFIREFOX_OPTIONS());
            }
            case IE -> {
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
                for (String option : ConfigManager.getBrowserCaps().getIE_OPTIONS()) {
                    ieOptions.addCommandSwitches(option);
                }
                driver = new InternetExplorerDriver(ieOptions);
                LoggerSingleton.info("{} Driver has been created with provided options: {}",
                        ConfigManager.getBrowserCaps().getDRIVER_TO_USE(), ConfigManager.getBrowserCaps().getIE_OPTIONS());
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                for (String option : ConfigManager.getBrowserCaps().getEDGE_OPTIONS()) {
                    options.addArguments(option);
                }
                driver = new EdgeDriver(options);
                LoggerSingleton.info("{} Driver has been created with provided options: {}",
                        ConfigManager.getBrowserCaps().getDRIVER_TO_USE(), ConfigManager.getBrowserCaps().getEDGE_OPTIONS());
            }
            default -> throw new IllegalArgumentException("Wrong driver type has been passed");
        }
        return new Browser(driver);
    }
}
