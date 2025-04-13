package org.example.vkbot.dto.request;

import java.util.ArrayList;

public record MessageRequest(
        int date,
        int from_id,
        int id,
        int version,
        int out,
        ArrayList<Object>fwd_messages,
        boolean important,
        boolean is_hidden,
        ArrayList<Object> attachments,
        int conversation_message_id,
        String text,
        int peer_id,
        int random_id
) {
    
}
