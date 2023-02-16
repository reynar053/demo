import common.configmanager.ConfigManager;
import common.driver.Alerts;
import models.User;
import common.driver.BrowserSingleton;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.forms.AlertsDropdownMenu;
import pageobjects.forms.*;
import utilities.StringUtilities;
import utilities.TableManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DemoQaTest extends BaseTest{
    @DataProvider(name = "user_data_provider_for_tables_test")
    public Object[][] dataProvider(){
        List<User> users = new ArrayList<>();
        for (Map<String, String> userMap : ConfigManager.getTestUserData()){
            int userNumber = Integer.parseInt(userMap.get("User Number"));
            userMap.remove("User Number");
            users.add(new User(userMap, userNumber));
        }
        Object[][] obj = new Object[users.size()][2];
        for (int i = 0; i < users.size(); i++) {
            obj[i][0] = users.get(i);
            obj[i][1] = users.get(i).getUserNumber();
        }
        return obj;
    }

    @Test(testName = "Test 1 - Alerts")
    @Parameters({"Text after closing an alert"})
    public void alertsTest(String textOK){
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isFormOpened(), "The main page have not been opened");
        homePage.clickOnAlertsCard();

        AlertsForm alertsForm = new MenuForm().expandAlertsFrameWindows().openAlertsForm();
        Assert.assertTrue(alertsForm.isFormOpened(), "Alerts page have not appeared");
        alertsForm.clickAlertButton();
        Assert.assertTrue(Alerts.isAlertPresent(), "The alert have not been opened");
        Alerts.acceptAlert();
        Assert.assertFalse(Alerts.isAlertPresent(), "The alert is still open");

        alertsForm.clickConfirmButton();
        Assert.assertTrue(Alerts.isAlertPresent(), "The alert have not been opened");
        Alerts.acceptAlert();
        Assert.assertFalse(Alerts.isAlertPresent(), "The alert is still open");
        Assert.assertEquals(alertsForm.getConfirmResultText(), textOK, "text 'you selected OK' is not on the page");

        alertsForm.clickPromptButton();
        Assert.assertEquals(Alerts.getAlertText(), "Please enter your name", "The prompt alert have not been opened");
        String randomString = StringUtilities.generateRandomString(ConfigManager.getConfigData().getRANDOM_STRING_LENGTH());
        Alerts.typeInPromptAlert(randomString);
        Alerts.acceptAlert();
        Assert.assertFalse(Alerts.isAlertPresent(), "The alert is still open");
        String promptText = alertsForm.getPromptResultText();
        Assert.assertEquals(randomString, StringUtilities.getLastWordInString(promptText), "The entered string does not match the string on the page");
    }

    @Test(testName = "Test 2 - Iframe")
    @Parameters({"Parent frame text", "Child frame text"})
    public void iFrameTest(String expectedTextInParentFrame, String expectedTextInChildFrame){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isFormOpened(), "The main page have not been opened");
        homePage.clickOnAlertsCard();

        NestedFramesForm nestedFramesForm = new MenuForm().expandAlertsFrameWindows().openNestedFramesForm();
        Assert.assertTrue(nestedFramesForm.isFormOpened(), "Page with Nested Frames form is not open");
        String text1 = nestedFramesForm.getTextInParentFrame();
        String text2 = nestedFramesForm.getTextInChildFrame();
        softAssert.assertTrue(text1.equals(expectedTextInParentFrame), "There is no message 'Parent frame' present on page");
        softAssert.assertTrue(text2.equals(expectedTextInChildFrame), "There is no message 'Child Iframe' present on page");

        FramesForm framesForm = new AlertsDropdownMenu().openFramesForm();
        Assert.assertTrue(framesForm.isFormOpened(), "Page with Frames form is not open");
        softAssert.assertEquals(framesForm.getTextInFirstFrame(), framesForm.getTextInSecondFrame(),
                "Message from the upper frame is not equal to the message from the lower frame");
        softAssert.assertAll();
    }

    @Test(testName = "Test 3 - Tables", dataProvider = "user_data_provider_for_tables_test")
    public void tablesTest(User userToBeAdded, int userNumber){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isFormOpened(), "The main page have not been opened");
        homePage.clickOnElementsCard();

        WebTablesForm webTablesForm = new MenuForm().expandElements().openWebTables();
        Assert.assertTrue(webTablesForm.isFormOpened(), "Page with Web Tables form is not open");

        webTablesForm.clickAddButton();
        RegistrationForm registrationForm = new RegistrationForm();
        Assert.assertTrue(registrationForm.isFormOpened(), "Registration Form has not appeared on page");
        TableManagement.fillRegistrationFormWith(registrationForm, userToBeAdded);
        registrationForm.submitForm();
        Assert.assertTrue(registrationForm.isFormClosed(), "Registration form has not closed");

        List<User> recordsInTable = new ArrayList<>();
        for (Map<String, String> row : webTablesForm.getRows()) {
            recordsInTable.add(new User(row));
        }
        softAssert.assertTrue(recordsInTable.contains(userToBeAdded), "Data of User Number " + userNumber + " has not appeared in a table");

        int numberOfRecordsWithAddedUser = webTablesForm.getRows().size();
        webTablesForm.deleteUser(userToBeAdded.getMap());
        softAssert.assertNotEquals(numberOfRecordsWithAddedUser, webTablesForm.getRows().size(), "Number of records in table has changed");

        recordsInTable.clear();
        for (Map<String, String> row : webTablesForm.getRows()) {
            recordsInTable.add(new User(row));
        }
        softAssert.assertFalse(recordsInTable.contains(userToBeAdded), "Data of User Number " + userNumber + " has not been deleted from table");
        softAssert.assertAll();
    }

    @Test(testName = "Test 4 - Handles")
    public void handlesTest(){
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isFormOpened(), "The main page have not been opened");
        homePage.clickOnAlertsCard();

        BrowserWindowsForm browserWindowsForm = new MenuForm().expandAlertsFrameWindows().openBrowserWindowsForm();
        Assert.assertTrue(browserWindowsForm.isFormOpened(), "Page with Browser Windows form is not open");
        browserWindowsForm.clickNewTabButton();
        BrowserSingleton.getBrowser().switchWindowHandleToNewOpenedTab();
        SampleForm sampleForm = new SampleForm();
        Assert.assertTrue(sampleForm.isFormOpened(), "New tab with sample page is not open");
        BrowserSingleton.getBrowser().close();
        BrowserSingleton.getBrowser().switchWindowHandle(0);

        Assert.assertTrue(browserWindowsForm.isFormOpened(), "Page with Browser Windows form is not open");
        LinksForm linksForm = new MenuForm().expandElements().openLinks();
        Assert.assertTrue(linksForm.isFormOpened(), "Page with Links form is not open");
        linksForm.clickHomeButton();
        Assert.assertTrue(homePage.isFormOpened(), "New tab with main page is open");
        BrowserSingleton.getBrowser().switchWindowHandle(0);
        Assert.assertTrue(linksForm.isFormOpened(), "Page with Links form is not open");
    }
}
