package pageobjects.forms;

import common.driver.BrowserSingleton;
import common.logger.LoggerSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import basepage.BaseForm;
import pageobjects.elements.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebTablesForm extends BaseForm {
    private final Button addButton = new Button(By.xpath("//button[@id='addNewRecordButton']"), "Add button");

    public WebTablesForm() {
        super(By.xpath("//div[@role='grid']"), "Web tables page");
    }

    public void clickAddButton() {
        addButton.click();
    }

    public List<Map<String, String>> getRows() {
        LoggerSingleton.info("Parsing user records from the {}", name);
        List<Map<String, String>> result = new ArrayList<>();
        List<WebElement> rows = BrowserSingleton.getBrowser().getDriver()
                .findElements(By.xpath("//div[contains(@class, 'rt-tbody')]//div[@role='row']"));
        for (WebElement row : rows) {
            if (!row.getText().isBlank()) {
                result.add(getDataFromRowWebElement(row));
            }
        }
        return result;
    }

    private WebElement getRowByEmail(String email) {
        LoggerSingleton.info("Searching for '{}' in the {}", email, name);
        List<WebElement> rows = BrowserSingleton.getBrowser().getDriver()
                .findElements(By.xpath("//div[contains(@class, 'rt-tbody')]//div[@role='row']"));
        for (WebElement row : rows) {
            if (!row.getText().isBlank() && row.getText().contains(email)) {
                return row;
            }
        }
        return null;
    }

    private Map<String, String> getDataFromRowWebElement(WebElement row) {
        Map<String, String> userData = new HashMap<>();
        String[] cells = row.getText().split("\\r?\\n");
        for (int i = 0; i < cells.length; i++) {
            userData.put(findHeaders().get(i), cells[i]);
        }
        return userData;
    }

    /**
     * Finds row that contains email of the provided user and deletes it.
     *
     * @param user - user record to be deleted from the table.
     */
    public void deleteUser(Map<String, String> user) {
        LoggerSingleton.info("Deleting {} record from the table", user.toString());
        WebElement foundRow = getRowByEmail(user.get("Email"));
        if (foundRow != null) {
            foundRow.findElement(By.xpath(".//span[@title='Delete']")).click();
        }
    }

    private List<String> findHeaders() {
        List<String> result = new ArrayList<>();
        for (WebElement header : BrowserSingleton.getBrowser().getDriver()
                .findElements(By.xpath("//div[contains(@class, 'rt-resizable-header-content')]"))) {
            result.add(header.getText());
        }
        return result;
    }
}
