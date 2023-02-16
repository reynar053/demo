package pageobjects.elements;

import baseelement.BaseElement;
import org.openqa.selenium.By;

public class Button extends BaseElement {
    public Button(By uniqueLocator, String name){
        super(uniqueLocator, name);
    }
}
