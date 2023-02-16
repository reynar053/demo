package pageobjects.forms;

import basepage.BaseForm;
import org.openqa.selenium.By;
import pageobjects.elements.Button;

public final class AlertsDropdownMenu extends BaseForm {
    private final String headerPath = "//div[contains(text(),'Alerts, Frame & Windows')]//ancestor::div[contains(@class, 'element-group')]";
    private final Button browserWindows = new Button(By.xpath(headerPath + "//li[@id='item-0']"), "Browser windows");
    private final Button alerts = new Button(By.xpath(headerPath + "//li[@id='item-1']"), "Alerts");
    private final Button frames = new Button(By.xpath(headerPath + "//li[@id='item-2']"), "Frames");
    private final Button nestedFrames = new Button(By.xpath(headerPath + "//li[@id='item-3']"), "Nested frames");

    public AlertsDropdownMenu(){
        super(By.xpath("//div[contains(text(),'Alerts, Frame & Windows')]" +
                "//ancestor::div[contains(@class, 'element-group')]//div[contains(@class, 'element-list collapse show')]"), "Alerts Menu");
    }

    public AlertsForm openAlertsForm(){
        alerts.click();
        return new AlertsForm();
    }

    public BrowserWindowsForm openBrowserWindowsForm(){
        browserWindows.click();
        return new BrowserWindowsForm();
    }

    public NestedFramesForm openNestedFramesForm(){
        nestedFrames.click();
        return new NestedFramesForm();
    }

    public FramesForm openFramesForm(){
        frames.click();
        return new FramesForm();
    }
}
