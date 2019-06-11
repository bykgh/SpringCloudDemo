package com.byk.portal.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Simple implementation of {@link ClientHttpResponse} that reads the response's body
 * into memory, thus allowing for multiple invocations of {@link #getBody()}.
 *
 * @author Arjen Poutsma
 * @since 3.1
 */
final class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

    private final ClientHttpResponse response;

    private byte[] body;


    BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
        this.response = response;
    }


    @Override
    public HttpStatus getStatusCode() throws IOException {
        return this.response.getStatusCode();
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return this.response.getRawStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        return this.response.getStatusText();
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.response.getHeaders();
    }

    @Override
    public InputStream getBody() throws IOException {
        if (this.body == null) {
            this.body = StreamUtils.copyToByteArray(this.response.getBody());
        }
        return new ByteArrayInputStream(this.body);
    }

    @Override
    public void close() {
        this.response.close();
    }

}