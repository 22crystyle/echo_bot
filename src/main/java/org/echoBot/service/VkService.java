package org.echoBot.service;

import lombok.*;
import org.echoBot.config.ResponseProperties;
import org.echoBot.dto.MessageMapping;
import org.echoBot.dto.request.MessageRequest;
import org.echoBot.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Service
@Getter @AllArgsConstructor
public class VkService {
    private final MessageMapping messageMapping;
    private final String vkApiUrl;

    @Autowired
    public VkService(ResponseProperties responseProperties, MessageMapping messageMapping) {
        this.vkApiUrl = responseProperties.getApiUrl();
        this.messageMapping = messageMapping;
    }

    @Async
    public void sendMessage(MessageRequest messageRequest) {
        RestClient restClient = RestClient.create(vkApiUrl);
        MessageResponse message = messageMapping.toResponse(messageRequest);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("peer_id", String.valueOf(message.peer_id()));
        params.add("message", "Вы сказали: " + message.text());
        params.add("access_token", message.access_token());
        params.add("v", message.v());
        params.add("random_id", String.valueOf(message.random_id()));

        restClient.post()
                .body(params)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .body(String.class);
    }
}
