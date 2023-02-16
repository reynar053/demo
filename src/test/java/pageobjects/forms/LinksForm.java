package pageobjects.forms;

import common.driver.BrowserSingleton;
import org.openqa.selenium.By;
import basepage.BaseForm;
import pageobjects.elements.Button;

public class LinksForm extends BaseForm {
    private final Button homeButton = new Button(By.xpath("//a[@id='simpleLink']"), "Home button");

    public LinksForm() {
        super(By.xpath("//a[@id='simpleLink']"), "Links page");
    }

    public void clickHomeButton(){
        homeButton.click();
        BrowserSingleton.getBrowser().switchWindowHandleToNewOpenedTab();
    }
}
