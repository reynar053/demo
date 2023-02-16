import common.configmanager.ConfigManager;
import common.driver.BrowserSingleton;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void openHomePage() {
        BrowserSingleton.getBrowser().maximize();
        BrowserSingleton.getBrowser().goToURL(ConfigManager.getConfigData().getURL());
    }

    @AfterMethod(alwaysRun = true)
    public void terminateDriver() {
        BrowserSingleton.quit();
    }
}
