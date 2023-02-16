package pageobjects.forms;

import common.driver.BrowserSingleton;
import org.openqa.selenium.By;
import basepage.BaseForm;
import pageobjects.elements.Frame;
import pageobjects.elements.Label;

public class NestedFramesForm extends BaseForm {
    private final Frame parentFrame = new Frame(By.xpath("//iframe[@id='frame1']"), "Parent frame");
    private final Label textInsideParentFrame = new Label(By.xpath("//body"), "Text inside Parent Frame");
    private final Frame childFrame = new Frame(By.xpath("//iframe"), "Child frame");
    private final Label textInsideChildFrame = new Label(By.xpath("//p"), "Text inside Child Frame");

    public NestedFramesForm(){
        super(By.xpath("//div[@id='framesWrapper']"), "Nested frames page");
    }

    public String getTextInParentFrame(){
        parentFrame.switchToCurrentFrame();
        String result = textInsideParentFrame.getText();
        BrowserSingleton.getBrowser().switchToDefault();
        return result;
    }

    public String getTextInChildFrame(){
        parentFrame.switchToCurrentFrame();
        childFrame.switchToCurrentFrame();
        String result = textInsideChildFrame.getText();
        BrowserSingleton.getBrowser().switchToDefault();
        return result;
    }
}
