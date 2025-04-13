package org.example.vkbot.dto;

import org.example.vkbot.config.VkProperties;
import org.example.vkbot.dto.request.MessageRequest;
import org.example.vkbot.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MessageMapping {
    private final VkProperties properties;

    @Autowired
    public MessageMapping(VkProperties properties) {
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
