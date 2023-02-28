package com.crudtask.model;

import com.crudtask.utils.DateUtil;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "session")
public class SessionRow implements ITableRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "session_key")
    @NaturalId
    private String sessionKey;
    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdTime;
    @Column(name = "build_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long buildNumber;

    @Override
    public long getId() {
        return id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Calendar getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Calendar createdTime) {
        this.createdTime = createdTime;
    }

    public long getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(long buildNumber) {
        this.buildNumber = buildNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionRow that)) return false;
        return buildNumber == that.buildNumber && Objects.equals(sessionKey, that.sessionKey)
                && DateUtil.compareCalendars(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionKey, createdTime, buildNumber);
    }
}
