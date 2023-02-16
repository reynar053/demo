package baseelement;

import common.driver.Waits;
import common.driver.BrowserSingleton;
import common.logger.LoggerSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseElement {
    protected final By uniqueLocator;
    protected final String name;

    public BaseElement(By uniqueLocator, String name) {
        this.uniqueLocator = uniqueLocator;
        this.name = name;
    }

    protected WebElement getWebElement() {
        LoggerSingleton.info("Getting the '{}' element", name);
        return BrowserSingleton.getBrowser().getDriver().findElement(uniqueLocator);
    }

    public String getName() {
        return name;
    }

    public void click() {
        LoggerSingleton.info("Clicking the '{}' element", name);
        Waits.waitToBeClickable(uniqueLocator);
        getWebElement().click();
    }

    public String getText() {
        LoggerSingleton.info("Getting text from the '{}' element", name);
        Waits.waitToBeVisible(uniqueLocator);
        return getWebElement().getText();
    }
}
