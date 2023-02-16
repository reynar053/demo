package pageobjects.forms;

import basepage.BaseForm;
import org.openqa.selenium.By;
import pageobjects.elements.Button;

public final class ElementsDropdownMenu extends BaseForm {
    private final String headerPath = "//div[contains(text(),'Elements')]//ancestor::div[contains(@class, 'element-group')]";
    private final Button webTables = new Button(By.xpath(headerPath + "//li[@id='item-3']"), "Web tables");
    private final Button links = new Button(By.xpath(headerPath + "//li[@id='item-5']"), "Links");

    public ElementsDropdownMenu() {
        super(By.xpath("//div[contains(text(),'Elements')]//ancestor::div[contains(@class, 'element-group')]" +
                "//div[contains(@class, 'element-list collapse show')]"), "Elements Menu");
    }

    public WebTablesForm openWebTables() {
        webTables.click();
        return new WebTablesForm();
    }

    public LinksForm openLinks() {
        links.click();
        return new LinksForm();
    }
}
