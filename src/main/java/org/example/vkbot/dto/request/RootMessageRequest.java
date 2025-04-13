package org.example.vkbot.dto.request;

public record RootMessageRequest(
        int group_id,
        String type,
        String event_id,
        String v,
        MessageObject object

) {
}
