package org.echoBot.rest;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.Map;

public class RestTransportClient implements TransportClient {
    private final RestClient restClient;
    private final MediaType mediaType = MediaType.APPLICATION_FORM_URLENCODED;

    public RestTransportClient() {
        this.restClient = RestClient.create("https://api.vk.com/method/messages.send");
    }


    // TODO: избежать получения параметров в незашифрованном виде
    @Override
    public void post(String url, Map<String, String> params) {
        String responseBody = restClient.post().uri(url)
                .body(params)
                .contentType(mediaType)
                .retrieve()
                .body(String.class);
    }


    @Override
    public void post(String url, Map<String, String> params, MediaType mediaType) {
        restClient.post()
                .uri(url)
                .body(params)
                .contentType(mediaType)
                .retrieve()
                .body(String.class);
    }
}
