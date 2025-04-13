package org.example.vkbot.dto.response;

public record MessageResponse(
        String text,
        int peer_id,
        String access_token,
        String v,
        int random_id
) {
}
