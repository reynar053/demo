package com.crudtask.config;

import com.crudtask.model.AuthorRow;
import com.crudtask.model.ProjectRow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestConfig {
    private final AuthorRow author = new AuthorRow();
    private final ProjectRow project = new ProjectRow();
    @Value("${tc.browser.name}")
    private String browser;
    @Value("${tc.url}")
    private String url;
    @Value("${tc.expected.user.email}")
    private String expectedEmailValue;
    @Value("${tc.env.varname}")
    private String envVar;
    @Value("${tc.row.limit}")
    private int rowLimit;
    @Value("${tc.repeatingNumbersAmount}")
    private int repeatingNumbersAmount;

    public String getEnvVar() {
        return envVar;
    }

    public void setEnvVar(String envVar) {
        this.envVar = envVar;
    }

    public ProjectRow getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project.setName(project);
    }

    public AuthorRow getAuthor() {
        return author;
    }

    public void setAuthor(String name, String login, String email) {
        author.setEmail(email);
        author.setName(name);
        author.setLogin(login);
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getExpectedEmailValue() {
        return expectedEmailValue;
    }

    public void setExpectedEmailValue(String expectedEmailValue) {
        this.expectedEmailValue = expectedEmailValue;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public int getRepeatingNumbersAmount() {
        return repeatingNumbersAmount;
    }

    public void setRepeatingNumbersAmount(int repeatingNumbersAmount) {
        this.repeatingNumbersAmount = repeatingNumbersAmount;
    }
}
