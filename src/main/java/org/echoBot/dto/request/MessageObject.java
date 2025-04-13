package org.echoBot.dto.request;

public record MessageObject(
        ClientInfoRequest client_info,
        MessageRequest message
) {
}
