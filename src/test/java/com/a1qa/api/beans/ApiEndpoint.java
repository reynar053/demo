package com.a1qa.api.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ApiEndpoint {
    @JsonProperty("url")
    private String url;
    @JsonProperty("parameters")
    private Map<String, String> parameters;

    public String getUrl() {
        return url;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "ApiEndpoint{" +
                "url='" + url + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
