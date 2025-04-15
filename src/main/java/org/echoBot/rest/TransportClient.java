package org.echoBot.rest;

import org.springframework.http.MediaType;

import java.util.Map;

public interface TransportClient {
    void post(String url, Map<String, String> params);

    void post(String url, Map<String, String> params, MediaType mediaType);
}
