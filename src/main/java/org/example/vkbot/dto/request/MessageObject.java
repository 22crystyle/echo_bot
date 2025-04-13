package org.example.vkbot.dto.request;

public record MessageObject(
        ClientInfoRequest client_info,
        MessageRequest message
) {
}
