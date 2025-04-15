package org.echoBot.rest;

import org.springframework.util.MultiValueMap;

public interface TransportClient {
    String post(String url, MultiValueMap<String, String> params);
}
