package pageobjects.forms;

import org.openqa.selenium.By;
import basepage.BaseForm;
import pageobjects.elements.Button;

public class HomePage extends BaseForm {
    private final Button elements = new Button(By.xpath("//div[contains(@class, 'card-body')]//*[contains(text(),'Elements')]"), "Elements card");
    private final Button alertsFramesWindows = new Button(By.xpath("//div[contains(@class, 'card-body')]//*[contains(text(),'Alerts, Frame & Windows')]"), "alerts card");

    public HomePage(){
        super(By.xpath("//div[contains(@class, 'home-banner')]"), "Home page");
    }

    public void clickOnElementsCard(){
        elements.click();
    }
    public void clickOnAlertsCard(){
        alertsFramesWindows.click();
    }
}
