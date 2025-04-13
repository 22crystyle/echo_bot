package org.echoBot.dto;

import org.echoBot.config.ResponseProperties;
import org.echoBot.dto.request.MessageRequest;
import org.echoBot.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MessageMapping {
    private final ResponseProperties properties;

    @Autowired
    public MessageMapping(ResponseProperties properties) {
        this.properties = properties;
    }

    public MessageResponse toResponse(MessageRequest request) {
        return MessageResponse.of(
                request.peer_id(),
                request.text(),
                properties.getToken(),
                properties.getV(),
                new Random().nextInt(Integer.MAX_VALUE)
        );
    }
}
