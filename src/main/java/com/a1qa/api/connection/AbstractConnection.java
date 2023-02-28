package com.a1qa.api.connection;

import aquality.selenium.browser.AqualityServices;
import com.a1qa.api.common.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.io.Charsets;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class AbstractConnection implements IConnection {
    protected String responseBody;
    protected String contentType;
    protected int statusCode;
    protected CloseableHttpResponse response;
    protected ClassicHttpRequest request;
    protected final URIBuilder uriBuilder = new URIBuilder();

    AbstractConnection(){
    }

    public IConnection send(String url) {
        sendRequest(url);
        return this;
    }

    public IConnection addRequestHeader(String headerName, String headerBody) {
        request.setHeader(headerName, headerBody);
        return this;
    }

    public IConnection buildRequestParameters(Map<String, String> params){
        AqualityServices.getLogger().info("Building request parameters: %s", params);
        return this;
    }

    protected void sendRequest(String url) {
        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()){
            request.setUri(new URI(url + uriBuilder.build()));
            AqualityServices.getLogger().info("Requesting %s", request.getUri());
            response = httpclient.execute(request);
            saveRequestResults();
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getResponseContentType() {
        return contentType;
    }

    public int getResponseStatus() {
        return statusCode;
    }

    public JsonNode getResponseBodyAsJson() {
        closeConnection();
        return JsonParser.stringToJson(responseBody);
    }

    protected void saveRequestResults() throws IOException, ParseException {
        saveStatusCode();
        saveContentType();
        HttpEntity entity = response.getEntity();

        String encodingHeader = entity.getContentEncoding();
        Charset encoding = encodingHeader == null ? StandardCharsets.UTF_8 :
                Charsets.toCharset(encodingHeader);

        responseBody = EntityUtils.toString(entity, encoding);
        EntityUtils.consume(entity);
    }

    protected void saveContentType() {
        Header contentTypeHeader = response.getFirstHeader("Content-Type");
        contentType = contentTypeHeader.getValue();
    }

    protected void saveStatusCode() {
        statusCode = response.getCode();
    }

    public void closeConnection() {
        AqualityServices.getLogger().info("Closing connection");
        try {
            response.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
