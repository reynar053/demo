package pageobjects.forms;

import org.openqa.selenium.By;
import basepage.BaseForm;

public class SampleForm extends BaseForm {
    public SampleForm() {
        super(By.xpath("//h1[@id='sampleHeading']"), "Sample page");
    }
}
