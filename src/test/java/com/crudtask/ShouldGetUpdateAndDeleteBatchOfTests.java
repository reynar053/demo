package com.crudtask;

import com.crudtask.model.TestRow;
import com.crudtask.utils.StringUtil;
import com.crudtask.model.SessionRow;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ShouldGetUpdateAndDeleteBatchOfTests extends BaseTest {

    @DataProvider(name = "tests_provider")
    public Object[][] dpMethod() {
        String repeatingNumbers = StringUtil.getRndRepeatingNmbrs(testConfig.getRepeatingNumbersAmount());
        List<TestRow> tests = testDao.getRowsWithRepeatingNumberId(repeatingNumbers, testConfig.getRowLimit());
        Object[][] data = new Object[tests.size()][1];

        for (int i = 0; i < tests.size(); i++) {
            System.out.println(tests.get(i).getId());
            var newTest = new TestRow(tests.get(i));
            newTest.setProject(currProject);
            newTest.setAuthor(currAuthor);
            testDao.create(newTest);
            data[i][0] = newTest;
        }

        return data;
    }

    @Test(dataProvider = "tests_provider")
    public void runTest(TestRow test) {
        SessionRow currSession = new SessionRow();

        test.setStartTime(Calendar.getInstance());
        currSession.setCreatedTime(Calendar.getInstance());
        currSession.setSessionKey(Integer.toString(test.hashCode()));

        sessionDao.create(currSession);

        test.setSession(currSession);
        test.setStatus(statusDao.read(ThreadLocalRandom.current().nextInt(ITestResult.SUCCESS, ITestResult.SKIP + 1)));
        test.setEndTime(Calendar.getInstance());

        testDao.update(test);

        Assert.assertEquals(test, testDao.read(test.getId()), "Test is not created");
    }

    @AfterMethod(alwaysRun = true)
    public void deleteRecords(Object[] parameters) {
        TestRow test = (TestRow) parameters[0];
        testDao.delete(test.getId());
        Assert.assertNull(testDao.read(test.getId()), "Test is not deleted");
    }
}
