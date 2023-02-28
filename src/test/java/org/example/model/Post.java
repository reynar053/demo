package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Post {
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

    public Post() {}

    public Post(Integer userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Map<String, String> getPostAsMap() {
        Map<String, String> postMap = new HashMap<>();
        postMap.put("userId", userId.toString());
        postMap.put("title", title);
        postMap.put("body", body);
        return postMap;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Post: " +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'';
    }
}
