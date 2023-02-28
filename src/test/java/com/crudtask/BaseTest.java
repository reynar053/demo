package com.crudtask;

import com.crudtask.model.*;
import com.crudtask.config.SpringConfig;
import com.crudtask.config.TestConfig;
import com.crudtask.db.Dao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.*;

@Test
public class BaseTest {
    protected TestConfig testConfig;
    protected Dao<TestRow> testDao;
    protected Dao<ProjectRow> projectDao;
    protected Dao<AuthorRow> authorDao;
    protected Dao<StatusRow> statusDao;
    protected Dao<SessionRow> sessionDao;
    protected ProjectRow currProject;
    protected AuthorRow currAuthor;

    @BeforeTest(alwaysRun = true)
    public void springSetUp(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        String projectKeyColumnName = "name";
        String authorKeyColumnName = "name";

        testConfig = context.getBean(TestConfig.class);

        testDao = context.getBean(Dao.class);
        testDao.settClass(TestRow.class);

        projectDao = context.getBean(Dao.class);
        projectDao.settClass(ProjectRow.class);

        authorDao = context.getBean(Dao.class);
        authorDao.settClass(AuthorRow.class);

        statusDao = context.getBean(Dao.class);
        statusDao.settClass(StatusRow.class);

        sessionDao = context.getBean(Dao.class);
        sessionDao.settClass(SessionRow.class);

        currProject = projectDao.findByOrCreate(testConfig.getProject(), projectKeyColumnName, testConfig.getProject().getName());
        currAuthor = authorDao.findByOrCreate(testConfig.getAuthor(), authorKeyColumnName, testConfig.getAuthor().getName());
    }
}
