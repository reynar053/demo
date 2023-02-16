package pageobjects.forms;

import org.openqa.selenium.By;
import baseelement.BaseElement;
import pageobjects.elements.Button;

public final class MenuForm extends BaseElement {
    private final Button elements = new Button(By.xpath("//div[contains(text(),'Elements')]" +
            "//ancestor::div[contains(@class, 'element-group')]"),
            "Elements");
    private final Button alertsFrameWindows = new Button(By.xpath("//div[contains(text(),'Alerts, Frame & Windows')]" +
            "//ancestor::div[contains(@class, 'element-group')]"),
            "Alerts");

    public MenuForm(){
        super(By.xpath(""), "Menu Form");
    }

    public AlertsDropdownMenu expandAlertsFrameWindows(){
        if(!new AlertsDropdownMenu().isFormOpened()){
            alertsFrameWindows.click();
        }
        return new AlertsDropdownMenu();
    }

    public ElementsDropdownMenu expandElements(){
        if(!new ElementsDropdownMenu().isFormOpened()){
            elements.click();
        }
        return new ElementsDropdownMenu();
    }
}
