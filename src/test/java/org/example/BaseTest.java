package org.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.example.api.APIConnector;
import org.example.configreader.ConfigManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTest {

    protected final static String BASE_URI = ConfigManager.getConfigDataByPath("/baseURL").asText();
    protected final static int RANDOM_STRING_LENGTH = ConfigManager.getConfigDataByPath("/defaultRandomStringLength").asInt();
    protected CloseableHttpResponse response;

    protected APIConnector apiConnector;

    @AfterMethod(alwaysRun = true)
    public void closeResponse() {
        try {
            response.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        response = null;
    }

    @AfterClass(alwaysRun = true)
    public void connectionTearDown() {
        APIConnector.closeConnection();
    }
}
