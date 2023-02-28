package com.crudtask.model;

import com.crudtask.utils.DateUtil;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "test")
public class TestRow implements ITableRow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "status_id")
    @ManyToOne(targetEntity = StatusRow.class)
    private StatusRow status;
    @Column(name = "method_name")
    private String methodName;
    @JoinColumn(name = "project_id")
    @ManyToOne(targetEntity = ProjectRow.class)
    private ProjectRow project;
    @JoinColumn(name = "session_id")
    @ManyToOne(targetEntity = SessionRow.class)
    private SessionRow session;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endTime;
    @Column(name = "env")
    private String env;
    @Column(name = "browser")
    private String browser;
    @JoinColumn(name = "author_id")
    @ManyToOne(targetEntity = AuthorRow.class)
    private AuthorRow author;

    public TestRow(TestRow row) {
        setName(row.getName());
        setStatus(row.getStatus());
        setMethodName(row.getMethodName());
        setProject(row.getProject());
        setSession(row.getSession());
        setStartTime(row.getStartTime());
        setEndTime(row.getEndTime());
        setEnv(row.getEnv());
        setBrowser(row.getBrowser());
        setAuthor(row.getAuthor());
    }

    public TestRow() {

    }

    public TestRow(String name, StatusRow status, String methodName, ProjectRow project,
                   SessionRow session, Calendar startTime, Calendar endTime, String env, String browser, AuthorRow author) {
        this.name = name;
        this.status = status;
        this.methodName = methodName;
        this.project = project;
        this.session = session;
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = env;
        this.browser = browser;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusRow getStatus() {
        return status;
    }

    public void setStatus(StatusRow status_id) {
        this.status = status_id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String method_name) {
        this.methodName = method_name;
    }

    public ProjectRow getProject() {
        return project;
    }

    public void setProject(ProjectRow project_id) {
        this.project = project_id;
    }

    public SessionRow getSession() {
        return session;
    }

    public void setSession(SessionRow session_id) {
        this.session = session_id;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public AuthorRow getAuthor() {
        return author;
    }

    public void setAuthor(AuthorRow author_id) {
        this.author = author_id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status_id=" + status +
                ", method_name='" + methodName + '\'' +
                ", project_id=" + project +
                ", session_id=" + session +
                ", start_time=" + startTime +
                ", end_time=" + endTime +
                ", env='" + env + '\'' +
                ", browser='" + browser + '\'' +
                ", author_id=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestRow testRow)) return false;
        return Objects.equals(name, testRow.name) && Objects.equals(status, testRow.status) && Objects.equals(methodName, testRow.methodName)
                && Objects.equals(project, testRow.project) && Objects.equals(session, testRow.session)
                && DateUtil.compareCalendars(startTime, testRow.startTime) && DateUtil.compareCalendars(endTime, testRow.endTime)
                && Objects.equals(env, testRow.env)
                && Objects.equals(browser, testRow.browser) && Objects.equals(author, testRow.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, methodName, project, session, startTime, endTime, env, browser, author);
    }
}
