package pageobjects.forms;

import common.driver.BrowserSingleton;
import org.openqa.selenium.By;
import basepage.BaseForm;
import pageobjects.elements.Frame;
import pageobjects.elements.Label;

public class FramesForm extends BaseForm {
    private final Frame firstFrame = new Frame(By.xpath("//iframe[@id='frame1']"), "First frame");
    private final Label textInsideFrame = new Label(By.xpath("//h1[@id='sampleHeading']"), "Text inside Parent Frame");
    private final Frame secondFrame = new Frame(By.xpath("//iframe[@id='frame2']"), "Second frame");

    public FramesForm(){
        super(By.xpath("//div[@id='framesWrapper']"), "Frames page");
    }

    public String getTextInFirstFrame(){
        firstFrame.switchToCurrentFrame();
        String result = textInsideFrame.getText();
        BrowserSingleton.getBrowser().switchToDefault();
        return result;
    }

    public String getTextInSecondFrame(){
        secondFrame.switchToCurrentFrame();
        String result = textInsideFrame.getText();
        BrowserSingleton.getBrowser().switchToDefault();
        return result;
    }
}
