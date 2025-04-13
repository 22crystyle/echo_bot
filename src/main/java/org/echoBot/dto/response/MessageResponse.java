package org.echoBot.dto.response;

public record MessageResponse(
        int peer_id,
        String text,
        String access_token,
        String v,
        int random_id
) {
    public static MessageResponse of(int peer_id, String message, String access_token, String v, int random_id) {
        return new MessageResponse(peer_id, message, access_token, v, random_id);
    }
}
