package basepage;

import common.driver.BrowserSingleton;
import common.driver.Waits;
import common.logger.LoggerSingleton;
import org.openqa.selenium.*;

public abstract class BaseForm {
    protected final By uniqueLocator;
    protected final String name;

    public BaseForm(By uniqueLocator, String name){
        this.name = name;
        this.uniqueLocator = uniqueLocator;
    }

    public String getName() {
        return name;
    }

    public boolean isFormOpened(){
        LoggerSingleton.info("Opening the '{}' form", name);
        try{
            BrowserSingleton.getBrowser().getDriver().findElement(uniqueLocator);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isFormClosed(){
        try {
            Waits.waitToBeInvisible(uniqueLocator);
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }
}
