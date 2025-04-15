package org.echoBot.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class RestTransportClient implements TransportClient {
    private final RestClient restClient;
    private final MediaType mediaType = MediaType.APPLICATION_FORM_URLENCODED;

    @Autowired
    public RestTransportClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public String post(String url, MultiValueMap<String, String> params) {
        return restClient.post()
                .uri(url)
                .body(params)
                .contentType(mediaType)
                .retrieve()
                .body(String.class);
    }
}
