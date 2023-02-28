package com.a1qa.api.connection;

import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.util.Map;

public class Get extends AbstractConnection {

    protected Get() {
        super();
    }

    {
        request = ClassicRequestBuilder.get().build();
    }

    @Override
    public IConnection buildRequestParameters(Map<String, String> params) {
        super.buildRequestParameters(params);
        params.forEach(uriBuilder::setParameter);
        return this;
    }
}
