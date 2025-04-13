package org.example.vkbot.service;

import lombok.*;
import org.example.vkbot.config.VkProperties;
import org.example.vkbot.dto.request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Random;

@Service
@Getter @AllArgsConstructor
public class VkService {
    private final VkProperties vkProperties;

    private final String token;
    private final String confirmationToken;
    private final String v;
    private final String vkApiUrl;

    @Autowired
    public VkService(VkProperties vkProperties) {
        this.vkProperties = vkProperties;
        this.token = vkProperties.getToken();
        this.confirmationToken = vkProperties.getConfirmation();
        this.v = vkProperties.getV();
        this.vkApiUrl = vkProperties.getApiUrl();
    }

    @Async
    public void sendMessage(MessageRequest messageRequest) {
        RestClient restClient = RestClient.create(vkApiUrl);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("peer_id", String.valueOf(messageRequest.peer_id()));
        params.add("message", "Вы сказали: " + messageRequest.text());
        params.add("access_token", token);
        params.add("v", v);
        params.add("random_id", String.valueOf(new Random().nextInt(Integer.MAX_VALUE)));

        restClient.post()
                .body(params)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .body(String.class);
    }
}
