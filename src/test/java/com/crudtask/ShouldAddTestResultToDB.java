package com.crudtask;

import com.crudtask.model.SessionRow;
import com.crudtask.model.TestRow;
import com.crudtask.utils.DateUtil;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Calendar;

public class ShouldAddTestResultToDB extends BaseTest {
    SessionRow session;

    @Test
    public void runTest() {
        RestTemplate restTemplate = new RestTemplate();
        session = new SessionRow();
        session.setSessionKey(Integer.toString(restTemplate.hashCode()));
        session.setCreatedTime(Calendar.getInstance());
        sessionDao.create(session);

        String response = restTemplate.getForObject(testConfig.getUrl(), String.class);

        assert response != null;
        Assert.assertTrue(response.contains(testConfig.getExpectedEmailValue()), "Response contains wrong user email");
    }

    @AfterMethod(alwaysRun = true)
    public void addTestInfoToDB(ITestResult iTestResult) {
        TestRow testRow = new TestRow();

        testRow.setStartTime(DateUtil.msToCalendar(iTestResult.getStartMillis()));
        testRow.setEndTime(DateUtil.msToCalendar(iTestResult.getEndMillis()));
        testRow.setName(iTestResult.getTestContext().getName());
        testRow.setMethodName(iTestResult.getInstanceName());
        testRow.setEnv(System.getenv(testConfig.getEnvVar()));
        testRow.setStatus(statusDao.read(iTestResult.getStatus()));
        testRow.setBrowser(testConfig.getBrowser());
        testRow.setSession(session);
        testRow.setProject(currProject);
        testRow.setAuthor(currAuthor);

        testDao.create(testRow);

        Assert.assertEquals(testRow, testDao.read(testRow.getId()), "Test from the database is not equal to the created one");
    }
}