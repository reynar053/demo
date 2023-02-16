package pageobjects.forms;

import org.openqa.selenium.By;
import basepage.BaseForm;
import pageobjects.elements.Button;

public class BrowserWindowsForm extends BaseForm {
    private final Button newTab = new Button(By.xpath("//button[@id='tabButton']"), "New tab button");

    public BrowserWindowsForm() {
        super(By.xpath("//button[@id='tabButton']"), "Browser Windows page");
    }

    public void clickNewTabButton() {
        newTab.click();
    }
}
