package pageobjects.elements;

import baseelement.BaseElement;
import org.openqa.selenium.By;

public class Label extends BaseElement {
    public Label(By uniqueLocator, String name){
        super(uniqueLocator, name);
    }
}
