package com.a1qa.api.connection;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface IConnection {
    IConnection send(String url);

    IConnection buildRequestParameters(Map<String, String> params);

    void closeConnection();

    IConnection addRequestHeader(String headerName, String headerBody);

    String getResponseContentType();

    int getResponseStatus();

    JsonNode getResponseBodyAsJson();
}
