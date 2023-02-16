package pageobjects.elements;

import baseelement.BaseElement;
import common.driver.BrowserSingleton;
import org.openqa.selenium.By;

public class Frame extends BaseElement {
    public Frame(By uniqueLocator, String name){
        super(uniqueLocator, name);
    }

    public void switchToCurrentFrame(){
        BrowserSingleton.getBrowser().switchToFrame(getWebElement());
    }
}
