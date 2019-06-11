package com.byk.portal.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class RequestLoggingInterceptor implements ClientHttpRequestInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = null;
        try {
            response = execution.execute(request, body);
        } finally {
            BufferingClientHttpResponseWrapper responseWrapper = new BufferingClientHttpResponseWrapper(response);
            LOGGER.info("request method: {}, request URI: {}, request headers: {}, request body: {}, response status code: {}, response headers: {}, response body: {}",
                    request.getMethod(),
                    request.getURI(),
                    request.getHeaders(),
                    new String(body, Charset.forName("UTF-8")),
                    response.getStatusCode(),
                    response.getHeaders(),
                    new String(StreamUtils.copyToByteArray(responseWrapper.getBody()), Charset.forName("UTF-8")));
            return responseWrapper;

        }
    }
}

