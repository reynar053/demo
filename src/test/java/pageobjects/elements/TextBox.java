package pageobjects.elements;

import baseelement.BaseElement;
import common.logger.LoggerSingleton;
import org.openqa.selenium.By;

public class TextBox extends BaseElement {
    public TextBox(By uniqueLocator, String name){
        super(uniqueLocator, name);
    }

    public void type(String text){
        LoggerSingleton.info("Typing '{}' in the {}", text, name);
        getWebElement().sendKeys(text);
    }
}
