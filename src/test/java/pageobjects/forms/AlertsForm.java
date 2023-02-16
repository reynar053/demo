package pageobjects.forms;

import common.logger.LoggerSingleton;
import org.openqa.selenium.By;
import basepage.BaseForm;
import pageobjects.elements.Button;
import pageobjects.elements.Label;

public class AlertsForm extends BaseForm {
    private final Button alertButton = new Button(By.xpath("//button[@id='alertButton']"), "Alert button");
    private final Button confirmButton = new Button(By.xpath("//button[@id='confirmButton']"), "Confirm button");
    private final Label confirmResult = new Label(By.xpath("//span[@id='confirmResult']"), "Confirm result");
    private final Button promptButton = new Button(By.xpath("//button[@id='promtButton']"), "Prompt button");
    private final Label promptResult = new Label(By.xpath("//span[@id='promptResult']"), "Prompt result");

    public AlertsForm(){
        super(By.xpath("//div[contains(text(),'Alerts, Frame & Windows')]//ancestor::div[contains(@class, 'element-group')]"), "Alerts page");
    }

    public void clickAlertButton() {
        alertButton.click();
    }

    public void clickConfirmButton(){
        confirmButton.click();
    }

    public void clickPromptButton(){
        promptButton.click();
    }

    public String getConfirmResultText(){
        return confirmResult.getText();
    }

    public String getPromptResultText(){
        LoggerSingleton.info("Getting text from the prompt in {}", getName());
        return promptResult.getText();
    }
}
