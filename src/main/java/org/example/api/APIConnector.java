package org.example.api;

import org.apache.commons.io.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.example.logger.FrameworkLogger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public abstract class APIConnector {
    private static HttpGet httpGet;
    private static HttpPost httpPost;
    private static final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public static CloseableHttpResponse makeGetRequest(String url) {
        FrameworkLogger.logInfo("GET requesting '{}'", url);
        httpGet = new HttpGet(url);
        try {
            return httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CloseableHttpResponse makePostRequest(String url, Map<String, String> params) {
        FrameworkLogger.logInfo("POST requesting '{}' with parameters: '{}'", url, params);
        httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(buildParameters(params), "UTF-8"));
            return httpClient.execute(httpPost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<NameValuePair> buildParameters(Map<String, String> params) {
        ArrayList<NameValuePair> postParameters = new ArrayList<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            postParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return postParameters;
    }

    public static int getStatusCode(CloseableHttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        FrameworkLogger.logInfo("Status code is: '{}'", statusCode);
        return statusCode;
    }

    public static String getResponseBodyAsString(CloseableHttpResponse response) {
        FrameworkLogger.logInfo("Getting response body");

        HttpEntity entity = response.getEntity();
        String body;

        Header encodingHeader = entity.getContentEncoding();
        Charset encoding = encodingHeader == null ? StandardCharsets.UTF_8 :
                Charsets.toCharset(encodingHeader.getValue());

        try {
            body = EntityUtils.toString(entity, encoding);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return body;
    }

    public static String getContentType(CloseableHttpResponse response) {
        Header contentTypeHeader = response.getFirstHeader("Content-Type");
        FrameworkLogger.logInfo("Content type is: '{}'", contentTypeHeader);
        return contentTypeHeader.getValue();
    }

    public static void closeConnection() {
        FrameworkLogger.logInfo("Closing connection");
        try {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}