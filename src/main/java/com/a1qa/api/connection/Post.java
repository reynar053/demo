package com.a1qa.api.connection;

import aquality.selenium.browser.AqualityServices;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class Post extends AbstractConnection {

    protected Post(){
        super();
    }

    {
        request = ClassicRequestBuilder.post().build();
    }

    @Override
    public Post buildRequestParameters(Map<String, String> params){
        super.buildRequestParameters(params);
        request.setEntity(new UrlEncodedFormEntity(parameterMapToList(params), StandardCharsets.UTF_8));
        return this;
    }

    public Post buildMultipartRequestParameters(String filePath) {
        AqualityServices.getLogger().info("Building multipart request. Content is %s", filePath);
        HttpEntity multipart = MultipartEntityBuilder
                .create()
                .addBinaryBody("file1", new File(filePath))
                .build();
        request.setEntity(multipart);
        return this;
    }

    private ArrayList<NameValuePair> parameterMapToList(Map<String, String> params) {
        ArrayList<NameValuePair> postParameters = new ArrayList<>();
        params.forEach((key, value) -> postParameters.add(new BasicNameValuePair(key, value)));
        return postParameters;
    }
}
